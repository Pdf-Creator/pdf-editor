package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

import static hse.btf.pdfeditor.Singleton.observableList;

public class WorkSceneController {
    private double lastDraggedX = -1;
    private double lastDraggedY = -1;
    private boolean MAXIMIZED = true;

    // field center
    private static final int centerX = 388;
    private static final int centerY = 272;

    // rectangle
    private static final int defaultRecWidth = 166;
    private static final int defaultRecHeight = 119;

    @FXML
    private Pane layout;

    // window buttons
    @FXML
    private Button closeButton;
    @FXML
    private Button hideButton;
    @FXML
    private Button minButton;

    // upper bar
    @FXML
    private MenuBar stageControlBar;

    // right bar buttons
    @FXML
    private Button textButton;
    @FXML
    private Button formulaButton;
    @FXML
    private Button imageButton;
    @FXML
    private Button tableButton;
    @FXML
    private Button headingButton;
    @FXML
    private Button listButton;

    // pdf buttons
    @FXML
    private Button saveChangesButton;
    @FXML
    private Button convertToPdfButton;

    // left bar buttons
    private List<Button> leftBarButtons = new ArrayList<>();
    private List<Button> rightBarButtons = new ArrayList<>();

    @FXML
    private Rectangle paperRectangle;

    private List<? extends Item> paperObjectsList = new ArrayList<>();

    @FXML
    public void initialize() {
        createRightBarButtons();
        createPdfButtons();
    }

    @FXML
    void deleteLeftBarButtons() {
        for (Button button : leftBarButtons) {
            observableList.remove(button);
        }
        leftBarButtons.clear();
    }

    @FXML
    void changeLeftBarToTextButtons() {
        deleteLeftBarButtons();
        // задать свои кнопки в левой панели
    }

    @FXML
    void changeLeftBarToFormulaButtons() {
        deleteLeftBarButtons();
        // задать свои кнопки в левой панели
    }

    @FXML
    void changeLeftBarToTableButtons() {
        deleteLeftBarButtons();
        // задать свои кнопки в левой панели
    }

    @FXML
    void changeLeftBarToHeadingButtons() {
        deleteLeftBarButtons();
        // задать свои кнопки в левой панели
    }

    @FXML
    void changeLeftBarToListButtons() {
        deleteLeftBarButtons();
        // задать свои кнопки в левой панели
    }

    @FXML
    Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle();

        rectangle.setX(centerX);
        rectangle.setY(centerY);

        rectangle.setWidth(defaultRecWidth);
        rectangle.setHeight(defaultRecHeight);

        // adding to the layout
        //observableList.add(rectangle);
        layout.getChildren().add(rectangle);

        return rectangle;
    }

    @FXML
    public void createTextField(ActionEvent event) {
        // изменить кнопки в левой панели
        //changeLeftBarToTextButtons();

        // создать в центре paper'а прямоугольник с возможностью добавления текста
        Rectangle workingRec = createRectangle();
    }

    @FXML
    public void createFormulaField(ActionEvent event) {
        //changeLeftBarToFormulaButtons();

        //Rectangle workingRec = createRectangle();
    }

    @FXML
    public void createImageField(ActionEvent event) {
        //changeLeftBarToFormulaButtons();

        //Rectangle workingRec = createRectangle();
    }

    @FXML
    void createTableField(ActionEvent event) {
        changeLeftBarToTableButtons();

        Rectangle workingRec = createRectangle();
    }

    @FXML
    void createHeadingField(ActionEvent event) {
        changeLeftBarToHeadingButtons();

        Rectangle workingRec = createRectangle();
    }

    @FXML
    void createListField(ActionEvent event) {
        changeLeftBarToListButtons();

        Rectangle workingRec = createRectangle();
    }

    Button createRightBarButton(String buttonName, int number, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(buttonName);

        // поработать над изменением внешнего вида кнопок -- css
        Font buttonFont = Font.font("Arial", 13);
        button.setText(buttonName);
        button.setFont(buttonFont);

        double buttonsX = 756;
        double buttonsY = 50;
        double buttonsW = 218;
        double buttonsH = 64;
        double buttonsDelta = 30;

        // position
        button.setLayoutX(number <= 6 ? buttonsX : buttonsX + (buttonsW - buttonsDelta) / 2 + buttonsDelta);
        button.setLayoutY(number <= 6 ? buttonsY + 80 * number : buttonsY + 80 * 6);

        button.setPrefWidth(number <= 5 ? buttonsW : (buttonsW - buttonsDelta) / 2);
        button.setPrefHeight(buttonsH);

        // event handler
        button.setOnAction(eventHandler);

        // adding to list
        rightBarButtons.add(button);

        // adding to layout
        //observableList.add(button);

        layout.getChildren().add(button);
        return button;
    }

    private void createRightBarButtons() {
        textButton = createRightBarButton("Text", 0, this::createTextField);
        formulaButton = createRightBarButton("Formula", 1, this::createFormulaField);
        imageButton = createRightBarButton("Image", 2, this::createImageField);
        tableButton = createRightBarButton("Table", 3, this::createTableField);
        headingButton = createRightBarButton("Heading", 4, this::createHeadingField);
        listButton = createRightBarButton("List", 5, this::createListField);
    }

    private void createPdfButtons(){
        convertToPdfButton = createRightBarButton("Save", 6, null);
        saveChangesButton = createRightBarButton("Create Pdf", 7, null);
    }

    @FXML
    public void stagedDragged(MouseEvent event) {
        var dx = event.getScreenX() - lastDraggedX;
        var dy = event.getScreenY() - lastDraggedY;
        Window thisWindow = stageControlBar.getScene().getWindow();
        if (Math.abs(dx) > 30 || Math.abs(dy) > 30) {
            lastDraggedX = event.getScreenX();
            lastDraggedY = event.getScreenY();
        } else {
            thisWindow.setX(thisWindow.getX() + dx);
            thisWindow.setY(thisWindow.getY() + dy);
            lastDraggedX = event.getScreenX();
            lastDraggedY = event.getScreenY();

            Stage thisStage = (Stage) thisWindow;
            thisStage.setMaximized(false);
        }
    }

    @FXML
    public void closeWindow(ActionEvent event) {
        // Мб лучше хранить сцену как поле?
        Stage thisStage = (Stage) closeButton.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    public void hideWindow(ActionEvent event) {
        Stage thisStage = (Stage) hideButton.getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    public void minWindow(ActionEvent event) {
        Stage thisStage = (Stage) hideButton.getScene().getWindow();
        MAXIMIZED = !MAXIMIZED;
        thisStage.setMaximized(MAXIMIZED);
    }
}