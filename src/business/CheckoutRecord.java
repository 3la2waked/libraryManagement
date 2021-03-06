package business;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
    @Serial
    private static final long serialVersionUID = -6992600305410796258L;

    private final LibraryMember libraryMember;

    private final List<CheckoutRecordEntry> checkoutRecordEntries;

    public CheckoutRecord(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
        checkoutRecordEntries = new ArrayList<>();
    }

    public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
        return checkoutRecordEntries;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void addCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry) {
        checkoutRecordEntries.add(checkoutRecordEntry);
    }
}
