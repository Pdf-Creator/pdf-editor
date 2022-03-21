package hse.btf.pdfeditor;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class filePDF {
    private final String fileName;
    private boolean isCreated;
    private final File file;
    private HashMap<String, Font> fonts;

    filePDF() {
        this("out/file.pdf");
    }

    filePDF(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
        isCreated = false;
        try {
            isCreated = file.createNewFile();
        } catch (IOException | SecurityException e) {
            System.err.println(e);
        }
    }

    public void addFont(String fontName, Font.FontFamily fontFamily, float fontSize, int fontStyle) {
        addColoredFont(fontName, fontFamily, fontSize, fontStyle, BaseColor.BLACK);
    }

    public void addColoredFont(String fontName, Font.FontFamily fontFamily, float fontSize, int fontStyle, BaseColor fontColor) {
        Font font = new Font(fontFamily, fontSize, fontStyle, fontColor);
        fonts.put(fontName, font);
    }
}
