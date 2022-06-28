package hse.btf.pdfeditor;

import hse.btf.pdfeditor.utils.FontUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PdfEditorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PdfEditorApplication.class.getResource("hello-view.fxml"));
        FontUtil.init();

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}