package hse.btf.pdfeditor.models.itemsfxml;

import hse.btf.pdfeditor.models.Item;
import hse.btf.pdfeditor.models.itemsjava.TextItem;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Random;

public class TextItemFxml extends ItemFxml {
    private final TextArea textArea = new TextArea();

    public TextItemFxml(TextItem textItem){
        textArea.textProperty().bindBidirectional(textItem.text);
        textArea.setLayoutX(new Random().nextInt(300));
        textArea.setLayoutY(new Random().nextInt(300));
    }

    @Override
    public Node getRoot() {
        return textArea;
    }
}
