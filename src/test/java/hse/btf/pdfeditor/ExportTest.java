package hse.btf.pdfeditor;

import hse.btf.pdfeditor.entity.PDFDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ExportTest {
    private PDFDocument pdfDocument;

    @Test
    public void inSameOutDir() throws IOException {
        pdfDocument = new PDFDocument("out/file2.pdf");
    }

    @Test
    public void inNestedDir() throws IOException {
        pdfDocument = new PDFDocument("out/nested/file.pdf");
    }

    @Test
    public void inDeepDir() throws IOException {
        pdfDocument = new PDFDocument("out/nested/nested2/file.pdf");
    }

    @AfterEach
    public void export() {
        pdfDocument.exportDocument();
    }
}
