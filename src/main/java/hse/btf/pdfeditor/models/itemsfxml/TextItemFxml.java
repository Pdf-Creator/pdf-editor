package hse.btf.pdfeditor.models.itemsfxml;

import hse.btf.pdfeditor.models.itemsjava.TextItem;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class TextItemFxml extends ItemFxml {
    private final TextArea textArea = new TextArea();

    public TextItemFxml(TextItem textItem){
        textArea.textProperty().bindBidirectional(textItem.text);

        // position
        textArea.layoutXProperty().bindBidirectional(textItem.getX());
        textArea.layoutYProperty().bindBidirectional(textItem.getY());
        textArea.prefWidthProperty().bindBidirectional(textItem.getW());
        textArea.prefHeightProperty().bindBidirectional(textItem.getH());

        // resizable
        //textArea.

        // action
        textArea.setOnContextMenuRequested(new EventHandler<Event>()
        {
            @Override
            public void handle(Event arg0)
            {
                System.out.println("selected text:"
                        + textArea.getSelectedText());
            }
        });
    }

    @Override
    public Node getRoot() {
        return textArea;
    }
}
