package hse.btf.pdfeditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloSceneController {
    @FXML
    private Button createButton;

    @FXML
    void createProjectButton(ActionEvent event) throws IOException {
        // closing previous
        Stage thisStage = (Stage) createButton.getScene().getWindow();
        thisStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("work-window.fxml"));

        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setMaximized(false);
        stage.initStyle(StageStyle.DECORATED);

        stage.setTitle("Working Window");
        stage.setScene(scene);
        stage.show();
    }
}