package hse.btf.pdfeditor.service;

import com.itextpdf.kernel.geom.PageSize;
import hse.btf.pdfeditor.entity.PDFDocument;
import hse.btf.pdfeditor.entity.PDFImage;
import hse.btf.pdfeditor.entity.PDFTable;
import hse.btf.pdfeditor.entity.PDFText;
import hse.btf.pdfeditor.models.ImageItem;
import hse.btf.pdfeditor.models.Item;
import hse.btf.pdfeditor.models.TableItem;
import hse.btf.pdfeditor.models.TextItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static hse.btf.pdfeditor.utils.Singleton.itemsHolder;

public class Converter {
    public static void saveDocument() throws IOException {
        PDFDocument pdfDocument = new PDFDocument();
        PageSize pageSize = pdfDocument.getPageSize();
        for (Item item : itemsHolder.getObservableItemsList()) {
            System.out.println("Converter: converting item:" + item.toString());

            if (item instanceof TextItem) {
                System.out.println("TextItem if");

                TextItem textItem = (TextItem) item;
                PDFText pdfText = convertTextItem(textItem, pageSize);
                System.out.println("x: " + pdfText.getX() + " y: " + pdfText.getY() + " w: " + pdfText.getW() + " h: " + pdfText.getH() + " text: " + pdfText.getText());

                pdfDocument.addRectangleWithTextItem(pdfText);
            }
        }
        pdfDocument.exportDocument();
    }

    private static PDFText convertTextItem(TextItem textItem, PageSize pageSize) {
        System.out.println("x: " + textItem.getX() + " y: " + textItem.getY() + " w: " + textItem.getW() + " h: " + textItem.getH());
        System.out.println("page height: " + pageSize.getHeight() + " page width: " + pageSize.getWidth());
        System.out.println("needed y: " + (pageSize.getHeight() - textItem.getY().get() - textItem.getH().get()));
        List<Double> converted = convertCoordinates(textItem.getX().get(), textItem.getY().get(), textItem.getW().get(), textItem.getH().get(), pageSize);
        PDFText pdfText = new PDFText(
                converted.get(0),
                converted.get(1),
                converted.get(2),
                converted.get(3)
        );
        pdfText.setText(textItem.getText().get());
        return pdfText;
    }

    private static List<Double> convertCoordinates(double x, double y, double w, double h, PageSize pageSize) {
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(0, x); // x
        coordinates.add(1, pageSize.getHeight() - y - h); //
        coordinates.add(2, w); // w
        coordinates.add(3, h); // h
        return coordinates;
    }

    private PDFTable convertTableItem(TableItem tableItem) {
        PDFTable pdfTable = new PDFTable(
                tableItem.getX().get(),
                tableItem.getY().get(),
                tableItem.getW().get(),
                tableItem.getH().get()
        );
        pdfTable.setCols(tableItem.getCols().get());
        return pdfTable;
    }

    private PDFImage convertImageItem(ImageItem imageItem) {
        // TODO fix this
        return null;
    }
}

