package hse.btf.pdfeditor.entity;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;

public class PDFItem {
    private double x;
    private double y;
    private double w;
    private double h;
    private Color rectangleStrokeColor;
    private Color rectangleFillColor;

    public PDFItem(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rectangleStrokeColor = ColorConstants.WHITE;
        rectangleFillColor = ColorConstants.WHITE;
    }

    public void setRectangleStrokeColor(Color rectangleStrokeColor) {
        this.rectangleStrokeColor = rectangleStrokeColor;
    }

    public void setRectangleFillColor(Color rectangleFillColor) {
        this.rectangleFillColor = rectangleFillColor;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setW(float w) {
        this.w = w;
    }

    public void setH(float h) {
        this.h = h;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public Color getRectangleStrokeColor() {
        return rectangleStrokeColor;
    }

    public Color getRectangleFillColor() {
        return rectangleFillColor;
    }
}
