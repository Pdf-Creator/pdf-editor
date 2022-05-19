package hse.btf.pdfeditor.entity;

public class FormulaItem extends DocumentItem {
    private String formula;
    private int fontSize;

    public FormulaItem(float x, float y, float w, float h) {
        super(x, y, w, h);
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
}
