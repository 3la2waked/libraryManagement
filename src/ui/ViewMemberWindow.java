package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewMemberWindow extends Stage implements LibWindow {
	public static final ViewMemberWindow INSTANCE = new ViewMemberWindow();
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
    private ViewMemberWindow() {}

    public void init() {

		try {

			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/ViewMember.fxml")));
			Scene scene = new Scene(root, 500, 500);
			setScene(scene);
			isInitialized(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
