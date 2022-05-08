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
    requires itextpdf;
    //requires bcprov.jdk16;
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
    exports hse.btf.pdfeditor.entity;
    opens hse.btf.pdfeditor.entity to javafx.fxml;
    exports hse.btf.pdfeditor.models.itemsfxml;
    opens hse.btf.pdfeditor.models.itemsfxml to javafx.fxml;
    exports hse.btf.pdfeditor.models.itemsstand;
    opens hse.btf.pdfeditor.models.itemsstand to javafx.fxml;

    requires tornadofx;
    requires kotlin.stdlib;
    requires kotlin.reflect;
    requires kotlinx.serialization.core;
    requires kotlinx.serialization.json;
}