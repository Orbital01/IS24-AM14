module it.polimi.ingsw.is24am14 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens it.polimi.ingsw.is24am14 to javafx.fxml;
    exports it.polimi.ingsw.is24am14.server;
}