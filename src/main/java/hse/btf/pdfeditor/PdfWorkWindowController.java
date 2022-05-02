package hse.btf.pdfeditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

import static hse.btf.pdfeditor.Singleton.observableList;

public class PdfWorkWindowController {
    private double lastDraggedX = -1;
    private double lastDraggedY = -1;
    private boolean MAXIMIZED = true;

    // field center
    private static final int centerX = 388;
    private static final int centerY = 272;

    // rectangle
    private static final int defaultRecWidth = 166;
    private static final int defaultRecHeight = 119;

    // window buttons
    @FXML
    private Button closeButton;
    @FXML
    private Button hideButton;
    @FXML
    private Button minButton;

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

    // left bar buttons
    private List<Button> leftBarButtons = new ArrayList<>();
    private List<Button> rightBarButtons = new ArrayList<>();

    @FXML
    private Rectangle paperRectangle;

    private List<? extends RectangleField> paperObjectsList = new ArrayList<>();

    @FXML
    void deleteLeftBarButtons() {
        for (Button button : leftBarButtons) {
            // removing
            // private HBOx projectlist;
            // projectList.getChildren().remove(button);
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
        // center
        Rectangle rectangle = new Rectangle();
        rectangle.setX(centerX);
        rectangle.setY(centerY);

        // adding to the Scene

        return rectangle;
    }

    @FXML
    public void createTextField(ActionEvent event) {
        // изменить кнопки в левой панели
        //changeLeftBarToTextButtons();

        // создать в центре paper'а прямоугольник с возможностью добавления текста
        createRightBarButtons();
    }

    @FXML
    void createFormulaField(MouseEvent event) {
        //changeLeftBarToFormulaButtons();

        //Rectangle workingRec = createRectangle();
    }

    @FXML
    void createTableField(MouseEvent event) {
        changeLeftBarToTableButtons();

        Rectangle workingRec = createRectangle();
    }

    @FXML
    void createHeadingField(MouseEvent event) {
        changeLeftBarToHeadingButtons();

        Rectangle workingRec = createRectangle();
    }

    @FXML
    void createListField(MouseEvent event) {
        changeLeftBarToListButtons();

        Rectangle workingRec = createRectangle();
    }

    Button createRightBarButton(String buttonName, int number) {
        Button button = new Button();

        // поработать над изменением внешнего вида кнопок -- css
//        Font buttonFont = Font.font("Arial", 13);
//        button.setText(buttonName);
//        button.setFont(buttonFont);

        // position
        //button.setLayoutX(756);
        button.setLayoutX(100);
        button.setLayoutY(94 + 80 * number);

        // adding to list
        rightBarButtons.add(button);

//        observableList.add(button);
        return button;
    }

    void createRightBarButtons() {
        textButton = createRightBarButton("Text", 1);
        observableList.add(textButton);
//        formulaButton = createRightBarButton("Formula", 2);
//        imageButton = createRightBarButton("Image", 3);
//        tableButton = createRightBarButton("Table", 4);
//        headingButton = createRightBarButton("Heading", 5);
//        listButton = createRightBarButton("List", 6);
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