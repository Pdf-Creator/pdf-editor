module hse.btf.pdfeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires jlatexmath;
    requires kernel;
    requires io;
    requires layout;
    requires forms;
    requires pdfa;
    requires sign;
    requires barcodes;
    requires font.asian;
    requires hyph;

    opens hse.btf.pdfeditor to javafx.fxml;
    exports hse.btf.pdfeditor;
}