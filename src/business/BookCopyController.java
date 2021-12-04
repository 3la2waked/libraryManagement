package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ui.MainWindow;
import ui.Start;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookCopyController {

    @FXML
    private TextField isbnTF;

    @FXML
    private TableView<Book> table;

    @FXML
    private Text errorTxt;

    private ObservableList<Book> oBooks;

    @FXML
    public void initialize() {
        DataAccess dataAccess = new DataAccessFacade();
        HashMap<String, Book> books = dataAccess.readBooksMap();
        oBooks = FXCollections.observableList(new ArrayList<>(books.values()));
        updateTable();
    }

    public void addBookCopy() {
        errorTxt.setText(null);
        Book book = table.getSelectionModel().getSelectedItem();
        if (null == book)
            errorTxt.setText("No book is currently selected!");
        else {
            book.addCopy();
            DataAccess dataAccess = new DataAccessFacade();
            dataAccess.saveNewBook(book);
            search();
        }
    }

    @FXML
    public void cancel() {
        Start.hideAllWindows();
        if (!MainWindow.INSTANCE.isInitialized())
            MainWindow.INSTANCE.init();
        MainWindow.INSTANCE.show();
    }

    @FXML
    public void search() {
        errorTxt.setText(null);
        String term = isbnTF.getText();
        DataAccess dataAccess = new DataAccessFacade();
        HashMap<String, Book> books = dataAccess.readBooksMap();

        if (!term.isEmpty()) {
            List<Book> bookList = new ArrayList<>();
            if (!books.isEmpty() && books.containsKey(term)) {
                bookList.add(books.get(term));
            }
            oBooks = FXCollections.observableList(bookList);
        }
        else {
            oBooks = FXCollections.observableList(new ArrayList<>(books.values()));
        }
        updateTable();
    }

    private void updateTable() {
        table.getItems().clear();
        table.getColumns().clear();
        table.setItems(oBooks);

        TableColumn<Book, String> isbnCol = new TableColumn<>();
        isbnCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIsbn()));
        isbnCol.setText("ISBN");

        TableColumn<Book, String> titleCol = new TableColumn<>();
        titleCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
        titleCol.setText("Title");

        TableColumn<Book, String> maxCOULengthCol = new TableColumn<>();
        maxCOULengthCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getMaxCheckoutLength()));
        maxCOULengthCol.setText("Max Checkout Length");

        TableColumn<Book, String> numCopiesCol = new TableColumn<>();
        numCopiesCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getCopies().length));
        numCopiesCol.setText("No. of Copies");

        table.getColumns().addAll(isbnCol, titleCol, maxCOULengthCol, numCopiesCol);
    }
}
