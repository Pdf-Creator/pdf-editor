package hse.btf.pdfeditor.entity;

import com.itextpdf.kernel.colors.Color;

import java.util.List;

public class TableItem extends DocumentItem {
    private int cols;
    private List<? extends String> cellContents;
    private Color fontColor;


    public TableItem(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getCols() {
        return cols;
    }

    public void setCellContents(List<? extends String> cellContents) {
        this.cellContents = cellContents;
    }

    public List<? extends String> getCellContents() {
        return cellContents;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public Color getFontColor() {
        return fontColor;
    }
}
