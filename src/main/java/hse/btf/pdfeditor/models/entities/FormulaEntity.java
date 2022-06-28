package hse.btf.pdfeditor.models.entities;

import hse.btf.pdfeditor.MouseController;
import hse.btf.pdfeditor.PdfWorkWindowController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import java.awt.image.BufferedImage;

public class FormulaEntity extends PaperEntity implements FormulaEntityInterface {
    private ImageView formulaImage;
    private String formula;
    private StringProperty formulaProperty = new SimpleStringProperty();

    public FormulaEntity(String formula) {
        super();
        formulaImage = drawFormula(formula);
        double coef = formulaImage.getImage().getWidth() / formulaImage.getImage().getHeight();
        this.formula = formula;
        this.formulaProperty = new SimpleStringProperty(formula);
        formulaImage.setFitHeight(50);
        textBox.setPrefHeight(50);
        textBox.setPrefWidth(50 * coef);
    }

    @Override
    public Pane createFxmlObject() {
        if (PdfWorkWindowController.target != null) {
            PdfWorkWindowController.target.removeCss("text-region");
            PdfWorkWindowController.target.hidePoint();
        }
        this.applyCss("text-region");
        PdfWorkWindowController.target = this;


        formulaImage.setPreserveRatio(true);
        resizePoint = new Circle(6, Color.WHITE);
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
            if (PdfWorkWindowController.target != null) {
                PdfWorkWindowController.target.removeCss("text-region");
                PdfWorkWindowController.target.hidePoint();
            }
            this.applyCss("text-region");
            PdfWorkWindowController.target = this;
            this.showPoint();
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

            double coef = textBox.getPrefHeight() / textBox.getPrefWidth();
            double x = textBox.getPrefWidth() + distanceX;
            double y = textBox.getPrefHeight() + distanceX * coef;

            textBox.setPrefWidth(x);
            textBox.setPrefHeight(y);
            formulaImage.setFitWidth(x - leftPadding - rightPadding);
            formulaImage.setFitHeight(y - bottomPadding - topPadding);

            e.consume();
        });

        return textBox;
    }

    private ImageView drawFormula(String latex) {
        TeXFormula formula = new TeXFormula(latex);
        java.awt.Image awtImage = formula.createBufferedImage(TeXConstants.STYLE_TEXT, PDFEditorConstants.DEFAULT_LATEX_DPI, java.awt.Color.BLACK, null);
        Image fxImage = SwingFXUtils.toFXImage((BufferedImage) awtImage, null);
        return new ImageView(fxImage);
    }

    @Override
    public StringProperty getFormulaProperty() {
        return formulaProperty;
    }
}
