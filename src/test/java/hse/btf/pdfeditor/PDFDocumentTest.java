package hse.btf.pdfeditor;

import com.itextpdf.kernel.colors.ColorConstants;
import hse.btf.pdfeditor.entity.*;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class PDFDocumentTest {
    private static PDFDocument pdfDocument;
    private final int x = 200, y = 200, w = 200, h = 200;

    @BeforeAll
    static void init() throws FileNotFoundException {
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
        TextItem textItem = new TextItem(x, y, w, h);
        textItem.setText("Testing TextItem");
        textItem.setFontColor(ColorConstants.BLACK);
        pdfDocument.addRectangleWithTextItem(textItem);
    }

    @Test
    @Order(2)
    public void addRectangleWithImageTest() throws MalformedURLException {
        ImageItem imageItem = new ImageItem(x, y, w, h);
        imageItem.setImagePath("image_examples/Java_logo.svg");
        pdfDocument.addRectangleWithImageItem(imageItem);
    }

    @Test
    @Order(3)
    public void addRectangleWithTableTest() {
        TableItem tableItem = new TableItem(x, y, w, h);
        tableItem.setCols(3);
        List<String> content = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        tableItem.setCellContents(content);
        pdfDocument.addRectangleWithTableItem(tableItem);
    }

    @Test
    @Order(4)
    public void addRectangleWithFormulaTest() throws IOException {
        FormulaItem formulaItem = new FormulaItem(x, y, w, h);
        formulaItem.setFormula("x = \\frac{-b \\pm \\sqrt{b^2 - 4ac}}{2a}");
        formulaItem.setFontSize(20);
        pdfDocument.addRectangleWithFormulaItem(formulaItem);
    }
}
