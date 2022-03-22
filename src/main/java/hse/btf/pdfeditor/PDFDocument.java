package hse.btf.pdfeditor;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.colorspace.PdfDeviceCs;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PDFDocument {
    private final String path;
    private final PdfWriter pdfWriter;
    private final PdfDocument pdfDocument;
    List<PdfPage> pages;
    private PdfPage currentPage;


    PDFDocument() throws FileNotFoundException {
        this("out/file.pdf");
    }

    PDFDocument(String path) throws FileNotFoundException {
        this.path = path;
        pdfWriter = new PdfWriter(path);
        pdfDocument = new PdfDocument(pdfWriter);
        addPage();
    }

    public void addRectangle(float x, float y, float width, float height) {
        Paragraph p = new Paragraph("This is the text added in the rectangle.").setFontColor(ColorConstants.BLACK);
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(x, y, width, height);
        canvas.setStrokeColor(ColorConstants.WHITE)
                .rectangle(rect)
                .stroke();
        new Canvas(canvas, rect).add(p);
    }

    public void setCurrentPage(int index) {
        currentPage = pdfDocument.getPage(index);
    }

    public void addPage() {
        currentPage = pdfDocument.addNewPage();
    }

    public void exportDocument() {
        Document document = new Document(pdfDocument);

        document.close();
    }

}
