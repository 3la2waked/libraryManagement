package dataaccess;

import business.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class DataAccessFacade implements DataAccess {
	
	enum StorageType {
		BOOKS, MEMBERS, USERS, CHECKOUTRECORD, CHECKOUTENTRIES;
	}
	
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ File.separator + "src" + File.separator + "dataaccess" + File.separator + "storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";
	
	//implement: other save operations
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}

	public void saveNewBook(Book book) {
		HashMap<String, Book> bk = readBooksMap();
		String isbn = book.getIsbn();
		bk.put(isbn, book);
		saveToStorage(StorageType.BOOKS, bk);
	}
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		//Returns a Map with name/value pairs being
		//   isbn -> Book
		return (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		//Returns a Map with name/value pairs being
		//   memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(
				StorageType.MEMBERS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		return (HashMap<String, CheckoutRecord>) readFromStorage(StorageType.CHECKOUTRECORD);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecordEntry> readCheckoutEntryMap() {
		return (HashMap<String, CheckoutRecordEntry>) readFromStorage(StorageType.CHECKOUTENTRIES);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		//Returns a Map with name/value pairs being
		//   userId -> User
		return (HashMap<String, User>)readFromStorage(StorageType.USERS);
	}

	public CheckoutRecordEntry checkoutBookCopy(Book book, LibraryMember libraryMember) {
		BookCopy bookCopy = book.getNextAvailableCopy();
		if (null != bookCopy) {
			bookCopy.changeAvailability();
			HashMap<String, CheckoutRecordEntry> checkoutRecordEntries = readCheckoutEntryMap();

			if (null == checkoutRecordEntries)
				checkoutRecordEntries = new HashMap<>();

			String key;
			if (checkoutRecordEntries.isEmpty()) {
				key = "1";
			}
			else {
				List<String> keys = new ArrayList<>(checkoutRecordEntries.keySet());
				List<Integer> intKeys = keys.stream().map(Integer::parseInt).collect(Collectors.toList());
				key = "" + (Collections.max(intKeys) + 1);
			}

			CheckoutRecordEntry entry = new CheckoutRecordEntry(libraryMember.getCheckoutRecord(), bookCopy);
			checkoutRecordEntries.put(key, entry);

			libraryMember.getCheckoutRecord().addCheckoutRecordEntry(entry);
			saveToStorage(StorageType.CHECKOUTENTRIES, checkoutRecordEntries);

			book.updateCopies(bookCopy);
			HashMap<String,Book> bookMap = readBooksMap();
			bookMap.put(book.getIsbn(), book);
			saveToStorage(StorageType.BOOKS, bookMap);

			return entry;
		}
		return null;
	}
	
	
	/////load methods - these place test data into the storage area
	///// - used just once at startup  
	
		
	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}
	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}
 
	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);

		HashMap<String, CheckoutRecord> checkoutRecords = new HashMap<>();
		memberList.forEach(member -> checkoutRecords.put(member.getMemberId(), new CheckoutRecord()));
		saveToStorage(StorageType.CHECKOUTRECORD, checkoutRecords);
	}
	
	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	
	
	
	final static class Pair<S,T> implements Serializable{
		
		S first;
		T second;
		Pair(S s, T t) {
			first = s;
			second = t;
		}
		@Override 
		public boolean equals(Object ob) {
			if(ob == null) return false;
			if(this == ob) return true;
			if(ob.getClass() != getClass()) return false;
			@SuppressWarnings("unchecked")
			Pair<S,T> p = (Pair<S,T>)ob;
			return p.first.equals(first) && p.second.equals(second);
		}
		
		@Override 
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}
		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}
		private static final long serialVersionUID = 5399827794066637059L;
	}
}
