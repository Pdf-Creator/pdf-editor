package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.FormulaEntity;
import hse.btf.pdfeditor.models.TextEntity;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;

public class FormulaPaneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFormulaActions();
    }

    private void setFormulaActions() {
        newFormulaButton.setOnMouseClicked(ev -> {
            FormulaEntity entity = new FormulaEntity();
            entity.setString("\\frac{1}{\\sum 3}");
            entity.setBottomPadding(8.0);
            entity.setTopPadding(8.0);
            entity.setLeftPadding(8.0);
            entity.setRightPadding(8.0);
            entity.setWidth(150);
            entity.setHeight(90);
            papers.get(0).getChildren().add(entity.createFxmlObject());
            papers.get(0).getStylesheets().add(PdfEditorApplication.class.getResource("main.css").toExternalForm());
        });
    }

    @FXML
    public Button newFormulaButton;
}
