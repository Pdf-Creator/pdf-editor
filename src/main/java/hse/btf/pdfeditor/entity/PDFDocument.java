package hse.btf.pdfeditor.entity;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
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
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TransparentColor;
import hse.btf.pdfeditor.service.Converter;
import hse.btf.pdfeditor.utils.PDFEditorConstants;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.StyleConstants;
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
    private final PageSize pageSize;

    public PDFDocument() throws IOException {
        this(PDFEditorConstants.DEFAULT_PDF_FILE, PDFEditorConstants.DEFAULT_PAGE_SIZE);
    }

    public PDFDocument(String fileName) throws IOException {
        this(fileName, PDFEditorConstants.DEFAULT_PAGE_SIZE);
    }

    public PDFDocument(String fileName, PageSize pageSize) throws IOException {
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        pdfWriter = new PdfWriter(fileName);
        pdfDocument = new PdfDocument(pdfWriter);
        this.pageSize = pageSize;
        addPage();
    }

    public PageSize getPageSize() {
        return pageSize;
    }

    public void addRectangleWithTextItem(PDFText textItem) {
        // creating canvas on current page
        PdfCanvas canvas = new PdfCanvas(currentPage);
        // creating rectangle on canvas
        Rectangle rect = new Rectangle(
                (float) textItem.getX(),
                (float) textItem.getY(),
                (float) textItem.getW(),
                (float) textItem.getH()
        );
        // drawing rectangle
        // stroke
        TransparentColor borderColor = textItem.getBorderColor();
        canvas.saveState().setStrokeColor(borderColor.getColor());
        borderColor.applyStrokeTransparency(canvas);
        canvas.rectangle(rect);
        canvas.stroke().restoreState();
        // fill
        TransparentColor backgroundColor = textItem.getBackgroundColor();
        canvas.saveState().setFillColor(backgroundColor.getColor());
        backgroundColor.applyFillTransparency(canvas);
        canvas.rectangle(rect);
        canvas.fill().restoreState();

        // adding text to rectangle
        Text text = new Text(textItem.getText())
                .setFontColor(textItem.getTextColor())
                .setFont(textItem.getTextFont());
        Paragraph paragraph = new Paragraph(text);
        new Canvas(canvas, rect).add(paragraph);
    }

    public void addRectangleWithImageItem(PDFImage imageItem) throws MalformedURLException {
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(
                (float) imageItem.getX(),
                (float) imageItem.getY(),
                (float) imageItem.getW(),
                (float) imageItem.getH()
        );

        // drawing rectangle
        // stroke
        TransparentColor borderColor = imageItem.getBorderColor();
        canvas.saveState().setStrokeColor(borderColor.getColor());
        borderColor.applyStrokeTransparency(canvas);
        canvas.rectangle(rect);
        canvas.stroke().restoreState();
        // fill
        TransparentColor backgroundColor = imageItem.getBackgroundColor();
        canvas.saveState().setFillColor(backgroundColor.getColor());
        backgroundColor.applyFillTransparency(canvas);
        canvas.rectangle(rect);
        canvas.fill().restoreState();

        ImageData data = ImageDataFactory.create(imageItem.getImagePath());
        Image image = new Image(data);
        image.setFixedPosition((float) imageItem.getX(), (float) imageItem.getY());
        image.scaleAbsolute((float) imageItem.getW(), (float) imageItem.getH());

        new Canvas(canvas, rect).add(image);
    }

    public void addRectangleWithFormulaItem(PDFFormula formulaItem) throws IOException {
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(
                (float) formulaItem.getX(),
                (float) formulaItem.getY(),
                (float) formulaItem.getW(),
                (float) formulaItem.getW()
        );

        // drawing rectangle
        // stroke
        TransparentColor borderColor = formulaItem.getBorderColor();
        canvas.saveState().setStrokeColor(borderColor.getColor());
        borderColor.applyStrokeTransparency(canvas);
        canvas.rectangle(rect);
        canvas.stroke().restoreState();
        // fill
        TransparentColor backgroundColor = formulaItem.getBackgroundColor();
        canvas.saveState().setFillColor(backgroundColor.getColor());
        backgroundColor.applyFillTransparency(canvas);
        canvas.rectangle(rect);
        canvas.fill().restoreState();

        // сreating formula image
        TeXFormula formula = new TeXFormula(formulaItem.getFormula());
        java.awt.Image formulaImage = formula.createBufferedImage(TeXConstants.STYLE_DISPLAY, formulaItem.getFontSize(), formulaItem.getFormulaColor(), null);
        BufferedImage bufferedImage = (BufferedImage) formulaImage;

        // -- adding it to pdf --
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

        ImageData data = ImageDataFactory.create(byteArrayOutputStream.toByteArray());
        Image image = new Image(data);
        image.setFixedPosition((float) formulaItem.getX(), (float) formulaItem.getY());
        image.scaleAbsolute((float) formulaItem.getW(), (float) formulaItem.getH());

        new Canvas(canvas, rect).add(image);
    }

    public void addRectangleWithTableItem(PDFTable tableItem) {
        PdfCanvas canvas = new PdfCanvas(currentPage);
        Rectangle rect = new Rectangle(
                (float) tableItem.getX(),
                (float) tableItem.getY(),
                (float) tableItem.getW(),
                (float) tableItem.getH()
        );
//        canvas.setStrokeColor(tableItem.getRectangleStrokeColor().getColor())
//                .setFillColor(tableItem.getRectangleFillColor().getColor())
//                .rectangle(rect)
//                .fill()
//                .stroke();

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
        Document document = new Document(pdfDocument, pageSize);
        document.close();
    }
}
