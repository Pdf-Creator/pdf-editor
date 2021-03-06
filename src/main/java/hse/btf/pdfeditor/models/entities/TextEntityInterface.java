package hse.btf.pdfeditor.models.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;

public interface TextEntityInterface {
    StringProperty getText();
    ObjectProperty<Font> getFont();
    ObjectProperty<Background> getBackground();
    ObjectProperty<Border> getBorder();
    StringProperty getFontFamily();
    StringProperty getFontName();
    IntegerProperty getFontSize();
    StringProperty getFontStyle();
}
