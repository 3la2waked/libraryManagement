package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CopyOverdueWindow extends Stage implements LibWindow {
	public static final CopyOverdueWindow INSTANCE = new CopyOverdueWindow();
	@FXML
	private boolean isInitialized = false;

	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	/* This class is a singleton */
    private CopyOverdueWindow() {}

    public void init() {

		try {
			Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/CopyOverdue.fxml")));
			Scene scene = new Scene(root, 600, 500);
			setScene(scene);
			isInitialized(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
