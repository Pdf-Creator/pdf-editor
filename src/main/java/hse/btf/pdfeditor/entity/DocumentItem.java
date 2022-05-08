package hse.btf.pdfeditor.entity;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;

public class DocumentItem {
    private float x;
    private float y;
    private float w;
    private float h;
    private Color rectangleStrokeColor;

    public DocumentItem(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rectangleStrokeColor = ColorConstants.WHITE;
    }

    public void setRectangleStrokeColor(Color rectangleStrokeColor) {
        this.rectangleStrokeColor = rectangleStrokeColor;
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }

    public Color getRectangleStrokeColor() {
        return rectangleStrokeColor;
    }
}
