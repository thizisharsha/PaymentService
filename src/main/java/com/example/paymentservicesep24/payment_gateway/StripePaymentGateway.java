package com.example.paymentservicesep24.payment_gateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGatewayInterface{
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    @Override
    public String createPaymentLink(long orderId, long amount) throws StripeException {
        //we are going to call stripe to do our payment
        Stripe.apiKey = stripeSecretKey;

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice("price_1Q2pAnDsLlWoivyTnhcf1mmD")
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://www.linkedin.com/in/gayathri-adusumilli-335a33219/")
                                .build()
                                ).build()
                        ).build();

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getUrl();
    }
}
