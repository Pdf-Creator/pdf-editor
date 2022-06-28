package hse.btf.pdfeditor.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import hse.btf.pdfeditor.PdfEditorApplication;
import javafx.scene.text.Font;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FontUtil {
    public static final @NotNull String FREE_SANS = "free_sans.ttf";
    public static final @NotNull String ARIAL = "arial.ttf";
    public static final @NotNull String TIMES_NEW_ROMAN = "times_new_roman.ttf";

    private static Map<String, Path> fontNameToFontPath = new HashMap<>();
    private static Map<Path, PdfFont> pdfFonts = new HashMap<>();
    private static Map<Path, Font> fxFonts = new HashMap<>();

    public static void init() {
        try {
            fontNameToFontPath.clear();
            pdfFonts.clear();
            fxFonts.clear();
            registerFont("Free Sans", Path.of(FREE_SANS));
            registerFont("Arial", Path.of(ARIAL));
            registerFont("Times New Roman", Path.of(TIMES_NEW_ROMAN));
        } catch (IOException e) {
            System.err.println("Couldn't load default fonts");
        }
    }

    public static void registerFont(String fontName, Path fontPath) throws IOException {
        fontNameToFontPath.put(fontName, fontPath);
        addPdfFont(fontPath);
        addFxFont(fontPath);
    }

    private static void addPdfFont(Path fontPath) throws IOException {
        InputStream is = PdfEditorApplication.class.getResourceAsStream(fontPath.toString());
        byte[] fontBytes = IOUtils.toByteArray(is);
        PdfFont font = PdfFontFactory.createFont(fontBytes, PdfEncodings.IDENTITY_H);
        pdfFonts.put(fontPath, font);
    }

    private static void addFxFont(Path fontPath) {
        addFxFont(fontPath, PDFEditorConstants.DEFAULT_FONT_SIZE);
    }

    public static void addFxFont(Path fontPath, double fontSize) {
        Font font = Font.loadFont(PdfEditorApplication.class.getResourceAsStream(fontPath.toString()), fontSize);
        fxFonts.put(fontPath, font);
    }

    public static PdfFont getPdfFontByName(String fontName) {
        Path fontPath = fontNameToFontPath.get(fontName);
        return pdfFonts.get(fontPath);
    }

    public static Font getFxFontByName(String fontName) {
        Path fontPath = fontNameToFontPath.get(fontName);
        return fxFonts.get(fontPath);
    }
}
