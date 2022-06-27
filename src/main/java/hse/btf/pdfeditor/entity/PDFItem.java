package hse.btf.pdfeditor.entity;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.properties.TransparentColor;

public class PDFItem {
    private double x;
    private double y;
    private double w;
    private double h;
    private TransparentColor rectangleStrokeColor;
    private TransparentColor rectangleFillColor;

    public PDFItem(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rectangleStrokeColor = new TransparentColor(ColorConstants.WHITE, 1);
        rectangleFillColor = new TransparentColor(ColorConstants.WHITE, 0);
    }

    public void setRectangleStrokeColor(TransparentColor rectangleStrokeColor) {
        this.rectangleStrokeColor = rectangleStrokeColor;
    }

    public void setRectangleFillColor(TransparentColor rectangleFillColor) {
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

    public TransparentColor getRectangleStrokeColor() {
        return rectangleStrokeColor;
    }

    public TransparentColor getRectangleFillColor() {
        return rectangleFillColor;
    }
}
