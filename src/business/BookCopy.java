package business;

import java.io.Serial;
import java.io.Serializable;

/**
 * Immutable class
 */
final public class BookCopy implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -63976228084869815L;
	private Book book;
	private int copyNum;
	private boolean isAvailable;
	private CheckoutRecordEntry checkoutRecordEntry;

	BookCopy(Book book, int copyNum, boolean isAvailable) {
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	BookCopy(Book book, int copyNum) {
		this.book = book;
		this.copyNum = copyNum;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public int getCopyNum() {
		return copyNum;
	}
	
	public Book getBook() {
		return book;
	}

	public CheckoutRecordEntry getCheckoutRecordEntry() {
		return checkoutRecordEntry;
	}
	
	public void changeAvailability(CheckoutRecordEntry checkoutRecordEntry) {
		if (isAvailable) {
			this.checkoutRecordEntry = checkoutRecordEntry;
			isAvailable = false;
		}
		else {
			isAvailable = true;
		}
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof BookCopy)) return false;
		BookCopy copy = (BookCopy)ob;
		return copy.book.getIsbn().equals(book.getIsbn()) && copy.copyNum == copyNum;
	}
}
