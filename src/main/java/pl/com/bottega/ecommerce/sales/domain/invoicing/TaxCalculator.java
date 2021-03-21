package pl.com.bottega.ecommerce.sales.domain.invoicing;

public interface TaxCalculator {
    Tax calculateTax(RequestItem item);
}
