package pl.com.bottega.ecommerce.sales.domain.payment;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class Payment {

    private ClientData clientData;

    private Money amount;

    private Id aggregateId;

    Payment(Id aggregateId, ClientData clientData, Money amount) {
        this.aggregateId = aggregateId;
        this.clientData = clientData;
        this.amount = amount;
    }

    public Payment rollBack() {
        Id id = Id.generate();
        PaymentFactory factory = new PaymentFactory();
        return factory.getPayment(id, clientData, amount.multiplyBy(-1));
    }
}