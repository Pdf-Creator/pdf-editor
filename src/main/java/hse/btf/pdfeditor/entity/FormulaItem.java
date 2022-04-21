package hse.btf.pdfeditor.entity;

public class FormulaItem extends DocumentItem {
    private String formula;

    public FormulaItem(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }
}
