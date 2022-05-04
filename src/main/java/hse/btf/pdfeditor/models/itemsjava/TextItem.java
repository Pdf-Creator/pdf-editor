package hse.btf.pdfeditor.models.itemsjava;

import hse.btf.pdfeditor.models.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;

public class TextItem extends Item {
    public StringProperty text = new SimpleStringProperty("type text here");
}
