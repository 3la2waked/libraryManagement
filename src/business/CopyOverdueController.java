package business;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ui.MainWindow;
import ui.Start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyOverdueController {

	@FXML
	private Text message;
	@FXML
	private TextField isbn;
	@FXML
	private TableView<BookCopy> tableView;

	public void initialize() {

	}
	public void back(){
		Start.hideAllWindows();
		MainWindow.INSTANCE.init();
		MainWindow.INSTANCE.show();
	}

	public void search(){
		DataAccess da = new DataAccessFacade();

		Book book = da.readBooksMap().get(isbn.getText());

		BookCopy[] copies= book.getCopies();
		ObservableList<BookCopy> bc = FXCollections.observableList(new ArrayList<>(Arrays.asList(copies)));
		updateTable(bc);


		if(copies.length==0){
			System.out.println("No Checkout Record Found");
		}


	}

	private void updateTable(ObservableList<BookCopy> bc) {
		tableView.getItems().clear();
		tableView.getColumns().clear();
		tableView.setItems(bc);

		TableColumn<BookCopy, String> isbnCol = new TableColumn<>();
		isbnCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBook().getIsbn()));
		isbnCol.setText("ISBN");

		TableColumn<BookCopy, String> titleCol = new TableColumn<>();
		titleCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBook().getTitle()));
		titleCol.setText("Title");

		TableColumn<BookCopy, String> maxCOULengthCol = new TableColumn<>();
		maxCOULengthCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getBook().getMaxCheckoutLength()));
		maxCOULengthCol.setText("Max Checkout Length");

		TableColumn<BookCopy, String> numCopiesCol = new TableColumn<>();
		numCopiesCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getCopyNum()));
		numCopiesCol.setText("Copy Number");

		TableColumn<BookCopy, String> member = new TableColumn<>();
		numCopiesCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getBook()));
		numCopiesCol.setText("Copy Number");

		tableView.getColumns().addAll(isbnCol, titleCol, maxCOULengthCol, numCopiesCol);
	}


	
	
}
