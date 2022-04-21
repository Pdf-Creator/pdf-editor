package hse.btf.pdfeditor.entity;

import com.itextpdf.kernel.colors.Color;

public class TextItem extends DocumentItem {
    private String text;
    private Color fontColor;

    public TextItem(float x, float y, float w, float h) {
        super(x, y, w, h);
        this.text = "Add text";
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
