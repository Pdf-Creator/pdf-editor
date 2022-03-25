package hse.btf.pdfeditor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PdfEditorController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}