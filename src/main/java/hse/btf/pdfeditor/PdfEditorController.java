package hse.btf.pdfeditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PdfEditorController {
    @FXML
    private Button createButton;

    @FXML
    void createProjectButton(ActionEvent event) throws IOException {
        // closing previous
        Stage thisStage = (Stage) createButton.getScene().getWindow();
        thisStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("work-window.fxml"));

        // scene
        Scene scene = new Scene(fxmlLoader.load());

        // styling
        //String css = Sample.class.getResource("main.css").toExternalForm();
        //scene.getStylesheets().add(css);

        // creating stage
        Stage stage = new Stage();

        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("Working Window");
        stage.setScene(scene);
        stage.show();
    }
}