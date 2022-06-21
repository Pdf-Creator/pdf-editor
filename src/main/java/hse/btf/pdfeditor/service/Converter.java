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
    public void saveDocument() throws IOException {
        PDFDocument pdfDocument = new PDFDocument();
        for (Item item : itemsHolder.getObservableItemsList()) {
            if (item.getClass().equals(TextItem.class)) {
                TextItem textItem = (TextItem) item;
                pdfDocument.addRectangleWithTextItem(convertTextItem(textItem));
            }
        }
        pdfDocument.exportDocument();
    }

    private PDFText convertTextItem(TextItem textItem) {
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

