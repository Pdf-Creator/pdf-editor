package hse.btf.pdfeditor.models.itemsfxml;

import hse.btf.pdfeditor.models.itemsstand.TextItem;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class TextItemFxml extends ItemFxml {
    private final TextArea textArea = new TextArea();

    public TextItemFxml(TextItem textItem) {
        // connection with TextItem
        textArea.textProperty().bindBidirectional(textItem.getText());

        // по-отдельности сделать ObservableInt размер, String шрифт и тд,
        // то есть bind'ить простые объекты

        // position
        textArea.layoutXProperty().bindBidirectional(textItem.getX());
        textArea.layoutYProperty().bindBidirectional(textItem.getY());
        textArea.prefWidthProperty().bindBidirectional(textItem.getW());
        textArea.prefHeightProperty().bindBidirectional(textItem.getH());

        // resizable
        //textArea.

        // action
    }

    @Override
    public Node getRoot() {
        return textArea;
    }
}
