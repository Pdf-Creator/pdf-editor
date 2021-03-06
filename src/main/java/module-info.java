module hse.btf.pdfeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;

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
    requires java.desktop;
    requires org.apache.commons.io;

    opens hse.btf.pdfeditor to javafx.fxml;
    exports hse.btf.pdfeditor;
    exports hse.btf.pdfeditor.entity;
    opens hse.btf.pdfeditor.entity to javafx.fxml;
    exports hse.btf.pdfeditor.models;
    opens hse.btf.pdfeditor.models to javafx.fxml;
    exports hse.btf.pdfeditor.utils;
    opens hse.btf.pdfeditor.utils to javafx.fxml;
    exports hse.btf.pdfeditor.models.utility;
    opens hse.btf.pdfeditor.models.utility to javafx.fxml;
    exports hse.btf.pdfeditor.models.entities;
    opens hse.btf.pdfeditor.models.entities to javafx.fxml;
    exports hse.btf.pdfeditor.storages;
    opens hse.btf.pdfeditor.storages to javafx.fxml;
    exports hse.btf.pdfeditor.serializers;
    opens hse.btf.pdfeditor.serializers to javafx.fxml;

    requires tornadofx;
    requires kotlin.stdlib;
    requires kotlin.reflect;
    requires kotlinx.serialization.core;
    requires kotlinx.serialization.json;
    requires annotations;
    requires org.jfree.fxgraphics2d;
}