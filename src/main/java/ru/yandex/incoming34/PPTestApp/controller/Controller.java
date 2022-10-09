package ru.yandex.incoming34.PPTestApp.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private String orderId = new String();

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay")
    public String payment(@RequestBody UserOrder userOrder) {

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

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paymentService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "http://src/main/resources/templates/success.jsp";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
