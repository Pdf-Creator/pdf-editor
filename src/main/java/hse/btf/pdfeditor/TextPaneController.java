package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.entities.TextEntity;
import hse.btf.pdfeditor.utils.CreatorConstants;
import hse.btf.pdfeditor.utils.FontUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;
import static hse.btf.pdfeditor.PdfWorkWindowController.target;
import static hse.btf.pdfeditor.utils.DataStorage.entitiesList;

public class TextPaneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textParameters.setVisible(false);
        newTextButton.setOnMouseClicked(ev -> {
            textParameters.setVisible(true);
            TextEntity entity = new TextEntity();
            entitiesList.add(entity);
            entity.setBottomPadding(8.0);
            entity.setTopPadding(8.0);
            entity.setLeftPadding(8.0);
            entity.setRightPadding(8.0);
            entity.setWidth(150);
            entity.setHeight(90);
            papers.get(0).getChildren().add(entity.createFxmlObject());
            papers.get(0).getStylesheets().add(Objects.requireNonNull(PdfEditorApplication.class.getResource("main.css")).toExternalForm());
        });

        textShriftChoiceBox.getItems().addAll(List.of("Free Sans", "Arial", "Times New Roman"));
        textShriftChoiceBox.setValue("Free Sans");
        textShriftChoiceBox.setOnAction(ev -> {
            TextEntity entity = (TextEntity)target;
            String fontName = textShriftChoiceBox.getValue();
            entity.setFont(fontName);
        });
    }

    @FXML
    public AnchorPane textParameters;

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
    public ColorPicker textFieldColorPicker;
}