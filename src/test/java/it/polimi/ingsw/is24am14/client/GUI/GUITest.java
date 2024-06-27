package it.polimi.ingsw.is24am14.client.GUI;

import it.polimi.ingsw.is24am14.client.GUI.GUIFactory.Guifactory;
import it.polimi.ingsw.is24am14.client.view.printer.RenderBoard;
import it.polimi.ingsw.is24am14.server.model.card.Corner;
import it.polimi.ingsw.is24am14.server.model.card.CornerEnum;
import it.polimi.ingsw.is24am14.server.model.card.ResourceCard;
import it.polimi.ingsw.is24am14.server.model.card.StarterCard;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This class is used to test the GUI bard rendering and the cards placement
 * it doesn't produce a passed or failed test, it just shows the GUI in the worst case scenario (all cards on the diagonal)
 */
public class GUITest extends Application {

    private GridPane griglia = new GridPane();

    /**
     * This method creates the game area with the cards
     * @return the game area
     */
    private GameArea createGameArea() {
        griglia=Guifactory.getBoard(); //creo la griglia

        GameArea gameArea = new GameArea();
        StarterCard starterCard;
        ResourceCard resourceCard1;
        ResourceCard resourceCard2;
        ResourceCard resourceCard3;
        ResourceCard resourceCard4;

        ArrayList<Corner> starterFrontCorners = new ArrayList<>();
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        starterFrontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        ArrayList<Corner> starterBackCorners = new ArrayList<>();
        starterBackCorners.add(new Corner(CornerEnum.EMPTY));
        starterBackCorners.add(new Corner(CornerEnum.EMPTY));
        starterBackCorners.add(new Corner(CornerEnum.HIDDEN));
        starterBackCorners.add(new Corner(CornerEnum.HIDDEN));
        ArrayList<CornerEnum.ResourceEnum> starterResources = new ArrayList<>();
        starterResources.add(CornerEnum.ResourceEnum.PLANT);
        starterResources.add(CornerEnum.ResourceEnum.ANIMAL);
        starterResources.add(CornerEnum.ResourceEnum.FUNGI);
        starterCard = new StarterCard(starterFrontCorners, starterBackCorners, starterResources, "src/main/resources/images/cards/starter_cards/starter_fronts/page_81.png", "src/main/resources/images/cards/starter_cards/starter_backs/page_81.png");

        gameArea.placeStarterCard(starterCard);
        Guifactory.addStarterCard(griglia, starterCard);//aggiungo la carta alla griglia

        ArrayList<Corner> resourceBackCorners = new ArrayList<>();
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));
        resourceBackCorners.add(new Corner(CornerEnum.EMPTY));

        ArrayList<Corner> resourceCard1FrontCorners = new ArrayList<>();
        resourceCard1FrontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        resourceCard1FrontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        resourceCard1FrontCorners.add(new Corner(CornerEnum.EMPTY));
        resourceCard1FrontCorners.add(new Corner(CornerEnum.HIDDEN));

        resourceCard1 = new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, resourceCard1FrontCorners, resourceBackCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_21.png", "");

        gameArea.addCard(starterCard, resourceCard1, 0);
        Guifactory.addCard(griglia, resourceCard1,50,50,0,1);

        ArrayList<Corner> resourceCard2FrontCorners = new ArrayList<>();
        resourceCard2FrontCorners.add(new Corner(CornerEnum.EMPTY));
        resourceCard2FrontCorners.add(new Corner(CornerEnum.EMPTY));
        resourceCard2FrontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        resourceCard2FrontCorners.add(new Corner(CornerEnum.HIDDEN));
        resourceCard2 = new ResourceCard(1, CornerEnum.ResourceEnum.PLANT, resourceCard2FrontCorners, resourceBackCorners, "src/main/resources/images/cards/resource_cards/blue_backs/page_21.png", "");

        gameArea.addCard(resourceCard1, resourceCard2, 0);
        Guifactory.addCard(griglia, resourceCard2,49,49,0,2);

        ArrayList<Corner> resourceCard3FrontCorners = new ArrayList<>();
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.HIDDEN));
        resourceCard3FrontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        resourceCard3 = new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, resourceCard3FrontCorners, resourceBackCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_21.png", "");

        gameArea.addCard(resourceCard2, resourceCard3, 0);
        Guifactory.addCard(griglia, resourceCard3,48,48,0,3);

        ArrayList<Corner> resourceCard4FrontCorners = new ArrayList<>();
        resourceCard4FrontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        resourceCard4FrontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        resourceCard4FrontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        resourceCard4FrontCorners.add(new Corner(CornerEnum.EMPTY));
        resourceCard4 = new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, resourceCard4FrontCorners, resourceBackCorners, "src/main/resources/images/cards/resource_cards/blue_backs/page_21.png", "");

        gameArea.addCard(resourceCard3, resourceCard4, 0);
        Guifactory.addCard(griglia, resourceCard4,47,47,0,4);

        return gameArea;
    }

    /**
     * This method starts the GUI
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        GameArea gameArea = createGameArea();

        //vedo come risulta la board
        RenderBoard renderBoard = new RenderBoard(gameArea);
        renderBoard.printBoard();

        Scene scene = new Scene(griglia, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

}
