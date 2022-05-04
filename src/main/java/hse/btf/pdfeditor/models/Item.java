package hse.btf.pdfeditor.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;

// внешний прямоугольник, в который обрамлен контент
public abstract class Item {
    // default width and height
    private static final int defaultWidth = 150;
    private static final int defaultHeight = 80;

    // field center
    private static final int centerX = 388;
    private static final int centerY = 272;
    /**
     * сделать размер центра изменяемым
     * подумать над минимальным размером Item'а
     * и обработать ситуации, когда размер, например, не может быть уменьшен,
     * потому что контент внутри не позволяет этого сделать
     **/

    private ObservableIntegerValue x = new SimpleIntegerProperty(centerX);
    private ObservableIntegerValue y = new SimpleIntegerProperty(centerY);
    private ObservableIntegerValue w = new SimpleIntegerProperty(defaultWidth);
    private ObservableIntegerValue h = new SimpleIntegerProperty(defaultHeight);
    private ObservableStringValue color = new SimpleStringProperty("white");
}
