package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GuiHelper.PointBoardController;
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


public class PointBoardControllerTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Application started");

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

        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(10, 10, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29));



        StackPane pointBoard = PointBoardController.getPointBoardStackPane(tokenImages, scores);
        Scene scene = new Scene(pointBoard, 250, 500);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}


