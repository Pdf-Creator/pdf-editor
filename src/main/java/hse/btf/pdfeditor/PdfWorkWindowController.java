package hse.btf.pdfeditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.control.ExpansionPanel;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

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

            Stage thisStage = (Stage)thisWindow;
            thisStage.setMaximized(false);
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage thisStage = (Stage)closeButton.getScene().getWindow();
        thisStage.close();
    }

    @FXML
    void hideWindow(ActionEvent event) {
        Stage thisStage = (Stage)hideButton.getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    void minWindow(ActionEvent event) {
        Stage thisStage = (Stage)hideButton.getScene().getWindow();
        MAXIMIZED = !MAXIMIZED;
        thisStage.setMaximized(MAXIMIZED);
    }
}
