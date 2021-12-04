package business;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {

    @Serial
    private static final long serialVersionUID = -11660003499541135L;

    private final CheckoutRecord checkoutRecord;
    private final BookCopy bookCopy;
    private final LocalDate checkoutDate, dueDate;

    public CheckoutRecordEntry(CheckoutRecord checkoutRecord, BookCopy bookCopy) {
        this.checkoutRecord = checkoutRecord;
        this.bookCopy = bookCopy;
        checkoutDate = LocalDate.now();
        dueDate = checkoutDate.plusDays(bookCopy.getBook().getMaxCheckoutLength());
    }

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
