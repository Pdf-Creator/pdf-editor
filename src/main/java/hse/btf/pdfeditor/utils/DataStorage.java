package hse.btf.pdfeditor.utils;

import hse.btf.pdfeditor.Holder;
import hse.btf.pdfeditor.models.PaperEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStorage {
    public static final ObservableList<PaperEntity> entitiesList = FXCollections.observableArrayList();
    public static final Holder itemsHolder = new Holder();
}
