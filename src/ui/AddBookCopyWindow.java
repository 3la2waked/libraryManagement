package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddBookCopyWindow extends Stage implements LibWindow {

    public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();

    @FXML
    private boolean isInitialized = false;

    @Override
    public void init() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/AddBookCopy.fxml")));
            Scene scene = new Scene(root, 600, 400);
            setScene(scene);
            isInitialized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }
}
