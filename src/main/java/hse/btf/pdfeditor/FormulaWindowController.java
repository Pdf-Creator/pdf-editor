package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.FormulaEntity;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import kotlin.jvm.Synchronized;

import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;

public class FormulaWindowController implements Initializable {
    public TextArea formulaText;
    public Button createFormula;
    public Button closeFormula;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createFormula.setOnAction(ev -> {
            FormulaEntity entity = new FormulaEntity();
            entity.setString(formulaText.getText());
            entity.setBottomPadding(8.0);
            entity.setTopPadding(8.0);
            entity.setLeftPadding(8.0);
            entity.setRightPadding(8.0);
            entity.setWidth(150);
            entity.setHeight(90);
            papers.get(0).getChildren().add(entity.createFxmlObject());
            papers.get(0).getStylesheets().add(PdfEditorApplication.class.getResource("main.css").toExternalForm());
            Stage stage = (Stage)formulaText.getScene().getWindow();
            stage.close();
        });

        closeFormula.setOnAction(ev -> {
            Stage stage = (Stage)formulaText.getScene().getWindow();
            stage.close();
        });
    }
}
