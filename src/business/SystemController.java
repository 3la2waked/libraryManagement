package business;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.LoginWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	@FXML
	private Button addMember;

	@FXML
	private Button addBook;

	@FXML
	private Button addBookCopy;

	@FXML
	private Button checkoutBook;

	@FXML
	public void initialize() {
		if (currentAuth == Auth.LIBRARIAN) {
			addMember.setVisible(false);
			addBook.setVisible(false);
			addBookCopy.setVisible(false);
		}
		if (currentAuth == Auth.ADMIN) {
			checkoutBook.setVisible(false);
		}
	}
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	public void showAddMember() {

	}

	public void showAddBookCopy() {

	}

	public void showCheckoutBook() {

	}

	public void showAddBook() {

	}
}
