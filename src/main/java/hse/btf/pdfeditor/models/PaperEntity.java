package hse.btf.pdfeditor.models;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class PaperEntity {
    AnchorPane textBox;
    protected double leftPadding;
    protected double rightPadding;
    protected double topPadding;
    protected double bottomPadding;

    protected PaperEntity() {
        textBox = new AnchorPane();
    }

    public abstract Pane createFxmlObject();

    public double getX() {
        return textBox.getLayoutX();
    }

    public double getY() {
        return textBox.getLayoutY();
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
}
