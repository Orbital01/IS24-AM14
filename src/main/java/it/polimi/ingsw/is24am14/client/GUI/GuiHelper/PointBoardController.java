package it.polimi.ingsw.is24am14.client.GUI.GuiHelper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PointBoardController {

    @FXML
    private StackPane pointBoardStackPane;
    @FXML
    private Pane pointBoardPane;
    @FXML
    private ImageView backgroundImage;

    private Map<Integer, double[]> positionsMap;
    private ArrayList<Image> tokenImages;
    private ArrayList<Integer> scores;

    public PointBoardController() {
    }

    @FXML
    private void initialize() {
        initializePointBoard();
        bindSizes();
        ChangeListener<Number> sizeChangeListener = (observable, oldValue, newValue) -> updateTokenPositions(tokenImages, scores);
        backgroundImage.fitWidthProperty().addListener(sizeChangeListener);
        backgroundImage.fitHeightProperty().addListener(sizeChangeListener);
    }

    private void bindSizes() {
        backgroundImage.fitWidthProperty().bind(pointBoardStackPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(pointBoardStackPane.heightProperty());
        pointBoardPane.prefWidthProperty().bind(pointBoardStackPane.widthProperty());
        pointBoardPane.prefHeightProperty().bind(pointBoardStackPane.heightProperty());

        pointBoardStackPane.setMinWidth(200);  // Imposta la larghezza minima desiderata
        pointBoardStackPane.setMinHeight(200); // Imposta l'altezza minima desiderata

        backgroundImage.imageProperty().addListener(new ChangeListener<Image>() {
            @Override
            public void changed(ObservableValue<? extends Image> observable, Image oldValue, Image newValue) {
                if (newValue != null) {
                    updateAspectRatio(newValue);
                }
            }
        });
    }

    private void updateAspectRatio(Image image) {
        double aspectRatio = image.getWidth() / image.getHeight();

        pointBoardStackPane.widthProperty().addListener((observable, oldWidth, newWidth) -> {
            double newHeight = newWidth.doubleValue() / aspectRatio;
            pointBoardStackPane.setMinHeight(newHeight);
            pointBoardStackPane.setPrefHeight(newHeight);
            pointBoardPane.setMinHeight(newHeight);
            pointBoardPane.setPrefHeight(newHeight);
        });

        pointBoardStackPane.heightProperty().addListener((observable, oldHeight, newHeight) -> {
            double newWidth = newHeight.doubleValue() * aspectRatio;
            pointBoardStackPane.setMinWidth(newWidth);
            pointBoardStackPane.setPrefWidth(newWidth);
            pointBoardPane.setMinWidth(newWidth);
            pointBoardPane.setPrefWidth(newWidth);
        });
    }

    public void initializePointBoard() {
        positionsMap = new HashMap<>();
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
   }

    private void updateTokenPositions(ArrayList<Image> tokenImages, ArrayList<Integer> scores) {
        if (backgroundImage.getFitWidth() <= 0 || backgroundImage.getFitHeight() <= 0) {
            return;
        }

        pointBoardPane.getChildren().clear();

        Map<Integer, Integer> tokenCount = new HashMap<>();

        for (int i = 0; i < tokenImages.size(); i++) {
            Image tokenImage = tokenImages.get(i);
            int score = scores.get(i);
            double[] position = positionsMap.get(score);

            if (position != null) {
                double imageWidth = backgroundImage.getFitWidth();
                double imageHeight = backgroundImage.getFitHeight();

                int count = tokenCount.getOrDefault(score, 0);
                double OFFSET_Y = imageHeight / 128;
                double TOKEN_SIZE = imageHeight / 16;
                double x = position[0] * imageWidth - TOKEN_SIZE / 2;
                double y = position[1] * imageHeight - TOKEN_SIZE / 2 - (count * OFFSET_Y);

                ImageView tokenImageView = new ImageView(tokenImage);
                tokenImageView.setLayoutX(x);
                tokenImageView.setLayoutY(y);
                tokenImageView.setFitWidth(TOKEN_SIZE);
                tokenImageView.setFitHeight(TOKEN_SIZE);
                tokenImageView.setPreserveRatio(true);

                pointBoardPane.getChildren().add(tokenImageView);

                System.out.println("Token added at: " + x + ", " + y);

                tokenCount.put(score, count + 1);
            } else {
                System.out.println("Position not found for score: " + score);
            }
        }
    }

    public void setTokenData(ArrayList<Image> tokenImages, ArrayList<Integer> scores) {
        this.tokenImages = tokenImages;
        this.scores = scores;
        updateTokenPositions(tokenImages, scores);
    }

    public void setStageDimensions(Stage stage) {
        backgroundImage.imageProperty().addListener((obs, oldImage, newImage) -> {
            if (newImage != null) {
                stage.setMinWidth(pointBoardStackPane.getMinWidth());
                stage.setMinHeight(pointBoardStackPane.getMinHeight());
            }
        });
    }

    public static StackPane getPointBoardStackPane(ArrayList<Image> tokenImages, ArrayList<Integer> scores) {
        try {
            FXMLLoader loader = new FXMLLoader(PointBoardController.class.getClassLoader().getResource("PointBoard.fxml"));
            StackPane stackPane = loader.load();
            PointBoardController controller = loader.getController();
            controller.setTokenData(tokenImages, scores);
            return stackPane;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

