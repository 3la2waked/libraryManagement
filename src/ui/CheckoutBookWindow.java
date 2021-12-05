package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CheckoutBookWindow extends Stage implements LibWindow {

    public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();

    private boolean isInitialized = false;

    private CheckoutBookWindow() {}

    @Override
    public void init() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/CheckoutBook.fxml")));
            Scene scene = new Scene(root, 600, 444);
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
