package hse.btf.pdfeditor.entity;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.font.FontProvider;

import java.io.IOException;

public class PDFText extends PDFItem {
    private String text;
    private PdfFont textFont;
    private Color textColor;

    // TODO fix after
    public static final String FONT = "src/main/resources/fonts/freesans.ttf";

    public PDFText(double x, double y, double w, double h) {
        super(x, y, w, h);
        this.text = "Add text";
        try {
            this.textFont = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
        } catch (IOException e) {
            System.out.println("Font not found");
        }
        this.textColor = ColorConstants.BLACK;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setTextFont(PdfFont textFont) {
        this.textFont = textFont;
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
