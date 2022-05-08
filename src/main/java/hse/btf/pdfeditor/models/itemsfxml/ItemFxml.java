package hse.btf.pdfeditor.models.itemsfxml;

import javafx.scene.Node;

public abstract class ItemFxml {
//    // paper bounds
//    protected static final int paperWidth = 415;
//    protected static final int paperHeight = 548;
//    protected static final int paperX = 260;
//    protected static final int paperY = 51;
//
//    // field center
//    protected static final int centerX = 388;
//    protected static final int centerY = 272;

    /**
     * returning element that could be drawn
     **/
    public abstract Node getRoot();
}
