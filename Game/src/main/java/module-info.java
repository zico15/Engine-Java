module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;


    opens com.engine.system to javafx.fxml;
    exports com.engine.system;
    exports com.engine.objects.interfaces;
    opens com.engine.objects.interfaces to javafx.fxml;
    exports com.engine.objects.scene;
    opens com.engine.objects.scene to javafx.fxml;

}