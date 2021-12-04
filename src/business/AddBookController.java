package business;


import com.sun.javafx.scene.control.LabeledText;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ui.AddBookWindow;
import ui.MainWindow;
import ui.Start;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddBookController {

	@FXML
	private ListView<String> authorsList;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField telephone;
	@FXML
	private TextField street;
	@FXML
	private TextField city;
	@FXML
	private TextField state;
	@FXML
	private TextField zip;
	@FXML
	private TextArea bio;
	@FXML
	private Text message;
	@FXML
	private Text messageBook;

	@FXML
	private TextField isbn;
	@FXML
	private TextField title;
	@FXML
	private TextField maxCheckoutLength;

	@FXML
	private Text isbnLabel;
	@FXML
	private Text titleLabel;
	@FXML
	private Text maxLabel;
	@FXML
	private Text authorsLabel;

	private ArrayList<Author> addedAuthors= new ArrayList<Author>();

	public void initialize() {
		authorsList.getItems().add("First Name | Last Name | Telephone | Street | City | State | Zip");
	}
	public void back(){
		Start.hideAllWindows();
		MainWindow.INSTANCE.init();
		MainWindow.INSTANCE.show();
	}

	public void cleanAuthor(){
		message.setText("");
		fname.setText("");
		lname.setText("");
		telephone.setText("");
		street.setText("");
		state.setText("");
		city.setText("");
		zip.setText("");
		bio.setText("");
	}

	public void addAuthor(){
		if(isValidAuthor()){
			authorsList.getItems().add(fname.getText()+" | "+lname.getText()+" | "+telephone.getText()+" | "+street.getText()+" | "+city.getText()+" | "+state.getText()+" | "+zip.getText());
			Address address = new Address(street.getText(),city.getText(),state.getText(),zip.getText());
			Author author= new Author(fname.getText(),lname.getText(),telephone.getText(),address,bio.getText());
			addedAuthors.add(author);
			cleanAuthor();
		}else{
			message.setFill(Color.RED);
			message.setText("Fill All Fields");
		}

	}

	public void save(){

		if(isValid()){
			try
			{
				Integer.parseInt(maxCheckoutLength.getText());
			}
			catch (NumberFormatException e)
			{
				messageBook.setFill(Color.RED);
				messageBook.setText("max Checkout Length Must be Integer");
			}

			Book book = new Book(isbn.getText(),title.getText(),Integer.parseInt(maxCheckoutLength.getText()),addedAuthors);
			DataAccessFacade daf = new DataAccessFacade();
			daf.saveNewBook(book);
			messageBook.setFill(Color.GREEN);
			messageBook.setText("Book Added Successfully");
		}else {
			messageBook.setFill(Color.RED);
			messageBook.setText("Fields With Red Color are Required");
		}


	}

	private boolean isValid() {
		boolean valid = true;


		if (isbn.getText().isEmpty()) {
			isbnLabel.setFill(Color.FIREBRICK);
			valid = false;
		} else {
			isbnLabel.setFill(Color.BLACK);
		}

		if (title.getText().isEmpty()) {
			titleLabel.setFill(Color.FIREBRICK);
			valid = false;
		} else {
			titleLabel.setFill(Color.BLACK);
		}

		if (maxCheckoutLength.getText().isEmpty()) {
			maxLabel.setFill(Color.FIREBRICK);
			valid = false;
		} else {
			maxLabel.setFill(Color.BLACK);
		}

		if (addedAuthors.isEmpty()) {
			authorsLabel.setFill(Color.FIREBRICK);
			valid = false;
		} else {
			authorsLabel.setFill(Color.BLACK);
		}

		return valid;
	}

	private boolean isValidAuthor(){

		if(fname.getText().isEmpty()){
			return false;
		}

		if(lname.getText().isEmpty()){
			return false;
		}

		if(telephone.getText().isEmpty()){
			return false;
		}

		if(street.getText().isEmpty()){
			return false;
		}

		if(city.getText().isEmpty()){
			return false;
		}

		if(state.getText().isEmpty()){
			return false;
		}

		if(zip.getText().isEmpty()){
			return false;
		}
		if(bio.getText().isEmpty()){
			return false;
		}


		return true;
	}

}
;