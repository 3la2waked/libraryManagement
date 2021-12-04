package business;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import ui.MainWindow;
import ui.Start;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckoutController {

    private Book _book;
    private LibraryMember _libraryMember;

    @FXML
    private TextField memberIdTF;

    @FXML
    private ChoiceBox<LibraryMember> memberIdCB;

    @FXML
    private TextField isbnTF;

    @FXML
    private ChoiceBox<Book> isbnCB;

    @FXML
    private Text errorTxt;

    @FXML
    private TableView<CheckoutRecordEntry> table;

    @FXML
    public void initialize() {
        DataAccess dataAccess = new DataAccessFacade();
        List<LibraryMember> members = new ArrayList<>(dataAccess.readMemberMap().values());
        List<Book> books = new ArrayList<>(dataAccess.readBooksMap().values());
        memberIdCB.getItems().addAll(members);
        isbnCB.getItems().addAll(books);

        memberIdCB.setOnAction(actionEvent -> {
            LibraryMember member = memberIdCB.getSelectionModel().getSelectedItem();
            memberIdTF.setText(member.getMemberId());
        });

        isbnCB.setOnAction(actionEvent -> {
            Book book = isbnCB.getSelectionModel().getSelectedItem();
            isbnTF.setText(book.getIsbn());
        });
    }

    @FXML
    public void checkoutBook() {
        table.setVisible(false);
        table.getItems().clear();
        table.getColumns().clear();

        if (isValid()) {
            errorTxt.setText(null);
            DataAccess dataAccess = new DataAccessFacade();
            CheckoutRecordEntry entry = dataAccess.checkoutBookCopy(_book, _libraryMember);

            ObservableList<CheckoutRecordEntry> data = FXCollections.observableList(new ArrayList<>());
            data.add(entry);
            table.setItems(data);

            TableColumn<CheckoutRecordEntry, String> bookIsbnCol = new TableColumn<>();
            bookIsbnCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBookCopy().getBook().getIsbn()));
            bookIsbnCol.setText("ISBN");

            TableColumn<CheckoutRecordEntry, String> bookTitleCol = new TableColumn<>();
            bookTitleCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBookCopy().getBook().getTitle()));
            bookTitleCol.setText("Book Title");

            TableColumn<CheckoutRecordEntry, String> bookCopyCol = new TableColumn<>();
            bookCopyCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("" + cellData.getValue().getBookCopy().getCopyNum()));
            bookCopyCol.setText("Copy #");

            TableColumn<CheckoutRecordEntry, String> checkoutDateCol = new TableColumn<>();
            checkoutDateCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCheckoutDate().format(DateTimeFormatter.ofPattern("MM/dd/yyy"))));
            checkoutDateCol.setText("Checkout Date");

            TableColumn<CheckoutRecordEntry, String> dueDateCol = new TableColumn<>();
            dueDateCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDueDate().format(DateTimeFormatter.ofPattern("MM/dd/yyy"))));
            dueDateCol.setText("Due Date");

            table.getColumns().addAll(bookIsbnCol, bookTitleCol, bookCopyCol, checkoutDateCol, dueDateCol);
            table.setVisible(true);
        }
    }

    @FXML
    public void cancel() {
        Start.hideAllWindows();
        if (!MainWindow.INSTANCE.isInitialized())
            MainWindow.INSTANCE.init();
        MainWindow.INSTANCE.show();
    }

    private boolean isValid() {
        if (null == memberIdTF.getText() || memberIdTF.getText().trim().isEmpty()) {
            errorTxt.setText("Fields with * are required!");
            return false;
        }

        if (null == isbnTF.getText() || isbnTF.getText().trim().isEmpty()) {
            errorTxt.setText("Fields with * are required!");
            return false;
        }

        String memberId = memberIdTF.getText().trim();
        String isbn = isbnTF.getText().trim();

        DataAccess dataAccess = new DataAccessFacade();

        Book book = dataAccess.readBooksMap().get(isbn);
        LibraryMember libraryMember = dataAccess.readMemberMap().get(memberId);

        if (null == book) {
            errorTxt.setText("A book with isbn " + isbn + " does not exist!");
            return false;
        }

        if (!book.isAvailable()) {
            errorTxt.setText("The book with isbn " + isbn + " is currently unavailable!");
            return false;
        }

        if (null == libraryMember) {
            errorTxt.setText("A library member with member ID " + memberId + " does not exist!");
            return false;
        }

        _book = book;
        _libraryMember = libraryMember;
        return true;
    }
}
