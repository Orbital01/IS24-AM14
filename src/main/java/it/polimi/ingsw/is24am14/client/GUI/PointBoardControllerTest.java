package it.polimi.ingsw.is24am14.client.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/*
public class PointBoardControllerTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXML file path
        String fxmlPath = "/PointBoard.fxml";
        System.out.println("FXML Path: " + getClass().getResource(fxmlPath));

        // Load GUI from FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        if (loader.getLocation() == null) {
            System.err.println("FXML file not found!");
            return;
        }
        Parent root = loader.load();
        PointBoardController controller = loader.getController();

//        // Set background image (unneeded)
//        URL backgroundImageURL = getClass().getResource("/images/background.png");
//        if (backgroundImageURL != null) {
//            Image backgroundImage = new Image(backgroundImageURL.toString());
//            controller.setBackgroundImage(backgroundImage);
//        } else {
//            System.err.println("Background image not found!");
//        }




        // Adding images and scores in the arrays
        ArrayList<Image> tokenImages = new ArrayList<>();
        String[] tokenPaths = {
                "/images.tokens/blue_token.png",
                "/images.tokens/red_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/red_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/red_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/red_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/red_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/red_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",
                "/images.tokens/blue_token.png",
                "/images.tokens/red_token.png",
                "/images.tokens/green_token.png",
                "/images.tokens/yellow_token.png",


        };

        for (String path : tokenPaths) {
            URL imageUrl = getClass().getResource(path);
            if (imageUrl == null) {
                System.err.println("Image not found: " + path);
                continue;
            }
            tokenImages.add(new Image(imageUrl.toString()));
        }

        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(10, 10, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29));    ;

        // Updating the board
        controller.updateBoard(tokenImages, scores);

        StackPane gameBoardStackPane = controller.getPointBoardStackPane(tokenImages, scores);
        System.out.println("StackPane aggiornato: " + gameBoardStackPane);
        // Visualizing the scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/