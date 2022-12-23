module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires annotations;

    opens com to javafx.fxml;
    exports com;
    exports plugins;
    exports com.tree;
    exports com.system;
    exports com.build;
    exports com.project;
    exports com.properties.components;
    opens com.tree to javafx.fxml;
    exports com.properties;
    opens com.properties to javafx.fxml;
    opens com.view to javafx.fxml;
    exports com.view;
}