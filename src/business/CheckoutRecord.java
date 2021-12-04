package business;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = -6992600305410796258L;

    private final List<CheckoutRecordEntry> checkoutRecordEntries;

    public CheckoutRecord() {
        checkoutRecordEntries = new ArrayList<>();
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
        return checkoutRecordEntries;
    }

    public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry) {
        checkoutRecordEntries.add(checkoutRecordEntry);
    }
}
