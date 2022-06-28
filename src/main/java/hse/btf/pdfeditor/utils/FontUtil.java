package hse.btf.pdfeditor.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import eu.hansolo.tilesfx.fonts.Fonts;
import hse.btf.pdfeditor.PdfEditorApplication;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FontUtil {
    private static final @NotNull String FONTS_DIR = "/src/main/resources/fonts/";

    public static final @NotNull String FREE_SANS = FONTS_DIR + "free_sans.ttf";
    public static final @NotNull String ARIAL = FONTS_DIR + "arial.ttf";
    public static final @NotNull String TIMES_NEW_ROMAN = FONTS_DIR + "times_new_roman.ttf";

    private static Map<String, Path> fontNameToFontPath = new HashMap<>();
    private static Map<Path, PdfFont> pdfFonts = new HashMap<>();
    private static Map<Path, Font> fxFonts = new HashMap<>();

    static {
        try {
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
        PdfFont font = PdfFontFactory.createFont(fontPath.toString(), PdfEncodings.IDENTITY_H);
        pdfFonts.put(fontPath, PdfFontFactory.createFont(fontPath.toString(), PdfEncodings.IDENTITY_H));
    }

    private static void addFxFont(Path fontPath) {
        addFxFont(fontPath, PDFEditorConstants.DEFAULT_FONT_SIZE);
    }

    public static void addFxFont(Path fontPath, double fontSize) {
        Font font = Font.loadFont(fontPath.toString(), fontSize);
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
