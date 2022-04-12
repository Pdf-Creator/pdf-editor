package hse.btf.pdfeditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

public class PdfWorkWindowController {
    private double lastDraggedX = -1;
    private double lastDraggedY = -1;
    private boolean MAXIMIZED = true;

    @FXML
    private Button closeButton;
    @FXML
    private Button hideButton;
    @FXML
    private Button minButton;

    @FXML
    private MenuBar stageControlBar;

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

    @FXML
    private Rectangle paperRectangle;

    private List<? extends RectangleField> paperObjectsList = new ArrayList<>();

    @FXML
    void createTextField(MouseEvent event){

    }

    @FXML
    void createFormulaField(MouseEvent event){

    }

    @FXML
    void createTableField(MouseEvent event){

    }

    @FXML
    void createHeadingField(MouseEvent event){

    }

    @FXML
    void createListField(MouseEvent event){

    }

    @FXML
    void stagedDragged(MouseEvent event) {
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
    void closeWindow(ActionEvent event) {
        Stage thisStage = (Stage) closeButton.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void hideWindow(ActionEvent event) {
        Stage thisStage = (Stage) hideButton.getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void minWindow(ActionEvent event) {
        Stage thisStage = (Stage) hideButton.getScene().getWindow();
        MAXIMIZED = !MAXIMIZED;
        thisStage.setMaximized(MAXIMIZED);
    }
}