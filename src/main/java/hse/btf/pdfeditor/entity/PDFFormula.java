package hse.btf.pdfeditor.entity;

import java.awt.Color;
import com.itextpdf.kernel.colors.ColorConstants;

public class PDFFormula extends PDFItem {
    public static final Color defaultColor = Color.BLACK;
    private String formula;
    private int fontSize;
    private Color formulaColor;

    public PDFFormula(float x, float y, float w, float h) {
        super(x, y, w, h);
        setFormulaColor(defaultColor);
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public Color getFormulaColor() {
        return formulaColor;
    }

    public void setFormulaColor(Color formulaColor) {
        this.formulaColor = formulaColor;
    }
}
