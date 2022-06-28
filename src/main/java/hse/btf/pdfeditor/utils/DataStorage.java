package hse.btf.pdfeditor.utils;

import hse.btf.pdfeditor.Holder;
import hse.btf.pdfeditor.models.entities.PaperEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.file.Path;

public class DataStorage {
    public static final ObservableList<PaperEntity> entitiesList = FXCollections.observableArrayList();
    public static final Holder itemsHolder = new Holder();
    public static String pdfFileName = "";
}
