package it.polimi.ingsw.is24am14.client.GUI;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PointBoardController {

    @FXML
    public StackPane pointBoardStackPane;
    @FXML
    public Pane pointBoardPane;
    
    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView gameBoardImage;

    private Map<Integer, double[]> positionsMap; // Map to hold cell centers as percentages

    private ArrayList<Image> tokenImages;
    private ArrayList<Integer> scores;

    public void initialize() {



        // Initialize positions map with percentages
        positionsMap = new HashMap<>();
        positionsMap.put(0, new double[]{0.27, 0.92});
        positionsMap.put(1, new double[]{0.50, 0.92});
        positionsMap.put(2, new double[]{0.73, 0.92});
        positionsMap.put(3, new double[]{0.85, 0.82});
        positionsMap.put(4, new double[]{0.62, 0.82});
        positionsMap.put(5, new double[]{0.39, 0.82});
        positionsMap.put(6, new double[]{0.16, 0.82});
        positionsMap.put(7, new double[]{0.16, 0.71});
        positionsMap.put(8, new double[]{0.39, 0.71});
        positionsMap.put(9, new double[]{0.62, 0.71});
        positionsMap.put(10, new double[]{0.85, 0.71});
        positionsMap.put(11, new double[]{0.85, 0.605});
        positionsMap.put(12, new double[]{0.62, 0.605});
        positionsMap.put(13, new double[]{0.39, 0.605});
        positionsMap.put(14, new double[]{0.16, 0.605});
        positionsMap.put(15, new double[]{0.16, 0.50});
        positionsMap.put(16, new double[]{0.39, 0.50});
        positionsMap.put(17, new double[]{0.62, 0.50});
        positionsMap.put(18, new double[]{0.85, 0.50});
        positionsMap.put(19, new double[]{0.85, 0.393});
        positionsMap.put(20, new double[]{0.50, 0.34});
        positionsMap.put(21, new double[]{0.16, 0.393});
        positionsMap.put(22, new double[]{0.16, 0.285});
        positionsMap.put(23, new double[]{0.16, 0.18});
        positionsMap.put(24, new double[]{0.29, 0.09});
        positionsMap.put(25, new double[]{0.5, 0.07});
        positionsMap.put(26, new double[]{0.71, 0.09});
        positionsMap.put(27, new double[]{0.85, 0.18});
        positionsMap.put(28, new double[]{0.85, 0.285});
        positionsMap.put(29, new double[]{0.50, 0.2});



        // Add listeners for width and height properties of pointBoardPane
        ChangeListener<Number> sizeChangeListener = (observable, oldValue, newValue) -> updateTokenPositions();
        pointBoardPane.widthProperty().addListener(sizeChangeListener);
        pointBoardPane.heightProperty().addListener(sizeChangeListener);
    }

    public void updateBoard(ArrayList<Image> tokenImages, ArrayList<Integer> scores) {
        this.tokenImages = tokenImages;
        this.scores = scores;
        Platform.runLater(this::updateTokenPositions);
    }

    public void setBackgroundImage(Image image) {
        backgroundImage.setImage(image);
    }


    private void updateTokenPositions() {
        if (tokenImages == null || scores == null || pointBoardPane.getWidth() <= 0 || pointBoardPane.getHeight() <= 0) {
            return;
        }

//        // Debug: stampa le dimensioni del pointBoardPane
//        System.out.println("pointBoardPane width: " + pointBoardPane.getWidth() + ", height: " + pointBoardPane.getHeight());

        // Clear existing tokens by removing nodes with IDs that start with "token_"
        pointBoardPane.getChildren().removeIf(node -> node.getId() != null && node.getId().startsWith("token_"));

        // Use a map to count tokens at each score position
        Map<Integer, Integer> tokenCount = new HashMap<>();

        for (int i = 0; i < tokenImages.size(); i++) {
            Image tokenImage = tokenImages.get(i);
            int score = scores.get(i);
            double[] position = positionsMap.get(score);

            if (position != null) {
                double boardWidth = pointBoardPane.getWidth();
                double boardHeight = pointBoardPane.getHeight();

                int count = tokenCount.getOrDefault(score, 0); // Get current count of tokens at this score position
                // Adjust token size
                double OFFSET_Y = boardHeight / 128;
                double TOKEN_SIZE = boardHeight / 16;
                double x = position[0] * boardWidth - TOKEN_SIZE / 2; // Adjust by half of the token size
                double y = position[1] * boardHeight - TOKEN_SIZE / 2 - (count * OFFSET_Y); // Adjust by half of the token height and apply offset

//                // Debug: stampa le coordinate calcolate
//                System.out.println("Token " + i + " (score: " + score + ") -> x: " + x + ", y: " + y);

                ImageView tokenImageView = new ImageView(tokenImage);
                tokenImageView.setLayoutX(x);
                tokenImageView.setLayoutY(y);
                tokenImageView.setFitWidth(TOKEN_SIZE);
                tokenImageView.setFitHeight(TOKEN_SIZE);
                tokenImageView.setPreserveRatio(true);
                tokenImageView.setId("token_" + i);

                pointBoardPane.getChildren().add(tokenImageView);

                tokenCount.put(score, count + 1); // Increment the count for this score position
            } else {
                System.out.println("Position not found for score: " + score);
            }
        }

//        // Debug: Log after token positioning
//        System.out.println("Updated token positions:");
//        pointBoardPane.getChildren().forEach(node -> {
//            if (node instanceof ImageView && node.getId().startsWith("token_")) {
//                System.out.println(node.getId() + " at x: " + node.getLayoutX() + ", y: " + node.getLayoutY());
//            }
//        });

    }

    public StackPane getPointBoardStackPane(ArrayList<Image> tokenImages, ArrayList<Integer> scores){
        updateBoard(tokenImages, scores);
        return pointBoardStackPane;
    }

    public Pane getPointBoardPane(ArrayList<Image> tokenImages, ArrayList<Integer> scores) {
        updateBoard(tokenImages, scores);
        return pointBoardPane;
    }

    public static PointBoardController createInstance() throws IOException {
        FXMLLoader loader = new FXMLLoader(PointBoardController.class.getResource("PointBoard.fxml"));
        loader.load();
        return loader.getController();
    }
}
