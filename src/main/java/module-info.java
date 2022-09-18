module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires engine2d.lib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;

    opens com to javafx.fxml;
    exports com;
    exports com.tree;
    opens com.tree to javafx.fxml;
    exports com.properties;
    opens com.properties to javafx.fxml;
}