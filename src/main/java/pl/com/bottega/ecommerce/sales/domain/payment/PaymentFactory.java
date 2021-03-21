package pl.com.bottega.ecommerce.sales.domain.payment;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class PaymentFactory {

    public Payment createPaymentForClient(Id aggregateId, ClientData clientData, Money amount) {
        return new Payment(aggregateId, clientData, amount);
    }

    public Payment createRollBackPayment(Payment payment) {
        return payment.rollBack();
    }

}
