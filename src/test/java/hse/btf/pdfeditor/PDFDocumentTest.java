package hse.btf.pdfeditor;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.properties.TransparentColor;
import hse.btf.pdfeditor.entity.*;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class PDFDocumentTest {
    private static PDFDocument pdfDocument;
    private final int x = 200, y = 200, w = 200, h = 200;

    @BeforeAll
    static void init() throws IOException {
        pdfDocument = new PDFDocument();
    }

    @AfterEach
    public void addPage() {
        pdfDocument.addPage();
    }

    @AfterAll
    static void export() {
        pdfDocument.exportDocument();
    }

    @Test
    @Order(1)
    public void addRectangleWithTextTest() {
        PDFText textItem = new PDFText(x, y, w, h);
        textItem.setText("Testing TextItem");
        textItem.setTextColor(ColorConstants.BLACK);
        textItem.setBorderColor(new TransparentColor(ColorConstants.BLACK, 1));
        textItem.setBackgroundColor(new TransparentColor(ColorConstants.CYAN, .2f));
        pdfDocument.addRectangleWithTextItem(textItem);
    }

    @Test
    @Order(2)
    public void addRectangleWithImageTest() throws MalformedURLException {
        PDFImage imageItem = new PDFImage(x, y, w, h);
        imageItem.setImagePath("src/test/resources/images/java_logo.svg");
        imageItem.setBorderColor(new TransparentColor(ColorConstants.BLACK, 1));
        imageItem.setBackgroundColor(new TransparentColor(ColorConstants.CYAN, .2f));
        pdfDocument.addRectangleWithImageItem(imageItem);
    }

    @Test
    @Order(3)
    public void addRectangleWithTableTest() {
        PDFTable tableItem = new PDFTable(x, y, w, h);
        tableItem.setCols(3);
        List<String> content = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        tableItem.setCellContents(content);
        tableItem.setFontColor(ColorConstants.BLACK);
        pdfDocument.addRectangleWithTableItem(tableItem);
    }

    @Test
    @Order(4)
    public void addRectangleWithFormulaTest() throws IOException {
        PDFFormula formulaItem = new PDFFormula(x, y, w, h);
        formulaItem.setFormula("x = \\frac{-b \\pm \\sqrt{b^2 - 4ac}}{2a}");
        formulaItem.setBorderColor(new TransparentColor(ColorConstants.BLACK, 1));
        formulaItem.setBackgroundColor(new TransparentColor(ColorConstants.CYAN, .2f));
        formulaItem.setFontSize(20);
        pdfDocument.addRectangleWithFormulaItem(formulaItem);
    }
}
