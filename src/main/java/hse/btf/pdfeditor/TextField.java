package hse.btf.pdfeditor;

import java.util.List;

public class TextField extends RectangleField {
    List<TextLine> textLines;

    void addTextLine(TextLine textLine) {
        if (textLine != null) {
            textLines.add(textLine);
        }
    }
}
