package hse.btf.pdfeditor;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;

public class PDFDocument {
    private final String path;

    PDFDocument() {
        this("out/file.pdf");
    }

    PDFDocument(String path) {
        this.path = path;
    }

    public void exportDocument() throws FileNotFoundException {
        PdfWriter pdfWriter = new PdfWriter(path);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);
        document.close();
    }
}
