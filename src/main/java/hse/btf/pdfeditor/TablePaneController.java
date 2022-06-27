package hse.btf.pdfeditor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TablePaneController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableActions();
    }

    private void setTableActions() {
        newTableButton.setOnMouseClicked(ev -> {
                }
                //entitiesHolder.getObservableItemsList().add(new TableItem())
        );

        // columns
        connectSliderWithTextField(tableColsSlider, tableColsField);

        tableColsSlider.setMin(1.0);
        tableColsSlider.setMax(20.0);
        tableColsField.setText("2.0");

        // rows
        connectSliderWithTextField(tableRowsSlider, tableRowsField);

        tableRowsSlider.setMin(1.0);
        tableRowsSlider.setMax(20.0);
        tableRowsField.setText("2.0");
    }

    private void connectSliderWithTextField(Slider slider, TextField field) {
        slider.valueProperty().addListener((changed, oldValue, newValue) -> field.setText(newValue.toString()));
        field.textProperty().addListener((changed, oldValue, newValue) -> slider.setValue(Double.parseDouble(newValue)));
    }

    @FXML
    public Button newTableButton;

    @FXML
    public TextField tableColsField;
    public Slider tableColsSlider;
    public TextField tableRowsField;
    public Slider tableRowsSlider;
}
