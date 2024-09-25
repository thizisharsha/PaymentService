package com.example.paymentservicesep24.payment_gateway;

public class RazorpayPaymentGateway implements PaymentGatewayInterface{
    @Override
    public String createPaymentLink(long orderId, long amount) {
        return "";
    }
}
