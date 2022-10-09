package ru.yandex.incoming34.PPTestApp.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.incoming34.PPTestApp.component.UserOrder;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentService.class);
    Base64.Encoder base64encoder = Base64.getEncoder();


    private final String APPROVE_LINK_REL = "approve";

    @Autowired
    private APIContext apiContext;

    private final String cancelUrl;
    private final String successUrl;

    public PaymentService(@Qualifier("cancelUrl") String cancelUrl, @Qualifier("successUrl") String successUrl) {
        this.cancelUrl = cancelUrl;
        this.successUrl = successUrl;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }

    public Optional<Payment> createPayment(UserOrder userOrder) {

        Payment payment = new Payment();

        payment.setIntent(userOrder.getIntent());
        payment.setPayer(createPayer(userOrder));
        payment.setTransactions(createTransactionList(userOrder));
        payment.setRedirectUrls(createRedirectUrls());

        try {
            return Optional.of(payment.create(apiContext));
        } catch (PayPalRESTException e) {
            logger.error("Ошибка создания Payment {}", base64encoder.encode(userOrder.toString().getBytes()));
            return Optional.empty();
        }

    }

    private RedirectUrls createRedirectUrls() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        return redirectUrls;
    }

    private Payer createPayer(UserOrder userOrder) {
        Payer payer = new Payer();
        payer.setPaymentMethod(userOrder.getMethod());
        return payer;
    }

    private List<Transaction> createTransactionList(UserOrder userOrder) {
        Transaction transaction = createTransaction(userOrder);
        return List.of(transaction);

    }

    private Transaction createTransaction(UserOrder userOrder) {
        Transaction transaction = new Transaction();
        transaction.setDescription(userOrder.getDescription());
        transaction.setAmount(createAmount(userOrder));
        return transaction;

    }

    private Amount createAmount(UserOrder userOrder) {
        Amount amount = new Amount();
        amount.setCurrency(userOrder.getCurrency());
        amount.setTotal(String.valueOf(userOrder.getPrice()));
        return amount;

    }
}
