package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

public class FoodIssuanceCalculator implements IssuanceCalculator {
    private Money net;

    public FoodIssuanceCalculator(Money net, RequestItem item) {
        this.net = net;
        this.item = item;
    }

    private RequestItem item;
    @Override
    public InvoiceLine calculate() {
        BigDecimal ratio =  BigDecimal.valueOf(0.07);
        String desc = "7% (F)";
        Money taxValue = net.multiplyBy(ratio);
        Tax tax = new Tax(taxValue, desc);
        return new InvoiceLine(item.getProductData(), item.getQuantity(), net, tax);
    }
}
