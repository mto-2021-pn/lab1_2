package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CountryTaxes {

    static ArrayList<BigDecimal> taxValue;
    static ArrayList<String> taxDescription;

    private CountryTaxes()
    {
        taxValue = new ArrayList<>();
        taxDescription = new ArrayList<>();
    }

    public static CountryTaxes getCountryTaxes(String country) {
        CountryTaxes taxes = new CountryTaxes();
        if(country.equalsIgnoreCase("POLAND")){
            taxValue.add(BigDecimal.valueOf(0.05));
            taxDescription.add("5% (D)");

            taxValue.add(BigDecimal.valueOf(0.07));
            taxDescription.add("7% (F)");

            taxValue.add(BigDecimal.valueOf(0.23));
            taxDescription.add("23%");
        }
        return taxes;
    }

    public Tax taxCalculation(RequestItem item){
        BigDecimal ratio;
        String desc;

        switch (item.getProductData().getType()) {
            case DRUG:
                ratio = taxValue.get(0);
                desc = taxDescription.get(0);
                break;
            case FOOD:
                ratio = taxValue.get(1);
                desc = taxDescription.get(1);
                break;
            case STANDARD:
                ratio = taxValue.get(2);
                desc = taxDescription.get(2);
                break;

            default:
                throw new IllegalArgumentException(item.getProductData().getType() + " not handled");
        }

        Money taxValue = item.getTotalCost().multiplyBy(ratio);
        return new Tax(taxValue, desc);
    }
}
