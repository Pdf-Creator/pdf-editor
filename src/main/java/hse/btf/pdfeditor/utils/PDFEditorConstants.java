package hse.btf.pdfeditor.utils;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfPage;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class PDFEditorConstants {
    public static final @NotNull String DEFAULT_FONT = Fonts.TIMES_NEW_ROMAN;
    public static final int DEFAULT_FONT_SIZE = 14;
    // TODO add default color
    // TODO add default bg color

    public static final @NotNull String DEFAULT_OUTPUT_DIR = "out/";
    public static final @NotNull String DEFAULT_PDF_FILE = DEFAULT_OUTPUT_DIR + "file.pdf";

    public static final @NotNull PageSize DEFAULT_PAGE_SIZE = PageSize.A4;
}