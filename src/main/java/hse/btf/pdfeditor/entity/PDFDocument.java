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
import com.itextpdf.layout.element.Table;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PDFDocument {
    private final PdfWriter pdfWriter;
    private final PdfDocument pdfDocument;
    List<PdfPage> pages;
    private PdfPage currentPage;

    public PDFDocument() throws IOException {
        this("out/file.pdf");
    }

    public PDFDocument(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        pdfWriter = new PdfWriter(fileName);
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
                .setFillColor(textItem.getRectangleFillColor())
                .rectangle(rect)
                .fill()
                .stroke();

        // adding text to rectangle;
        Paragraph paragraph = new Paragraph(textItem.getText());
        paragraph.setFontColor(textItem.getFontColor());

        new Canvas(canvas, rect).add(paragraph);
    }

    public void addRectangleWithFormulaItem(FormulaItem formulaItem) throws IOException {
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(
                formulaItem.getX(),
                formulaItem.getY(),
                formulaItem.getW(),
                formulaItem.getW()
        );
        canvas.setStrokeColor(formulaItem.getRectangleStrokeColor())
                .setFillColor(formulaItem.getRectangleStrokeColor())
                .rectangle(rect)
                .fill()
                .stroke();

        // very strange method
        TeXFormula tf = new TeXFormula(formulaItem.getFormula());
        TeXIcon ti = tf.createTeXIcon(TeXConstants.STYLE_DISPLAY, formulaItem.getFontSize());
        BufferedImage bimg = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);

        // -- painting the formula --
        Graphics2D g2d = bimg.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0,0, ti.getIconWidth(), ti.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        ti.paintIcon(jl, g2d, 0, 0);
        // --------------------------

        // -- adding it to pdf --
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bimg, "png", byteArrayOutputStream);
        ImageData data = ImageDataFactory.create(byteArrayOutputStream.toByteArray());
        Image image = new Image(data);
        image.setFixedPosition(formulaItem.getX(), formulaItem.getY());
        image.scaleAbsolute(formulaItem.getW(), formulaItem.getH());

        new Canvas(canvas, rect).add(image);
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
                .setFillColor(imageItem.getRectangleFillColor())
                .rectangle(rect)
                .fill()
                .stroke();

        ImageData data = ImageDataFactory.create(imageItem.getImagePath());
        Image image = new Image(data);
        image.setFixedPosition(imageItem.getX(), imageItem.getY());
        image.scaleAbsolute(imageItem.getW(), imageItem.getH());

        new Canvas(canvas, rect).add(image);
    }

    public void addRectangleWithTableItem(TableItem tableItem) {
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(
                tableItem.getX(),
                tableItem.getY(),
                tableItem.getW(),
                tableItem.getH()
        );
        canvas.setStrokeColor(tableItem.getRectangleStrokeColor())
                .setFillColor(tableItem.getRectangleFillColor())
                .rectangle(rect)
                .fill()
                .stroke();

        Table table = new Table(tableItem.getCols());
        table.setFontColor(tableItem.getFontColor());

        for (String cellContent : tableItem.getCellContents()) {
            table.addCell(cellContent);
        }

        new Canvas(canvas, rect).add(table);
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
