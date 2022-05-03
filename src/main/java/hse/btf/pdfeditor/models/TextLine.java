package hse.btf.pdfeditor.models;

public class TextLine {
    String textString;
    String fontName;
    String textColor;

    TextLine(String textString, String fontName, String textColor) {
        this.textString = textString;
        this.fontName = fontName;
        this.textColor = textColor;
    }
}
