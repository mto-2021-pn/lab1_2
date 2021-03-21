package pl.com.bottega.ecommerce.sales.domain.invoicing;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public interface TaxFactory {
    Tax createTax(ProductType productType, Money money);
}
