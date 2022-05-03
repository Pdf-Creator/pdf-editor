package hse.btf.pdfeditor.models;

import java.util.List;

public class TextItem extends Item {
    List<TextLine> textLines;

    void addTextLine(TextLine textLine) {
        if (textLine != null) {
            textLines.add(textLine);
        }
    }
}
