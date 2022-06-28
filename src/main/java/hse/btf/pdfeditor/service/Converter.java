package hse.btf.pdfeditor.service;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.colorspace.PdfColorSpace;
import com.itextpdf.layout.properties.TransparentColor;
import hse.btf.pdfeditor.entity.*;
import hse.btf.pdfeditor.models.*;
import hse.btf.pdfeditor.utils.FontUtil;
import hse.btf.pdfeditor.entity.PDFDocument;
import hse.btf.pdfeditor.entity.PDFFormula;
import hse.btf.pdfeditor.entity.PDFImage;
import hse.btf.pdfeditor.entity.PDFText;
import hse.btf.pdfeditor.models.FormulaItem;
import hse.btf.pdfeditor.models.ImageItem;
import hse.btf.pdfeditor.models.Item;
import hse.btf.pdfeditor.models.TextItem;
import hse.btf.pdfeditor.utils.PDFEditorConstants;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;

import java.awt.*;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static hse.btf.pdfeditor.storages.ProjectDataStorage.itemsHolder;
import static hse.btf.pdfeditor.utils.FontUtil.*;

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

//    static {
//        try {
//            FontUtil.registerFont("Free Sans", Path.of(FREE_SANS));
//            FontUtil.registerFont("Arial", Path.of(ARIAL));
//            FontUtil.registerFont("Times New Roman", Path.of(TIMES_NEW_ROMAN));
//        } catch (IOException e) {
//            System.err.println("Couldn't load default fonts");
//        }
//    }

    private static List<Float> convertCoordinates(double x, double y, double w, double h, PageSize pageSize) {
//        System.out.println("-- from ui --");
//        System.out.println("x = " + x);
//        System.out.println("y = " + y);
//        System.out.println("w = " + w);
//        System.out.println("h = " + h);
//        System.out.println("-------------");
//        System.out.println("-- page size --");
//        System.out.println("width = " + pageSize.getWidth());
//        System.out.println("height = " + pageSize.getHeight());
//        System.out.println("---------------");
        List<Float> coordinates = new ArrayList<>();
        // // prefHeight="1188.0" prefWidth="840.0"
        double coef = 842.0 / 1188.0;
        double conv_x = x * coef;
        double conv_y = y * coef;
        double conv_w = w * coef;
        double conv_h = h * coef;
//        System.out.println("conv_x = " + conv_x);
//        System.out.println("conv_y = " + conv_y);
//        System.out.println("conv_w = " + conv_w);
//        System.out.println("conv_h = " + conv_h);
        coordinates.add(0, (float) conv_x); // x
        coordinates.add(1, (float) (pageSize.getHeight() - conv_y - conv_h)); //
        coordinates.add(2, (float) conv_w); // w
        coordinates.add(3, (float) conv_h); // h
//        System.out.println("-- pdf --");
//        System.out.println("x = " + coordinates.get(0));
//        System.out.println("y = " + coordinates.get(1));
//        System.out.println("w = " + coordinates.get(2));
//        System.out.println("h = " + coordinates.get(3));
//        System.out.println("---------");
        return coordinates;
    }

    private static TransparentColor getPdfColorfromRGBA(String rgbaString) {
        List<Integer> rgba = new ArrayList<>();
        for (int i = 0; i < rgbaString.length(); i += 2) {
            rgba.add(Integer.parseInt(rgbaString.substring(i, i + 2), 16));
        }
        Color color = new DeviceRgb(rgba.get(0), rgba.get(1), rgba.get(2));
        return new TransparentColor(color, (float) rgba.get(3) / 255);
    }

    private static TransparentColor convertBorderColor(Border border) {
        if (border == null) {
            return new TransparentColor(ColorConstants.WHITE, 0);
        }
        Optional<BorderStroke> stroke = border.getStrokes().stream().findFirst();
        if (stroke.isEmpty()) {
            return new TransparentColor(ColorConstants.WHITE, 0);
        }
        System.out.println(stroke.get().getTopStroke());
        String rgbaString = stroke.get().getTopStroke().toString().substring(2);
        return getPdfColorfromRGBA(rgbaString);
    }

    private static TransparentColor convertBackgroundColor(Background background) {
        if (background == null) {
            return new TransparentColor(ColorConstants.WHITE, 0);
        }
        Optional<BackgroundFill> fill = background.getFills().stream().findFirst();
        if (fill.isEmpty()) {
            return new TransparentColor(ColorConstants.WHITE, 0);
        }
        System.out.println(fill.get().getFill());
        String rgbaString = fill.get().getFill().toString().substring(2);
        return getPdfColorfromRGBA(rgbaString);
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
        // background and border
//        TransparentColor backgroundColor = convertBackgroundColor(textItem.getBackground().get());
//        TransparentColor borderColor = convertBorderColor(textItem.getBorder().get());
//        pdfText.setBackgroundColor(backgroundColor);
//        pdfText.setBorderColor(borderColor);

        // TODO add size, font, color
        PdfFont font = FontUtil.getPdfFontByName(textItem.getFontFamily().get());
        if (font == null) {
            font = FontUtil.getPdfFontByName(PDFEditorConstants.DEFAULT_FONT);
        }
        pdfText.setTextFont(font);
        pdfText.setTextSize(textItem.getFontSize().get());
        // TODO: здесь пофиксить геттеры
//        pdfText.setText(textItem.getText().get());
//        System.out.println("-- font --");
//        Font font = textItem.getFont().get();
//        if (font != null) {
//            System.out.println(font.getFamily());
//            System.out.println(font.getName());
//            System.out.println(font.getSize());
//            System.out.println(font.getStyle());
//        }
//        System.out.println("----------");
//        System.out.println("-- background --");
//        Background background = textItem.getBackground().get();
//        if (background != null) {
//            background.getFills().forEach(fill -> System.out.println("background fill: " + fill.getFill()));
//            background.getImages().forEach(image -> System.out.println("background image: " + image.getImage().getUrl()));
//            System.out.println("background outsets: " + background.getOutsets());
//        }
//        System.out.println("----------------");
//        System.out.println("-- border --");
//        Border border = textItem.getBorder().get();
//        if (border != null) {
//            border.getImages().forEach(image -> System.out.println("border image: " + image.getImage().getUrl()));
//            border.getStrokes().forEach(stroke -> System.out.println("border stroke: " + stroke));
//            System.out.println("border insets: " + border.getInsets());
//            System.out.println("border outsets: " + border.getOutsets());
//        }
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

