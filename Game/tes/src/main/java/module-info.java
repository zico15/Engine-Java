module com.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.game to javafx.fxml, com.sun.javafx.perf;
    exports com.game.objects;
    exports com.view;
    opens com.view to javafx.fxml;
    exports com;
    opens com to javafx.fxml;

}