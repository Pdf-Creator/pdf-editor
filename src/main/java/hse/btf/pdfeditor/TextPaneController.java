package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.itemsstand.Item;
import hse.btf.pdfeditor.models.itemsstand.TextItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.Singleton.itemsHolder;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;

public class TextPaneController implements Initializable {

    @FXML
    public Button newTextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newTextButton.setOnMouseClicked(ev -> {
            Region textBox = new Region();
            textBox.setPrefWidth(100);
            textBox.setPrefHeight(60);

            papers.get(0).getStylesheets().add(PdfEditorApplication.class.getResource("main.css").toExternalForm());
            textBox.getStyleClass().add("text-region");
            papers.get(0).getChildren().add(textBox);

            textBox.setOnMouseDragged(e -> {

            });
        });
    }
}