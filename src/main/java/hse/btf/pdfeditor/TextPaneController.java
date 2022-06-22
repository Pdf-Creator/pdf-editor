package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.TextItem;
import hse.btf.pdfeditor.utils.CreatorConstants;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;
import static hse.btf.pdfeditor.utils.Singleton.itemsHolder;

public class TextPaneController implements Initializable {
    private static class Position {
        double x;
        double y;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextFieldActions();

        newTextButton.setOnMouseClicked(ev -> {
            itemsHolder.getObservableItemsList().add(new TextItem());

            if (PdfEditorView.lastAddedNode instanceof TextArea) {
                TextArea textArea = (TextArea) PdfEditorView.lastAddedNode;
                AnchorPane textBox = PdfEditorView.lastAddedAnchorPane;
                assert textBox != null;

                setTextAreaSettings(textArea, textBox);
            } else {
                System.out.println("Something went wrong, when textArea was creating");
            }
        });
    }

    public void setTextAreaSettings(TextArea text, AnchorPane textBox) {
        textBox.setPrefWidth(150);
        textBox.setPrefHeight(90);

        text.setWrapText(true);

        Circle resizePoint = new Circle(6, Color.WHITE);
        resizePoint.setStrokeWidth(1);
        resizePoint.setStrokeType(StrokeType.INSIDE);
        resizePoint.setStroke(Color.valueOf("0x808080FF"));

        AnchorPane.setRightAnchor(resizePoint, -6.0);
        AnchorPane.setBottomAnchor(resizePoint, -6.0);

        AnchorPane.setTopAnchor(text, 8.0);
        AnchorPane.setLeftAnchor(text, 8.0);
        AnchorPane.setRightAnchor(text, 8.0);
        AnchorPane.setBottomAnchor(text, 8.0);

        textBox.getChildren().add(text);
        textBox.getChildren().add(resizePoint);

        textBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> textBox.setCursor(Cursor.HAND));
        textBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> textBox.setCursor(Cursor.DEFAULT));

        final Position pos = new Position();

        textBox.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            textBox.setCursor(Cursor.MOVE);
            pos.x = e.getX();
            pos.y = e.getY();
        });

        textBox.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> textBox.setCursor(Cursor.DEFAULT));

        textBox.setOnMouseDragged(e -> {
            double distanceX = e.getX() - pos.x;
            double distanceY = e.getY() - pos.y;

            double x = textBox.getLayoutX() + distanceX;
            double y = textBox.getLayoutY() + distanceY;

            textBox.relocate(x, y);
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            textBox.setCursor(Cursor.SE_RESIZE);
            event.consume();
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            textBox.setCursor(Cursor.DEFAULT);
            event.consume();
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            pos.x = e.getX();
            pos.y = e.getY();
            e.consume();
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            textBox.setCursor(Cursor.DEFAULT);
            event.consume();
        });

        resizePoint.setOnMouseDragged(e -> {
            double distanceX = e.getX() - pos.x;
            double distanceY = e.getY() - pos.y;

            double x = textBox.getPrefWidth() + distanceX;
            double y = textBox.getPrefHeight() + distanceY;

            textBox.setPrefWidth(x);
            textBox.setPrefHeight(y);
            e.consume();
        });

        papers.get(0).getStylesheets().add(PdfEditorApplication.class.getResource("main.css").toExternalForm());
        textBox.getStyleClass().add("text-region");
        papers.get(0).getChildren().add(textBox);
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