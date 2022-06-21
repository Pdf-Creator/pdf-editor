package hse.btf.pdfeditor.models;

import javafx.scene.layout.Pane;

public abstract class PaperEntity {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected double leftPadding;
    protected double rightPadding;
    protected double topPadding;
    protected double bottomPadding;

    protected PaperEntity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract Pane createFxmlObject();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
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
