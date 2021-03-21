package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

public class TaxCalculator {

    Money net;
    BigDecimal ratio;
    String desc;
    Money taxValue;

    public TaxCalculator(){
        ratio = null;
        desc = null;
    }

    public void findTaxValue(RequestItem item){
        net = item.getTotalCost();

        switch (item.getProductData().getType()) {
            case DRUG:
                ratio = BigDecimal.valueOf(0.05);
                desc = "5% (D)";
                break;
            case FOOD:
                ratio = BigDecimal.valueOf(0.07);
                desc = "7% (F)";
                break;
            case STANDARD:
                ratio = BigDecimal.valueOf(0.23);
                desc = "23%";
                break;

            default:
                throw new IllegalArgumentException(item.getProductData().getType() + " not handled");
        }
        taxValue = net.multiplyBy(ratio);
    }

    public Money getNet() {
        return net;
    }

    public String getDesc() {
        return desc;
    }

    public Money getTaxValue() {
        return taxValue;
    }
}
