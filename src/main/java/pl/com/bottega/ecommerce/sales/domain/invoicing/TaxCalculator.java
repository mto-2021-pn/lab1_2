package pl.com.bottega.ecommerce.sales.domain.invoicing;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;
import java.math.BigDecimal;


public class TaxCalculator {

    public static Tax calculateTax(RequestItem item, String country){
        BigDecimal ratio = getRatio(item.getProductData().getType(), country);
        String desc = getDesc(item.getProductData().getType(), country);

        Money taxValue = item.getTotalCost().multiplyBy(ratio);

        return new Tax(taxValue, desc);
    }

    private static BigDecimal getRatio(ProductType type, String country){
        BigDecimal ratio = null;
        if(country.equals("PL"))
            switch (type) {
                case DRUG:
                    ratio = BigDecimal.valueOf(0.05);
                    break;
                case FOOD:
                    ratio = BigDecimal.valueOf(0.07);
                    break;
                case STANDARD:
                    ratio = BigDecimal.valueOf(0.23);
                    break;
                default:
                    throw new IllegalArgumentException("type not handled");
            }
        else
            ratio = BigDecimal.valueOf(0.12);
        return ratio;
    }

    private static String getDesc(ProductType type, String country){
        String desc = null;
        if(country.equals("PL"))
            switch (type) {
                case DRUG:
                    desc = "5% (D)";
                    break;
                case FOOD:
                    desc = "7% (F)";
                    break;
                case STANDARD:
                    desc = "23%";
                    break;
                default:
                    throw new IllegalArgumentException("type not handled");
            }
        else
            desc = "15%";
        return desc;
    }
}
