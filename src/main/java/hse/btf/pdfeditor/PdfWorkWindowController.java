package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.itemsstand.TextItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.Singleton.itemsHolder;


public class PdfWorkWindowController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paperSize = 100;

        papers = new ArrayList<>();
        papers.add(paper);

        originalPaperWidth = paper.getPrefWidth();
        originalPaperHeight = paper.getPrefHeight();
        leftPanel.setPrefWidth(20);
        textPane.setPrefWidth(0);
        tablePane.setPrefWidth(0);
        imagePane.setPrefWidth(0);
        formulaPane.setPrefWidth(0);

        PaperContextMenu contextMenu = new PaperContextMenu();

        paper.setOnMouseClicked(ev -> {
            if (ev.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(paper, ev.getScreenX(), ev.getScreenY());
            } else {
                contextMenu.hide();
            }
        });

        addLeftButtonClicker(leftTextPaneButton, textPane);
        addLeftButtonClicker(leftTablePaneButton, tablePane);
        addLeftButtonClicker(leftImagePaneButton, imagePane);
        addLeftButtonClicker(leftFormulaPaneButton, formulaPane);
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

    @FXML
    public Button leftTablePaneButton;

    @FXML
    public Button leftImagePaneButton;

    @FXML
    public Button leftFormulaPaneButton;

    /**
     * panes
     **/
    @FXML
    private AnchorPane selectedPane = null;

    @FXML
    public AnchorPane textPane;

    static List<AnchorPane> papers;

    @FXML
    public AnchorPane tablePane;

    @FXML
    public AnchorPane imagePane;

    @FXML
    public AnchorPane formulaPane;

    /**
     * panes' content
     **/
    @FXML
    public Button newTextButton;

    @FXML
    public Button newTableButton;

    @FXML
    public Button newImageButton;

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