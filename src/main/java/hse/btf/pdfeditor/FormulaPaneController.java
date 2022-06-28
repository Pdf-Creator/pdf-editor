package hse.btf.pdfeditor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FormulaPaneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFormulaActions();
    }

    synchronized private void setFormulaActions() {
        newFormulaButton.setOnMouseClicked(ev -> {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(PdfEditorApplication.class.getResource("formula-window.fxml"));

            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @FXML
    public Button newFormulaButton;
}
