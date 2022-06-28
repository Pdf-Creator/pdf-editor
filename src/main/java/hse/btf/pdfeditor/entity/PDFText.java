package hse.btf.pdfeditor.entity;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import hse.btf.pdfeditor.utils.FontUtil;
import hse.btf.pdfeditor.utils.PDFEditorConstants;

import java.io.IOException;

public class PDFText extends PDFItem {
    // TODO add to PDFEditorConstants
    public static final String defaultText = "default text";
    public static final Color defaultColor = ColorConstants.BLACK;

    private String text;
    private PdfFont textFont;
    private Color textColor;

    public PDFText(double x, double y, double w, double h) {
        super(x, y, w, h);
        setText(defaultText);
        setTextFont(FontUtil.getPdfFontByName(FontUtil.TIMES_NEW_ROMAN));
        setTextColor(defaultColor);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setTextFont(PdfFont font) {
        this.textFont = font;
    }

    public String getText() {
        return text;
    }

    public Color getTextColor() {
        return textColor;
    }

    public PdfFont getTextFont() {
        return textFont;
    }
}
