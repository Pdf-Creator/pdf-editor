package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.entities.TextEntity;
import hse.btf.pdfeditor.utils.CreatorConstants;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;
import static hse.btf.pdfeditor.utils.DataStorage.entitiesList;

public class TextPaneController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextFieldActions();


        newTextButton.setOnMouseClicked(ev -> {
            TextEntity entity = new TextEntity();
            entitiesList.add(entity);

            // TODO: вынести код -- повторяется в formula pane
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

    private void setTextFieldActions() {
        // font
        textShriftChoiceBox.setItems(FXCollections.observableList(CreatorConstants.FONTS));
        textShriftChoiceBox.setValue(CreatorConstants.DEFAULT_FONT);
        textShriftChoiceBox.setTooltip(new Tooltip("Select text font"));

        // text size
        connectSliderWithTextField(textSizeSlider, textSizeField);

        textSizeSlider.setMin(5.0);
        textSizeSlider.setMax(72.0);
        textSizeField.setText("13.0");

        // alignment
        alignmentLeftButton.setToggleGroup(alignmentToggleGroup);
        alignmentCenterButton.setToggleGroup(alignmentToggleGroup);
        alignmentRightButton.setToggleGroup(alignmentToggleGroup);
        alignmentWidthButton.setToggleGroup(alignmentToggleGroup);

        // TODO: сделать стартовым выравнивание по левому краю + чтобы всегда хотя бы одно выравнивание было

        // TODO: добавить отражение этих действий на selectedField

        // text properties

        // text color
        textColorPicker.setValue(Color.BLACK);
        textFillColorPicker.setValue(Color.WHITE);

        // line spaces
        connectSliderWithTextField(lineSpacesSlider, lineSpacesField);

        lineSpacesSlider.setMin(1.0);
        lineSpacesSlider.setMax(3.0);
        lineSpacesField.setText("1.0");

        // field color
        textFillColorPicker.setValue(Color.WHITE);
    }

    private void connectSliderWithTextField(Slider slider, TextField field) {
        slider.valueProperty().addListener((changed, oldValue, newValue) -> field.setText(newValue.toString()));
        field.textProperty().addListener((changed, oldValue, newValue) -> slider.setValue(Double.parseDouble(newValue)));
    }

    @FXML
    public Button newTextButton;

    @FXML
    public ChoiceBox<String> textShriftChoiceBox;

    @FXML
    public TextField textSizeField;
    public Slider textSizeSlider;

    @FXML
    public ToggleButton alignmentLeftButton;
    public ToggleButton alignmentCenterButton;
    public ToggleButton alignmentRightButton;
    public ToggleButton alignmentWidthButton;

    private final ToggleGroup alignmentToggleGroup = new ToggleGroup();

    @FXML
    public ToggleButton textPropertyBoldButton;
    public ToggleButton textPropertyCursiveButton;
    public ToggleButton textPropertyUnderlineButton;
    public ToggleButton textPropertyCrossOutButton;

    @FXML
    public ColorPicker textColorPicker;
    public ColorPicker textFillColorPicker;

    @FXML
    public TextField lineSpacesField;
    public Slider lineSpacesSlider;

    @FXML
    public ColorPicker textFieldColorPicker;
}