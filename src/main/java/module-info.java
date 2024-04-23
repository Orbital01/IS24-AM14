module it.polimi.ingsw.is24am14 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.core;
    requires java.rmi;

    opens it.polimi.ingsw.is24am14 to javafx.fxml;
    exports it.polimi.ingsw.is24am14.server;
    opens it.polimi.ingsw.is24am14.server.model.player to javafx.fxml;
    opens it.polimi.ingsw.is24am14.server.model.game to javafx.fxml;
    opens it.polimi.ingsw.is24am14.server.model.game.exceptions to javafx.fxml;
}