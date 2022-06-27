package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.FormulaEntity;
import hse.btf.pdfeditor.models.TextEntity;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;

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
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
    }

    @FXML
    public Button newFormulaButton;
}
