package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

public class TaxStakes {
    String country;
    BigDecimal drugStake;
    BigDecimal foodStake;
    BigDecimal standartStake;
    String drugStakeDesc;
    String foodStakeDesc;
    String standartStakeDesc;

    public TaxStakes (String country, BigDecimal drugStake, BigDecimal foodStake, BigDecimal standartStake){
        this.country = country;
        this.drugStake = drugStake;
        this.foodStake = foodStake;
        this.standartStake = standartStake;
        this.drugStakeDesc =(drugStake.multiply(BigDecimal.valueOf(100)))+"% (D)";
        this.foodStakeDesc =(foodStake.multiply(BigDecimal.valueOf(100)))+"% (F)";
        this.standartStakeDesc =String.valueOf(standartStake.multiply(BigDecimal.valueOf(100)));
    }

    public Tax getTaxRatio(RequestItem item, String country){
        Money net = item.getTotalCost();
        BigDecimal ratio = null;
        String desc = null;
        switch (item.getProductData().getType()) {
            case DRUG:
                ratio = drugStake;
                desc = drugStakeDesc;
                break;
            case FOOD:
                ratio = foodStake;
                desc = foodStakeDesc;
                break;
            case STANDARD:
                ratio = standartStake;
                desc = standartStakeDesc;
                break;

            default:
                throw new IllegalArgumentException(item.getProductData().getType() + " not handled");
        }

        Money taxValue = net.multiplyBy(ratio);

        return new Tax(taxValue, desc);
    }
}
