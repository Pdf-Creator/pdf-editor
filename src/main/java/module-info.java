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
    requires com.gluonhq.charm.glisten;

    opens hse.btf.pdfeditor to javafx.fxml;
    exports hse.btf.pdfeditor;
}