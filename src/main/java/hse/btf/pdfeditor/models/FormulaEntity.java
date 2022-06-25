package hse.btf.pdfeditor.models;

import hse.btf.pdfeditor.MouseController;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class FormulaEntity extends PaperEntity {

    private ImageView formulaImage;
    private String formula;

    public FormulaEntity() {
        super();
        formulaImage = new ImageView();
    }

    @Override
    public Pane createFxmlObject() {

        textBox.getStyleClass().add("text-region");
        Circle resizePoint = new Circle(6, Color.WHITE);
        resizePoint.setStrokeWidth(1);
        resizePoint.setStrokeType(StrokeType.INSIDE);
        resizePoint.setStroke(Color.valueOf("0x808080FF"));

        AnchorPane.setRightAnchor(resizePoint, -6.0);
        AnchorPane.setBottomAnchor(resizePoint, -6.0);

        AnchorPane.setTopAnchor(formulaImage, topPadding);
        AnchorPane.setLeftAnchor(formulaImage, leftPadding);
        AnchorPane.setRightAnchor(formulaImage, rightPadding);
        AnchorPane.setBottomAnchor(formulaImage, bottomPadding);

        textBox.getChildren().add(formulaImage);
        textBox.getChildren().add(resizePoint);

        textBox.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> textBox.setCursor(Cursor.HAND));
        textBox.addEventHandler(MouseEvent.MOUSE_EXITED, event -> textBox.setCursor(Cursor.DEFAULT));

        textBox.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            textBox.setCursor(Cursor.MOVE);
            MouseController.Position.x = e.getX();
            MouseController.Position.y = e.getY();
        });

        textBox.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> textBox.setCursor(Cursor.DEFAULT));

        textBox.setOnMouseDragged(e -> {
            double distanceX = e.getX() - MouseController.Position.x;
            double distanceY = e.getY() - MouseController.Position.y;

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
            MouseController.Position.x = e.getX();
            MouseController.Position.y = e.getY();
            e.consume();
        });

        resizePoint.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            textBox.setCursor(Cursor.DEFAULT);
            event.consume();
        });

        resizePoint.setOnMouseDragged(e -> {
            double distanceX = e.getX() - MouseController.Position.x;
            double distanceY = e.getY() - MouseController.Position.y;

            double x = textBox.getPrefWidth() + distanceX;
            double y = textBox.getPrefHeight() + distanceY;

            textBox.setPrefWidth(x);
            textBox.setPrefHeight(y);
            e.consume();
        });

        return textBox;
    }
}
