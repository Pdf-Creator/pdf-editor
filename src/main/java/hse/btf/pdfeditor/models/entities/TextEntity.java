package hse.btf.pdfeditor.models.entities;

import hse.btf.pdfeditor.PdfWorkWindowController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Cursor;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import static hse.btf.pdfeditor.MouseController.Position;

public class TextEntity extends PaperEntity implements TextEntityInterface {
    private final TextArea text = new TextArea();
    public TextEntity() {
        super();
    }

    @Override
    public Pane createFxmlObject() {
        if (PdfWorkWindowController.target != null) {
            PdfWorkWindowController.target.removeCss("text-region");
            PdfWorkWindowController.target.hidePoint();
        }
        this.applyCss("text-region");
        PdfWorkWindowController.target = this;

        text.setWrapText(true);

        resizePoint = new Circle(6, Color.WHITE);
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
            if (PdfWorkWindowController.target != null) {
                PdfWorkWindowController.target.removeCss("text-region");
                PdfWorkWindowController.target.hidePoint();
            }
            this.applyCss("text-region");
            PdfWorkWindowController.target = this;
            this.showPoint();
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

    @Override
    public StringProperty getText() {
        return text.textProperty();
    }

    @Override
    public ObjectProperty<Font> getFont() {
        return text.fontProperty();
    }

    @Override
    public ObjectProperty<Background> getBackground() {
        return text.backgroundProperty();
    }

    @Override
    public ObjectProperty<Border> getBorder() {
        return text.borderProperty();
    }
}
