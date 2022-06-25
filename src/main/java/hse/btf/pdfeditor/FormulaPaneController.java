package hse.btf.pdfeditor;

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

public class FormulaPaneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFormulaActions();
    }

    private void setFormulaActions() {

    }

    private ImageView drawFormula(String latex) {
        TeXFormula formula = new TeXFormula(latex);
        java.awt.Image awtImage = formula.createBufferedImage(TeXConstants.STYLE_TEXT, 12, Color.BLACK, null);
        Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);
        return new ImageView(fxImage);
    }

    @FXML
    public Button newFormulaButton;
}
