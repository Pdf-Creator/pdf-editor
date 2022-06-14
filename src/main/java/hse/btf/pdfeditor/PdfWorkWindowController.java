package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.TableItem;
import hse.btf.pdfeditor.models.TextItem;
import hse.btf.pdfeditor.utils.CreatorConstants;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.Singleton.itemsHolder;

public class PdfWorkWindowController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPaperSettings();
        setLeftPanelsActions();

        pdfEditorView = new PdfEditorView(paper);
    }

    private void setPaperSettings() {
        paperSize = 100;
        originalPaperWidth = paper.getPrefWidth();
        originalPaperHeight = paper.getPrefHeight();

        PaperContextMenu contextMenu = new PaperContextMenu();
        paper.setOnMouseClicked(ev -> {
            if (ev.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(paper, ev.getScreenX(), ev.getScreenY());
            } else {
                contextMenu.hide();
            }
        });
    }

    private void setLeftPanelsActions() {
        leftPanel.setPrefWidth(20);

        textPane.setPrefWidth(0);
        tablePane.setPrefWidth(0);
        imagePane.setPrefWidth(0);
        formulaPane.setPrefWidth(0);

        addLeftButtonClicker(leftTextPaneButton, textPane);
        addLeftButtonClicker(leftTablePaneButton, tablePane);
        addLeftButtonClicker(leftImagePaneButton, imagePane);
        addLeftButtonClicker(leftFormulaPaneButton, formulaPane);

        setTextFieldActions();
        setTableActions();
        setImageActions();
        setFormulaActions();
    }

    private void setTextFieldActions() {
        // new text field button
        newTextButton.setOnMouseClicked(ev ->
                itemsHolder.getObservableItemsList().add(new TextItem())
        );

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

        // добавить отражение этих действий на selectedField

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

    private void setTableActions() {
        newTableButton.setOnMouseClicked(ev ->
                itemsHolder.getObservableItemsList().add(new TableItem())
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

    private void setImageActions() {

    }

    private void setFormulaActions() {

    }

    private void connectSliderWithTextField(Slider slider, TextField field) {
        slider.valueProperty().addListener((changed, oldValue, newValue) -> field.setText(newValue.toString()));
        field.textProperty().addListener((changed, oldValue, newValue) -> slider.setValue(Double.parseDouble(newValue)));
    }

/*
    TODO : ultimate way to add handlers
    private void initFormulaPane() {
        formulaPane.getChildren().forEach(node -> {
            node.setOnMouseClicked();
        });
    }
*/

    private void addLeftButtonClicker(Button button, AnchorPane panel) {
        button.setOnMouseClicked(ev -> {
            if (selectedPane == null) {
                selectedPane = panel;
                leftPanel.setPrefWidth(164);
                selectedPane.setPrefWidth(144);
            } else {
                selectedPane.setPrefWidth(0);

                if (selectedPane == panel) {
                    selectedPane = null;
                    leftPanel.setPrefWidth(20);
                } else {
                    selectedPane = panel;
                    selectedPane.setPrefWidth(144);
                }
            }
        });
    }

    @FXML
    public void paperSizeDecrease(MouseEvent event) {
        if (paperSize != 25) {
            paperSize -= 25;
            sizeLabel.setText(paperSize + "%");
            resizePaper();
        }
    }

    @FXML
    public void paperSizeIncrease(MouseEvent event) {
        if (paperSize != 100) {
            paperSize += 25;
            sizeLabel.setText(paperSize + "%");
            resizePaper();
        }
    }

    private void resizePaper() {
        paper.setPrefWidth(originalPaperWidth / 100 * paperSize);
        paper.setPrefHeight(originalPaperHeight / 100 * paperSize);
    }

    /**
     * left panel
     **/
    @FXML
    public AnchorPane leftPanel;

    /**
     * choosing pane buttons
     **/
    @FXML
    public Button leftTextPaneButton;
    public Button leftTablePaneButton;
    public Button leftImagePaneButton;
    public Button leftFormulaPaneButton;

    /**
     * panes
     **/
    @FXML
    private AnchorPane selectedPane = null;

    @FXML
    public AnchorPane textPane;
    public AnchorPane tablePane;
    public AnchorPane imagePane;
    public AnchorPane formulaPane;

    /**
     * text pane's content
     **/
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

    private final ToggleGroup textPropertyToggleGroup = new ToggleGroup();

    @FXML
    public ColorPicker textColorPicker;
    public ColorPicker textFillColorPicker;

    @FXML
    public TextField lineSpacesField;
    public Slider lineSpacesSlider;

    @FXML
    public ColorPicker textFieldColorPicker;

    private TextArea selectedTextArea;

    /**
     * table pane's content
     **/
    @FXML
    public Button newTableButton;

    @FXML
    public TextField tableColsField;
    public Slider tableColsSlider;
    public TextField tableRowsField;
    public Slider tableRowsSlider;

    /**
     * image pane's content
     **/
    @FXML
    public Button newImageButton;

    /**
     * formula pane's content
     **/
    @FXML
    public Button newFormulaButton;

    /**
     * paper
     **/
    @FXML
    private AnchorPane paperBackground;

    @FXML
    private AnchorPane paper;

    @FXML
    private Button buttonPaperSizeIncrease;

    @FXML
    private Button buttonPaperSizeDecrease;

    @FXML
    private Label sizeLabel;

    private PdfEditorView pdfEditorView;

    private double originalPaperWidth;

    private double originalPaperHeight;

    private Integer paperSize;
}