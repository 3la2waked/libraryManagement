package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CheckoutRecordWindow extends Stage implements LibWindow {
	public static final CheckoutRecordWindow INSTANCE = new CheckoutRecordWindow();
@FXML
	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	/* This class is a singleton */
    private CheckoutRecordWindow() {}

    public void init() {

		try {

			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/CheckoutRecord.fxml")));
			Scene scene = new Scene(root, 406, 162);
			setScene(scene);
			isInitialized(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
