/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.math.BigDecimal;
import java.util.List;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class BookKeeper {

    public Invoice issuance(InvoiceRequest request) {
        Invoice invoice = Invoice.createInvoice(Id.generate(), request.getClient());
        TaxStakes taxStake = new TaxStakes("PL", BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.07), BigDecimal.valueOf(0.23));
        for (RequestItem item : request.getItems()) {
            Tax tax = taxStake.getTaxRatio(item, "PL");
            InvoiceLine invoiceLine = new InvoiceLine(item.getProductData(), item.getQuantity(), item.getTotalCost(), tax);
            invoice.addItem(invoiceLine);
        }

        return invoice;
    }

}
