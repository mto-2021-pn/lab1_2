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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class Invoice {

    private ClientData client;

    private Money net;

    private Money gros;

    private List<InvoiceLine> items;

    private Id id;

    private Invoice(Id invoiceId, ClientData client) {
        this.id = invoiceId;
        this.client = client;
        this.items = new ArrayList<InvoiceLine>();

        this.net = Money.ZERO;
        this.gros = Money.ZERO;
    }

    public static InvoiceBuilder builder() {
        return new InvoiceBuilder();
    }

    public static final class InvoiceBuilder {

        private ClientData client;
        private Money net = Money.ZERO;
        private Money gros = Money.ZERO;
        private List<InvoiceLine> items = new ArrayList<>();
        private Id id;

        private InvoiceBuilder() {}

        public InvoiceBuilder setClient(ClientData clientData) {
            this.client = clientData;
            return this;
        }

        public InvoiceBuilder setNet(Money net) {
            this.net = net;
            return this;
        }

        public InvoiceBuilder setGros(Money gros) {
            this.gros = gros;
            return this;
        }

        public InvoiceBuilder setItems(List<InvoiceLine> items) {
            this.items = items;
            return this;
        }

        public InvoiceBuilder setId(Id id) {
            this.id = id;
            return this;
        }

        public InvoiceBuilder addItem(InvoiceLine item) {
            this.items.add(item);
            this.addNet(item.getNet());
            this.addGros(item.getGros());
            return this;
        }

        private void addNet(Money net) {
            this.net.add(net);
        }

        private void addGros(Money gros) {
            this.gros.add(gros);
        }

        public Invoice build() {
            Invoice invoice = new Invoice(this.id, this.client);
            invoice.gros = this.gros;
            invoice.items = this.items;
            invoice.net = this.net;
            return invoice;
        }
    }

    /**
     * 
     * @return immutable projection
     */
    public List<InvoiceLine> getItems() {
        return Collections.unmodifiableList(items);
    }

    public ClientData getClient() {
        return client;
    }

    public Money getNet() {
        return net;
    }

    public Money getGros() {
        return gros;
    }

}
