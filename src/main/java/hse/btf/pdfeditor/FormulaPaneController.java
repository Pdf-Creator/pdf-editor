package hse.btf.pdfeditor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FormulaPaneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setFormulaActions();
    }

    private void setFormulaActions() {

    }

    @FXML
    public Button newFormulaButton;
}
