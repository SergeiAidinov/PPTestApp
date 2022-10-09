package ru.yandex.incoming34.PPTestApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.incoming34.PPTestApp.component.UserOrder;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    @Autowired
    PaymentService paymentService;

    private final String APPROVE_LINK_REL = "approve";

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    /*

    public UserOrder createOrder(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = createOrderRequest(totalAmount, returnUrl);
        final OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);
        final HttpResponse<Order> orderHttpResponse;
        try {
            orderHttpResponse = paymentService.getPayPalHttpClient().execute(ordersCreateRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final Order order = orderHttpResponse.result();
        LinkDescription approveUri = extractApprovalLink(order);
        return new UserOrder(order.id(), URI.create(approveUri.href()));

    }

    private OrderRequest createOrderRequest(Double totalAmount, URI returnUrl) {
        final OrderRequest orderRequest = new OrderRequest();
        setCheckoutIntent(orderRequest);
        setPurchaseUnits(totalAmount, orderRequest);
        setApplicationContext(returnUrl, orderRequest);
        return orderRequest;
    }

    private void setCheckoutIntent(OrderRequest orderRequest) {
        orderRequest.checkoutPaymentIntent("CAPTURE");
    }

    private OrderRequest setApplicationContext(URI returnUrl, OrderRequest orderRequest) {
        return orderRequest.applicationContext(new ApplicationContext().returnUrl(returnUrl.toString()));
    }

    private void setPurchaseUnits(Double totalAmount, OrderRequest orderRequest) {
        final PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(totalAmount.toString()));
        orderRequest.purchaseUnits(Arrays.asList(purchaseUnitRequest));
    }

    private com.paypal.orders.LinkDescription extractApprovalLink(Order order) {
        com.paypal.orders.LinkDescription approveUri = order.links().stream()
                .filter(link -> APPROVE_LINK_REL.equals(link.rel()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return approveUri;
    }


    public void captureOrder(String token, PayPalHttpClient payPalHttpClient, String orderId) {

        final OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(orderId);
        final HttpResponse<Order> httpResponse;
        try {
            httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Order Capture Status: {}",httpResponse.result().status());
    }

     */
}
