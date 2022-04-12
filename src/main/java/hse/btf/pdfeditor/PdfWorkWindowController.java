package hse.btf.pdfeditor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.fxml.Initializable;

public class PdfWorkWindowController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paperSize = 100;
        originalPaperWidth = paper.getPrefWidth();
        originalPaperHeight = paper.getPrefHeight();
    }



    @FXML
    void paperSizeDecrease(MouseEvent event) {
        if (paperSize != 25) {
            paperSize -= 25;
            sizeLabel.setText(paperSize.toString() + "%");
            resizePaper();
        }
    }

    @FXML
    void paperSizeIncrease(MouseEvent event) {
        if (paperSize != 100) {
            paperSize += 25;
            sizeLabel.setText(paperSize.toString() + "%");
            resizePaper();
        }
    }

    private void resizePaper() {
        paper.setPrefWidth(originalPaperWidth / 100 * paperSize);
        paper.setPrefHeight(originalPaperHeight / 100 * paperSize);
    }

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

    private double originalPaperWidth;
    private double originalPaperHeight;
    private Integer paperSize; /// TODO :: beautiful enum

}