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
    requires bcprov.jdk16;

    opens hse.btf.pdfeditor to javafx.fxml;
    exports hse.btf.pdfeditor;
    exports hse.btf.pdfeditor.models;
    opens hse.btf.pdfeditor.models to javafx.fxml;
}