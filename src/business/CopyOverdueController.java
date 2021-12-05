package business;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ui.MainWindow;
import ui.Start;

import java.time.format.DateTimeFormatter;
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
	@FXML
	private ChoiceBox<Book> isbnCB;

	public void initialize() {
		DataAccess dataAccess = new DataAccessFacade();
		List<Book> books = new ArrayList<>(dataAccess.readBooksMap().values());
		isbnCB.getItems().addAll(books);

		isbnCB.setOnAction(actionEvent -> {
			Book book = isbnCB.getSelectionModel().getSelectedItem();
			isbn.setText(book.getIsbn());
		});
	}
	public void back(){
		Start.hideAllWindows();
		MainWindow.INSTANCE.init();
		MainWindow.INSTANCE.show();
	}

	public void search(){
		DataAccess da = new DataAccessFacade();
		Book book = da.readBooksMap().get(isbn.getText());

		if (null == book) {
			message.setText("ISBN " + isbn.getText() + " does not exist!");
		}
		else {
			List<BookCopy> overdueCopies = book.getOverdueCopies();
			ObservableList<BookCopy> bc = FXCollections.observableList(overdueCopies);
			updateTable(bc);
		}
	}

	@FXML
	public void logout() {
		Start.hideAllWindows();
		Start.primStage().show();
	}

	private void updateTable(ObservableList<BookCopy> bc) {
		tableView.getItems().clear();
		tableView.getColumns().clear();
		tableView.setItems(bc);

		TableColumn<BookCopy, String> copyNoCol = new TableColumn<>();
		copyNoCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getCopyNum()));
		copyNoCol.setText("Copy Number");

		TableColumn<BookCopy, String> isbnCol = new TableColumn<>();
		isbnCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBook().getIsbn()));
		isbnCol.setText("ISBN");

		TableColumn<BookCopy, String> titleCol = new TableColumn<>();
		titleCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBook().getTitle()));
		titleCol.setText("Title");

		TableColumn<BookCopy, String> duedateCol = new TableColumn<>();
		duedateCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCheckoutRecordEntry().getDueDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
		duedateCol.setText("Due Date");

		TableColumn<BookCopy, String> memberCol = new TableColumn<>();
		memberCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getCheckoutRecordEntry().getCheckoutRecord().getLibraryMember().toString()));
		memberCol.setText("Library Member");

		tableView.getColumns().addAll(copyNoCol, isbnCol, titleCol, memberCol);
	}


	
	
}
