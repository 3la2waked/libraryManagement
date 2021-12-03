package ui;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddMemberWindow extends Stage implements LibWindow {
	public static final AddMemberWindow INSTANCE = new AddMemberWindow();
@FXML
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
    private AddMemberWindow() {}

    public void init() {

		try {

			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/AddMember.fxml")));
			Scene scene = new Scene(root, 500, 500);
			setScene(scene);
			isInitialized(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
