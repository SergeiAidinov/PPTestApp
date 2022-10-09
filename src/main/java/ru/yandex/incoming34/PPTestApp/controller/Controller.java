package ru.yandex.incoming34.PPTestApp.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.PPTestApp.component.UserOrder;
import ru.yandex.incoming34.PPTestApp.service.OrderService;
import ru.yandex.incoming34.PPTestApp.service.PaymentService;

import java.util.Optional;

@RestController("/orders")
public class Controller {

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    public static final String SUCCESS_URL = "pay/success";
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("/pay")
    public String payment(@RequestBody UserOrder userOrder) {
        logger.info("Обрабатываем заказ {}", userOrder.toString());

        Optional<Payment> paymentOptional = paymentService.createPayment(userOrder);

        if (paymentOptional.isPresent()) {
            for (Links link : paymentOptional.get().getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping(value = SUCCESS_URL)
    public String resultPayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paymentService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "redirect:/success.html";
            }
        } catch (PayPalRESTException e) {
            logger.error("Ошибка платежа: paymentId: {}, payerId: {}", paymentId, payerId);
        }
        return "redirect:/failure.html";
    }

}
