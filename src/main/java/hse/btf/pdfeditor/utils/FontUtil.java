package hse.btf.pdfeditor.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FontUtil {
    private static final @NotNull String FONTS_DIR = "src/main/resources/fonts/";

    public static final @NotNull String FREE_SANS = FONTS_DIR + "free_sans.ttf";
    public static final @NotNull String ARIAL = FONTS_DIR + "arial.ttf";
    public static final @NotNull String TIMES_NEW_ROMAN = FONTS_DIR + "times_new_roman.ttf";

    private static Map<String, PdfFont> pdfFonts = new HashMap<>();
    private static Map<String, Font> fxFonts = new HashMap<>();

    public static void addPdfFont(String fontFile) throws IOException {
        pdfFonts.put(fontFile, PdfFontFactory.createFont(fontFile, PdfEncodings.IDENTITY_H));
    }

    public static void addFxFont(String fontFile) {
        addFxFont(fontFile, PDFEditorConstants.DEFAULT_FONT_SIZE);
    }

    public static void addFxFont(String fontFile, double fontSize) {
        fxFonts.put(fontFile, Font.loadFont(fontFile, fontSize));
    }
}
