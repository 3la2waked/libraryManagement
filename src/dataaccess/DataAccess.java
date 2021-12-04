package dataaccess;

import java.util.HashMap;

import business.Book;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	HashMap<String, Book> readBooksMap();
	HashMap<String, User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
	HashMap<String, CheckoutRecordEntry> readCheckoutEntryMap();
	void saveNewMember(LibraryMember member);
	CheckoutRecordEntry checkoutBookCopy(Book book, LibraryMember libraryMember);
}
