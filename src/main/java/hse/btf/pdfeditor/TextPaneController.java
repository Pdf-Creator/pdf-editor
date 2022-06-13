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

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.Singleton.itemsHolder;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;

public class TextPaneController implements Initializable {

    @FXML
    public Button newTextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rectangle textBox = new Rectangle(200, 200);
        var first = papers.get(0);

        newTextButton.setOnMouseClicked(ev -> {
            first.getChildren().add(textBox);
        });


    }
}