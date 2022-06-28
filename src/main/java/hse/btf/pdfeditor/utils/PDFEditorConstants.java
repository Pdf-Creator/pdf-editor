package hse.btf.pdfeditor.utils;

import com.itextpdf.kernel.geom.PageSize;
import org.jetbrains.annotations.NotNull;

public class PDFEditorConstants {
    public static final @NotNull String DEFAULT_FONT = FontUtil.TIMES_NEW_ROMAN;
    public static final double DEFAULT_FONT_SIZE = 14;
    // TODO add default color
    // TODO add default bg color

    public static final @NotNull String DEFAULT_OUTPUT_DIR = "out/";
    public static final @NotNull String DEFAULT_PDF_FILE = DEFAULT_OUTPUT_DIR + "file.pdf";

    public static final @NotNull PageSize DEFAULT_PAGE_SIZE = PageSize.A4;
    public static final @NotNull int DEFAULT_LATEX_DPI = 1000;
}
