module hse.btf.pdfeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires jlatexmath;

    opens hse.btf.pdfeditor to javafx.fxml;
    exports hse.btf.pdfeditor;
}