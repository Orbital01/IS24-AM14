module it.polimi.ingsw.is24am14 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;
    requires com.google.gson;
    requires net.fellbaum.jemoji;
    requires java.naming;

    opens it.polimi.ingsw.is24am14 to javafx.fxml;
    exports it.polimi.ingsw.is24am14.server;
    opens it.polimi.ingsw.is24am14.server.model.player to javafx.fxml, com.google.gson;
    opens it.polimi.ingsw.is24am14.server.model.game to javafx.fxml, com.google.gson;
    opens it.polimi.ingsw.is24am14.server.model.game.exceptions to javafx.fxml;

    exports it.polimi.ingsw.is24am14.server.model.card to com.google.gson;

    exports it.polimi.ingsw.is24am14.server.network to java.rmi;
    exports it.polimi.ingsw.is24am14.server.model.player to java.rmi;
    exports it.polimi.ingsw.is24am14.server.model.game to java.rmi;
    exports it.polimi.ingsw.is24am14.server.model.game.exceptions to java.rmi;
    exports it.polimi.ingsw.is24am14.server.controller to java.rmi;

    opens it.polimi.ingsw.is24am14.server.network to com.google.gson, java.rmi;
    opens it.polimi.ingsw.is24am14.server.controller to com.google.gson;
    opens it.polimi.ingsw.is24am14.server.model.card to com.google.gson;
    exports it.polimi.ingsw.is24am14.server.view;
    exports it.polimi.ingsw.is24am14.server.utils to java.rmi;
    opens it.polimi.ingsw.is24am14.server.utils to com.google.gson, java.rmi;
    exports it.polimi.ingsw.is24am14.server.utils.GSONAdapters to java.rmi;
    opens it.polimi.ingsw.is24am14.server.utils.GSONAdapters to com.google.gson, java.rmi;

}