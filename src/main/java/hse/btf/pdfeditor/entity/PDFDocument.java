package hse.btf.pdfeditor.entity;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public class PDFDocument {
    private final String path;
    private final PdfWriter pdfWriter;
    private final PdfDocument pdfDocument;
    List<PdfPage> pages;
    private PdfPage currentPage;

    public PDFDocument() throws FileNotFoundException {
        this("out/file.pdf");
    }

    public PDFDocument(String path) throws FileNotFoundException {
        this.path = path;
        pdfWriter = new PdfWriter(path);
        pdfDocument = new PdfDocument(pdfWriter);
        addPage();
    }

    public void addRectangleWithTextItem(TextItem textItem) {
        // creating canvas on current page
        PdfCanvas canvas = new PdfCanvas(currentPage);
        // creating rectangle on canvas
        Rectangle rect = new Rectangle(
                textItem.getX(),
                textItem.getY(),
                textItem.getW(),
                textItem.getH()
        );
        // drawing rectangle
        canvas.setStrokeColor(textItem.getRectangleStrokeColor())
                .rectangle(rect)
                .stroke();
        // adding text to rectangle;
        new Canvas(canvas, rect).add(
                new Paragraph(textItem.getText())
                        .setFontColor(textItem.getFontColor())
        );
    }

    public void addRectangleWithFormulaItem(FormulaItem formulaItem) {
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(
                formulaItem.getX(),
                formulaItem.getY(),
                formulaItem.getW(),
                formulaItem.getW()
        );
        canvas.setStrokeColor(formulaItem.getRectangleStrokeColor())
                .rectangle(rect)
                .stroke();

        // TODO formula to image
    }

    public void addRectangleWithImageItem(ImageItem imageItem) throws MalformedURLException {
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(
                imageItem.getX(),
                imageItem.getY(),
                imageItem.getW(),
                imageItem.getH()
        );
        canvas.setStrokeColor(imageItem.getRectangleStrokeColor())
                .rectangle(rect)
                .stroke();

        ImageData data = ImageDataFactory.create(imageItem.getImagePath());
        Image image = new Image(data);
        image.setFixedPosition(imageItem.getX(), imageItem.getY());
        image.scaleAbsolute(imageItem.getW(), imageItem.getH());

        new Canvas(canvas, rect).add(image);
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
