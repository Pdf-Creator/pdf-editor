package hse.btf.pdfeditor.models.entities;

import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public abstract class PaperEntity {
    protected AnchorPane textBox;
    protected Circle resizePoint;
    protected double leftPadding;
    protected double rightPadding;
    protected double topPadding;
    protected double bottomPadding;

    protected PaperEntity() {
        textBox = new AnchorPane();
    }

    public abstract Pane createFxmlObject();

    public DoubleProperty getXProperty() {
        return textBox.layoutXProperty();
    }

    public DoubleProperty getYProperty() {
        return textBox.layoutYProperty();
    }

    public DoubleProperty getWidthProperty() {
        return textBox.prefWidthProperty();
    }

    public DoubleProperty getHeightProperty() {
        return textBox.prefHeightProperty();
    }

    public double getWidth() {
        return textBox.getWidth();
    }

    public double getHeight() {
        return textBox.getHeight();
    }

    public double getLeftPadding() {
        return leftPadding;
    }

    public double getRightPadding() {
        return rightPadding;
    }

    public double getTopPadding() {
        return topPadding;
    }

    public double getBottomPadding() {
        return bottomPadding;
    }

    public void setX(double x) {
        textBox.setLayoutX(x);
    }

    public void setY(double y) {
        textBox.setLayoutY(y);
    }

    public void setWidth(double width) {
        textBox.setPrefWidth(width);
    }

    public void setHeight(double height) {
        textBox.setPrefHeight(height);
    }

    public void setLeftPadding(double leftPadding) {
        this.leftPadding = leftPadding;
    }

    public void setRightPadding(double rightPadding) {
        this.rightPadding = rightPadding;
    }

    public void setTopPadding(double topPadding) {
        this.topPadding = topPadding;
    }

    public void setBottomPadding(double bottomPadding) {
        this.bottomPadding = bottomPadding;
    }

    protected void removeCss(String css) {
        textBox.getStyleClass().remove(css);
    }

    protected void applyCss(String css) {
        textBox.getStyleClass().add(css);
    }

    protected void hidePoint() {
        resizePoint.setVisible(false);
    }

    protected void showPoint() {
        resizePoint.setVisible(true);
    }
}

