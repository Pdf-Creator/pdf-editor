package hse.btf.pdfeditor.utils;

import hse.btf.pdfeditor.PdfEditorApplication;
import hse.btf.pdfeditor.models.ImageEntity;
import hse.btf.pdfeditor.service.Converter;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;
import static hse.btf.pdfeditor.utils.DataStorage.entitiesList;

public class FileUtil {
    private static final FileChooser fileChooser = new FileChooser();
    private static final Desktop desktop = Desktop.getDesktop();

    public static void savePDFDocument(Button button) {
        button.setOnAction(ev -> {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);
            System.out.println(file.getAbsolutePath().toLowerCase(Locale.ROOT));
            try {
                Converter.saveDocument(file.getAbsolutePath().toLowerCase(Locale.ROOT));
            } catch (IOException e) {
                e.printStackTrace();
            }
            openPDFDocument(file.getParentFile());
        });
    }

    private static void openPDFDocument(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            System.err.println("Couldn't open file");
        }
    }

    public static void loadImage(Button button) {
        button.setOnAction(ev -> {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.gif"));
            File loadedImage = fileChooser.showOpenDialog(null);
            ImageEntity entity = new ImageEntity(loadedImage.getAbsolutePath());
            entitiesList.add(entity);
            entity.setBottomPadding(8.0);
            entity.setTopPadding(8.0);
            entity.setLeftPadding(8.0);
            entity.setRightPadding(8.0);
            entity.setWidth(150);
            entity.setHeight(90);
            papers.get(0).getChildren().add(entity.createFxmlObject());
            papers.get(0).getStylesheets().add(PdfEditorApplication.class.getResource("main.css").toExternalForm());
        });
    }

//    FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save PDF Document");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
//        createPdfButton.setOnAction(actionEvent -> {
//        File file = fileChooser.showSaveDialog(null);
//        System.out.println(file.getAbsolutePath().toLowerCase(Locale.ROOT));
//        try {
//            Converter.saveDocument(file.getAbsolutePath().toLowerCase(Locale.ROOT));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    });
}
