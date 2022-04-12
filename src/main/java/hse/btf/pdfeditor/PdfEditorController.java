package hse.btf.pdfeditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.NumberFormat;

public class PdfEditorController {
    @FXML
    private Button createButton;

    @FXML
    void createProjectButton(ActionEvent event) throws IOException {
        Stage thisStage = (Stage) createButton.getScene().getWindow();
        thisStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("work-window.fxml"));

        var stage = new Stage();
        stage.setTitle("Working Window");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setMaximized(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }

}