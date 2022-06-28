package hse.btf.pdfeditor.service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.properties.TransparentColor;
import hse.btf.pdfeditor.entity.PDFDocument;
import hse.btf.pdfeditor.entity.PDFFormula;
import hse.btf.pdfeditor.entity.PDFImage;
import hse.btf.pdfeditor.entity.PDFText;
import hse.btf.pdfeditor.models.FormulaItem;
import hse.btf.pdfeditor.models.ImageItem;
import hse.btf.pdfeditor.models.Item;
import hse.btf.pdfeditor.models.TextItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static hse.btf.pdfeditor.storages.ProjectDataStorage.itemsHolder;

public class Converter {
    public static void saveDocument(String fileName) throws IOException {
        PDFDocument pdfDocument = new PDFDocument(fileName);
        PageSize pageSize = pdfDocument.getPageSize();

        System.out.println("size: " + itemsHolder.getObservableItemsList().size());
        for (Item item : itemsHolder.getObservableItemsList()) {
            if (item instanceof TextItem) {
                System.out.println("TextItem");

                TextItem textItem = (TextItem) item;
                PDFText pdfText = convertTextItem(textItem, pageSize);
                pdfDocument.addRectangleWithTextItem(pdfText);
            }
            if (item instanceof ImageItem) {
                System.out.println("image item");
                ImageItem imageItem = (ImageItem) item;
                PDFImage pdfImage = convertImageItem(imageItem, pageSize);
                pdfDocument.addRectangleWithImageItem(pdfImage);
            }
            if (item instanceof FormulaItem) {
                System.out.println("formula item");
                FormulaItem formulaItem = (FormulaItem) item;
                PDFFormula pdfFormula = convertFormulaItem(formulaItem, pageSize);
                pdfDocument.addRectangleWithFormulaItem(pdfFormula);
            }
        }
        pdfDocument.exportDocument();
    }

    private static List<Float> convertCoordinates(double x, double y, double w, double h, PageSize pageSize) {
        List<Float> coordinates = new ArrayList<>();
        coordinates.add(0, (float) x); // x
        coordinates.add(1, (float) (pageSize.getHeight() - y - h)); //
        coordinates.add(2, (float) w); // w
        coordinates.add(3, (float) h); // h
        return coordinates;
    }

    private static TransparentColor convertBorderColor(Border border) {
        return null;
    }

    private static TransparentColor convertBackgroundColor(Background background) {
        return null;
    }

    private static PDFText convertTextItem(TextItem textItem, PageSize pageSize) {
        List<Float> converted = convertCoordinates(textItem.getX().get(), textItem.getY().get(), textItem.getW().get(), textItem.getH().get(), pageSize);
        PDFText pdfText = new PDFText(
                converted.get(0),
                converted.get(1),
                converted.get(2),
                converted.get(3)
        );
        pdfText.setText(textItem.getText().get());
//        System.out.println("-- font --");
//        Font font = textItem.getFont().get();
//        System.out.println(font.getFamily());
//        System.out.println(font.getName());
//        System.out.println(font.getSize());
//        System.out.println(font.getStyle());
//        System.out.println("----------");
//        System.out.println("-- background --");
//        Background background = textItem.getBackground().get();
//        background.getFills().forEach(fill -> System.out.println("background fill: " + fill.getFill()));
//        background.getImages().forEach(image -> System.out.println("background image: " + image.getImage().getUrl()));
//        System.out.println("background outsets: " + background.getOutsets());
//        System.out.println("----------------");
//        System.out.println("-- border --");
//        Border border = textItem.getBorder().get();
//        border.getImages().forEach(image -> System.out.println("border image: " + image.getImage().getUrl()));
//        border.getStrokes().forEach(stroke -> System.out.println("border stroke: " + stroke));
//        System.out.println("border insets: " + border.getInsets());
//        System.out.println("border outsets: " + border.getOutsets());
//        System.out.println("------------");
        return pdfText;
    }

    private static PDFImage convertImageItem(ImageItem imageItem, PageSize pageSize) {
        List<Float> converted = convertCoordinates(imageItem.getX().get(), imageItem.getY().get(), imageItem.getW().get(), imageItem.getH().get(), pageSize);
        PDFImage pdfImage = new PDFImage(
                converted.get(0),
                converted.get(1),
                converted.get(2),
                converted.get(3)
        );
        pdfImage.setImagePath(imageItem.getImageFileName());
        return pdfImage;
    }

    private static PDFFormula convertFormulaItem(FormulaItem formulaItem, PageSize pageSize) {
        List<Float> converted = convertCoordinates(formulaItem.getX().get(), formulaItem.getY().get(), formulaItem.getW().get(), formulaItem.getH().get(), pageSize);
        PDFFormula pdfFormula = new PDFFormula(
                converted.get(0),
                converted.get(1),
                converted.get(2),
                converted.get(3)
        );
        pdfFormula.setFormula(formulaItem.getFormula().get());
        pdfFormula.setFontSize(50);
        return pdfFormula;
    }
}

