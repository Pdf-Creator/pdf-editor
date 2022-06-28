package hse.btf.pdfeditor.utils;

import hse.btf.pdfeditor.PdfEditorApplication;
import hse.btf.pdfeditor.models.entities.ImageEntity;
import hse.btf.pdfeditor.service.Converter;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import static hse.btf.pdfeditor.PdfWorkWindowController.papers;
import static hse.btf.pdfeditor.utils.DataStorage.entitiesList;

public class FileUtil {
    private static final FileChooser fileChooser = new FileChooser();
    private static final Desktop desktop = Desktop.getDesktop();

    public static void savePDFDocument(Button button) {
        button.setOnAction(ev -> {
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);
            if (file == null) {
                return;
            }
            try {
                Converter.saveDocument(file.getAbsolutePath().toLowerCase(Locale.ROOT));
            } catch (IOException e) {
                System.err.println("Couldn't convert document");
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
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png", "*.gif"));
            File loadedImage = fileChooser.showOpenDialog(null);
            if (loadedImage == null) {
                return;
            }
            ImageEntity entity = new ImageEntity(loadedImage.getAbsolutePath());
            entitiesList.add(entity);
            entity.setBottomPadding(0);
            entity.setTopPadding(0);
            entity.setLeftPadding(0);
            entity.setRightPadding(0);
            papers.get(0).getChildren().add(entity.createFxmlObject());
            papers.get(0).getStylesheets().add(PdfEditorApplication.class.getResource("main.css").toExternalForm());
        });
    }
}
