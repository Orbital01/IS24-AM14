module it.polimi.ingsw.is24am14 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;
    requires com.google.gson;
    requires net.fellbaum.jemoji;

    opens it.polimi.ingsw.is24am14 to javafx.fxml;
    exports it.polimi.ingsw.is24am14.server;
    opens it.polimi.ingsw.is24am14.server.model.player to javafx.fxml;
    opens it.polimi.ingsw.is24am14.server.model.game to javafx.fxml;
    opens it.polimi.ingsw.is24am14.server.model.game.exceptions to javafx.fxml;

    exports it.polimi.ingsw.is24am14.server.model.card to com.google.gson;

    exports it.polimi.ingsw.is24am14.server.network to java.rmi;
    exports it.polimi.ingsw.is24am14.client to java.rmi;
}