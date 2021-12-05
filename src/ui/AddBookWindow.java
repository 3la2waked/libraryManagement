package ui;

import business.AddBookController;
import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddBookWindow extends Stage implements LibWindow {
	public static final AddBookWindow INSTANCE = new AddBookWindow();

	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private Text messageBar = new Text();
	public void clear() {
		messageBar.setText("");
	}

	/* This class is a singleton */
    private AddBookWindow() {}

	public void init() {

		try {

			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/AddBook.fxml")));
			Scene scene = new Scene(root, 891, 511);
			setScene(scene);
			isInitialized(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
/*	public void init() {
		GridPane grid = new GridPane();
		grid.setId("top-container");
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Add Book");
		scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
		grid.add(scenetitle, 0, 0, 2, 1);

		Label isbn = new Label("ISBN:");
		grid.add(isbn, 0, 1);

		TextField isbnTextField = new TextField();
		grid.add(isbnTextField, 1, 1);

		Label title = new Label("Title:");
		grid.add(title, 0, 2);

		TextField titleTextField = new TextField();
		grid.add(titleTextField, 1, 2);

		Label maxCheckoutLength = new Label("Max Checkout Length:");
		grid.add(maxCheckoutLength, 0, 3);

		TextField maxCheckoutLengthTextField = new TextField();
		grid.add(maxCheckoutLengthTextField, 1, 3);

		Label copies = new Label("Copies:");
		grid.add(copies, 0, 4);

		TextField copiesTextField = new TextField();
		grid.add(copiesTextField, 1, 4);

		Label authors = new Label("Authors:");
		grid.add(authors, 0, 5);

		GridPane authersPanel= new GridPane();
		grid.add(authersPanel,1,5);

		Label test = new Label("ddddd");
		authersPanel.add(test,0,0);

		Button loginBtn = new Button("Save");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(loginBtn);
		grid.add(hbBtn, 1, 6);

		HBox messageBox = new HBox(10);
		messageBox.setAlignment(Pos.BOTTOM_RIGHT);
		messageBox.getChildren().add(messageBar);;
		grid.add(messageBox, 1, 7);

		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				AddBookController c = new AddBookController();
				//c.add(userTextField.getText().trim(), pwBox.getText().trim());
				Start.hideAllWindows();
				MainWindow.INSTANCE.init();
				MainWindow.INSTANCE.show();

			}
		});

		Button backBtn = new Button("<= Back to Main");
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Start.hideAllWindows();
				MainWindow.INSTANCE.init();
				MainWindow.INSTANCE.show();
			}
		});
		HBox hBack = new HBox(10);
		hBack.setAlignment(Pos.BOTTOM_LEFT);
		hBack.getChildren().add(backBtn);
		grid.add(hBack, 0, 7);
		Scene scene = new Scene(grid);
		scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
		setScene(scene);

	}*/



}
