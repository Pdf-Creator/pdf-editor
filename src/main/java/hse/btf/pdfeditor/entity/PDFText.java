package hse.btf.pdfeditor.entity;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;

public class PDFText extends PDFItem {
    private String text;
    private Color fontColor;

    public PDFText(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.text = "Add text";
        this.fontColor = ColorConstants.BLACK;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public String getText() {
        return text;
    }

    public Color getFontColor() {
        return fontColor;
    }
}
