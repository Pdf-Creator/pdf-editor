package hse.btf.pdfeditor;

import com.itextpdf.kernel.geom.PageSize;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try {
            PDFDocument pdfDocument = new PDFDocument();
            float x = PageSize.A4.getWidth() / 2 - 50;
            float y = PageSize.A4.getHeight() / 2 - 50;
            float width = 100;
            float height = 100;
            pdfDocument.addRectangle(x, y, width, height);
            pdfDocument.exportDocument();
        } catch (Exception e) {
            System.err.println(e);
        }
        welcomeText.setText("Document exported!");
    }
}