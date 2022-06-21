package hse.btf.pdfeditor.models;

import hse.btf.pdfeditor.MouseController;
import hse.btf.pdfeditor.PdfEditorView;
import hse.btf.pdfeditor.TextPaneController;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import static hse.btf.pdfeditor.MouseController.Position;

public class TextEntity extends PaperEntity {
    
    public TextEntity(double x, double y) {
        super(x, y);
    }

    public TextEntity() {
        super(0, 0);
    }


    @Override
    public Pane createFxmlObject() {
        TextArea text = new TextArea();
        AnchorPane textBox = new AnchorPane();
        
        textBox.getStyleClass().add("text-region");

        textBox.setPrefWidth(width);
        textBox.setPrefHeight(height);

        text.setWrapText(true);

        Circle resizePoint = new Circle(6, Color.WHITE);
        resizePoint.setStrokeWidth(1);
        resizePoint.setStrokeType(StrokeType.INSIDE);
        resizePoint.setStroke(Color.valueOf("0x808080FF"));

        AnchorPane.setRightAnchor(resizePoint, -6.0);
        AnchorPane.setBottomAnchor(resizePoint, -6.0);

        AnchorPane.setTopAnchor(text, topPadding);
        AnchorPane.setLeftAnchor(text, leftPadding);
        AnchorPane.setRightAnchor(text, rightPadding);
        AnchorPane.setBottomAnchor(text, bottomPadding);

        textBox.getChildren().add(text);
        textBox.getChildren().add(resizePoint);

        textBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> textBox.setCursor(Cursor.HAND));
        textBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> textBox.setCursor(Cursor.DEFAULT));

        textBox.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            textBox.setCursor(Cursor.MOVE);
            Position.x = e.getX();
            Position.y = e.getY();
        });

        textBox.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> textBox.setCursor(Cursor.DEFAULT));

        textBox.setOnMouseDragged(e -> {
            double distanceX = e.getX() - Position.x;
            double distanceY = e.getY() - Position.y;

            double x = textBox.getLayoutX() + distanceX;
            double y = textBox.getLayoutY() + distanceY;

            textBox.relocate(x, y);
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            textBox.setCursor(Cursor.SE_RESIZE);
            event.consume();
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            textBox.setCursor(Cursor.DEFAULT);
            event.consume();
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            Position.x = e.getX();
            Position.y = e.getY();
            e.consume();
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            textBox.setCursor(Cursor.DEFAULT);
            event.consume();
        });

        resizePoint.setOnMouseDragged(e -> {
            double distanceX = e.getX() - Position.x;
            double distanceY = e.getY() - Position.y;

            double x = textBox.getPrefWidth() + distanceX;
            double y = textBox.getPrefHeight() + distanceY;

            textBox.setPrefWidth(x);
            textBox.setPrefHeight(y);
            e.consume();
        });

        return textBox;
    }
}