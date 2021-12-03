package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainWindow extends Stage implements LibWindow {
    public static final MainWindow INSTANCE = new MainWindow();

    private boolean isInitialized = false;

    private MainWindow() {}

    @Override
    public void init() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ui/MainScreen.fxml")));
            Scene scene = new Scene(root, 460, 400);
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
