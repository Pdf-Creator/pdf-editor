package hse.btf.pdfeditor;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import hse.btf.pdfeditor.entity.ImageItem;
import hse.btf.pdfeditor.entity.PDFDocument;
import hse.btf.pdfeditor.entity.TableItem;
import hse.btf.pdfeditor.entity.TextItem;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public class PDFDocumentTest {
    private PDFDocument pdfDocument;
    private final int x = 50, y = 50, w = 100, h = 100;

    @BeforeAll
    void init() throws FileNotFoundException {
        this.pdfDocument = new PDFDocument();

    }

    @BeforeEach
    public void addPage() {
        pdfDocument.addPage();
    }

    @AfterAll
    public void export() {
        pdfDocument.exportDocument();
    }

    @Test
    public void addRectangleWithTextTest() {
        TextItem textItem = new TextItem(x, y, w, h);
        textItem.setText("Testing TextItem");
        textItem.setFontColor(ColorConstants.BLACK);
        pdfDocument.addRectangleWithTextItem(textItem);
    }

    @Test
    public void addRectangleWithImageTest() throws MalformedURLException {
        ImageItem imageItem = new ImageItem(x, y, w, h);
        imageItem.setImagePath("image_examples/Java_logo.svg");
        pdfDocument.addRectangleWithImageItem(imageItem);
    }

    @Test
    public void addRectangleWithTableTest() {
        TableItem tableItem = new TableItem(x, y, w, h);
        tableItem.setCols(3);
        List<String> content = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");
        tableItem.setCellContents(content);
        pdfDocument.addRectangleWithTableItem(tableItem);
    }
}
