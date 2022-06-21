package hse.btf.pdfeditor.service;

import hse.btf.pdfeditor.entity.PDFDocument;
import hse.btf.pdfeditor.entity.PDFImage;
import hse.btf.pdfeditor.entity.PDFTable;
import hse.btf.pdfeditor.entity.PDFText;
import hse.btf.pdfeditor.models.ImageItem;
import hse.btf.pdfeditor.models.Item;
import hse.btf.pdfeditor.models.TableItem;
import hse.btf.pdfeditor.models.TextItem;

import java.io.IOException;

import static hse.btf.pdfeditor.Singleton.itemsHolder;

public class Converter {
    public static void saveDocument() throws IOException {
        PDFDocument pdfDocument = new PDFDocument();
        for (Item item : itemsHolder.getObservableItemsList()) {
            System.out.println("Converter: converting item:" + item.toString());

            if (item.getClass().equals(TextItem.class)) {
                System.out.println("TextItem if");

                TextItem textItem = (TextItem) item;
                PDFText pdfText = convertTextItem(textItem);
                System.out.println("x: " + pdfText.getX() + " y: " + pdfText.getY() + " w: " + pdfText.getW() + " h: " + pdfText.getH() + " text: " + pdfText.getText());

                pdfDocument.addRectangleWithTextItem(pdfText);
            }
        }
        pdfDocument.exportDocument();
    }

    private static PDFText convertTextItem(TextItem textItem) {
        System.out.println("x: " + textItem.getX() + " y: " + textItem.getY() + " w: " + textItem.getW() + " h: " + textItem.getH());
        PDFText pdfText = new PDFText(
                textItem.getX().get(),
                textItem.getY().get(),
                textItem.getW().get(),
                textItem.getH().get()
        );
        pdfText.setText(textItem.getText().get());
        return pdfText;
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

