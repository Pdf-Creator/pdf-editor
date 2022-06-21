package hse.btf.pdfeditor;

import hse.btf.pdfeditor.models.itemsstand.Item;
import hse.btf.pdfeditor.models.itemsstand.TextItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

import static hse.btf.pdfeditor.Singleton.itemsHolder;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;

public class TextPaneController implements Initializable {
    private static class Position {
        double x;
        double y;
    }

    @FXML
    public Button newTextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newTextButton.setOnMouseClicked(ev -> {
            AnchorPane textBox = new AnchorPane();
            textBox.setPrefWidth(150);
            textBox.setPrefHeight(90);

            TextArea text = new TextArea();
            text.setWrapText(true);

            Circle resizePoint = new Circle(6, Color.WHITE);
            resizePoint.setStrokeWidth(1);
            resizePoint.setStrokeType(StrokeType.INSIDE);
            resizePoint.setStroke(Color.valueOf("0x808080FF"));

            AnchorPane.setRightAnchor(resizePoint, -6.0);
            AnchorPane.setBottomAnchor(resizePoint, -6.0);

            AnchorPane.setTopAnchor(text, 8.0);
            AnchorPane.setLeftAnchor(text, 8.0);
            AnchorPane.setRightAnchor(text, 8.0);
            AnchorPane.setBottomAnchor(text, 8.0);

            textBox.getChildren().add(text);
            textBox.getChildren().add(resizePoint);

            textBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> textBox.setCursor(Cursor.HAND));
            textBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> textBox.setCursor(Cursor.DEFAULT));

            final Position pos = new Position();

            textBox.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                textBox.setCursor(Cursor.MOVE);
                pos.x = e.getX();
                pos.y = e.getY();
            });

            textBox.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> textBox.setCursor(Cursor.DEFAULT));

            textBox.setOnMouseDragged(e -> {
                double distanceX = e.getX() - pos.x;
                double distanceY = e.getY() - pos.y;

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
                pos.x = e.getX();
                pos.y = e.getY();
                e.consume();
            });

            resizePoint.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                textBox.setCursor(Cursor.DEFAULT);
                event.consume();
            });

            resizePoint.setOnMouseDragged(e -> {
                double distanceX = e.getX() - pos.x;
                double distanceY = e.getY() - pos.y;

                double x = textBox.getPrefWidth() + distanceX;
                double y = textBox.getPrefHeight() + distanceY;

                textBox.setPrefWidth(x);
                textBox.setPrefHeight(y);
                e.consume();
            });

            papers.get(0).getStylesheets().add(PdfEditorApplication.class.getResource("main.css").toExternalForm());
            textBox.getStyleClass().add("text-region");
            papers.get(0).getChildren().add(textBox);
        });
    }
}