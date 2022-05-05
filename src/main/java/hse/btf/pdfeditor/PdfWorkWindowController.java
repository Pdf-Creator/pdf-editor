package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.itemsjava.TextItem;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.skin.ContextMenuSkin;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.Window;

import static hse.btf.pdfeditor.Singleton.itemsHolder;

public class PdfWorkWindowController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paperSize = 100;
        originalPaperWidth = paper.getPrefWidth();
        originalPaperHeight = paper.getPrefHeight();
        leftPanel.setPrefWidth(20);
        formulaPane.setPrefWidth(0);


        textItemButton.setOnMouseClicked(ev -> {
            TextItem textField = new TextItem();

            itemsHolder.observableItemsList.add(new TextItem());
            System.out.println("Wow, you pressed me!");
        });


        PaperContextMenu contextMenu = new PaperContextMenu();

        paper.setOnMouseClicked(ev -> {
            if (ev.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(paper, ev.getScreenX(), ev.getScreenY());
            } else {
                contextMenu.hide();
            }
        });

        addLeftButtonClicker(leftFormulaButton, formulaPane);
        addLeftButtonClicker(leftOthersButton, othersPane);
        pdfEditorView = new PdfEditorView(paper);
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
    void paperSizeDecrease(MouseEvent event) {
        if (paperSize != 25) {
            paperSize -= 25;
            sizeLabel.setText(paperSize + "%");
            resizePaper();
        }
    }

    @FXML
    void paperSizeIncrease(MouseEvent event) {
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

    @FXML
    public Button leftFormulaButton;

    @FXML
    public Button leftOthersButton;

    @FXML
    public Button textItemButton;

    @FXML
    public AnchorPane leftPanel;

    @FXML
    private AnchorPane selectedPane = null;

    @FXML
    public AnchorPane othersPane;

    @FXML
    public AnchorPane formulaPane;

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