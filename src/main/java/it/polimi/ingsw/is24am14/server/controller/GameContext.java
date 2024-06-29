package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.Message;
import it.polimi.ingsw.is24am14.server.network.NotYourColorTurnException;

import java.io.Serializable;
import java.util.*;

/**
 * This class is used to manage the game state and the game flow.
 * It contains all the aspects of one match of the game.
 * it contains the game, the game state, the colors available, the objective card choices, the last played card and the messages.
 */
public class GameContext implements Serializable {
    Game game;
    GameStateEnum gameStateEnum;
    ArrayList<TokenColour> colors;
    HashMap<String, ArrayList<ObjectiveCard>> objectiveCardChoices;
    PlayableCard lastPlayedCard;
    ArrayList<Message> messages;

    /**
     * Constructor of the class
     * it initializes the game, the colors, the objective card choices and the messages.
     * @param game the game
     */
    public GameContext(Game game) {
        this.game = game;
        colors = new ArrayList<>(Arrays.asList(TokenColour.values()));
        colors.remove(TokenColour.BLACK);
        objectiveCardChoices = new HashMap<>();
        messages = new ArrayList<>();
    }

    /**
     * Retrieves the current state of the game.
     *
     * @return the current {@link GameStateEnum} representing the state of the game
     */
    public GameStateEnum getGameStateEnum() {
        return gameStateEnum;
    }

    /**
     * Sets the current state of the game.
     *
     * @param gameStateEnum the {@link GameStateEnum} to be set as the current state of the game
     */
    public void setGameStateEnum(GameStateEnum gameStateEnum) {
        this.gameStateEnum = gameStateEnum;
    }

    /**
     * Retrieves the current game instance.
     *
     * @return the current {@link Game} instance
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the color for the specified player and updates the game state accordingly.
     *
     * @param player the name of the player whose color is to be set
     * @param color the {@link TokenColour} to be assigned to the player
     * @throws RuntimeException if the player is not found in the game's player list
     * @throws NotYourColorTurnException if it is not the player's turn to choose a color
     * @throws Exception if any other unexpected error occurs
     */
    public void setColor(String player, TokenColour color) throws Exception {
        int playerIndex = game.getPlayers().indexOf(game.getPlayer(player));
        if (playerIndex == -1) {throw new RuntimeException();}
        for (int i = 0; i < playerIndex; i++) {
            if (game.getPlayers().get(i).getColour() == null) throw new NotYourColorTurnException();
        }
        colors.remove(color);
        game.getPlayer(player).setColour(color);
        if (colorStateCompleted()) starterCardState();
    }

    /**
     * Retrieves the list of available token colors.
     *
     * @return a {@link List} of {@link TokenColour} representing the available token colors
     */
    public List<TokenColour> getColors() {
        return colors;
    }

    /**
     * Retrieves the list of objective cards for the specified user.
     *
     * @param user the name of the user whose objective cards are to be retrieved
     * @return an {@link ArrayList} of {@link ObjectiveCard} representing the user's objective cards,
     *         or {@code null} if the user does not exist in the objective card choices map
     */
    public ArrayList<ObjectiveCard> getObjectiveCards(String user) {
        return objectiveCardChoices.get(user);
    }

    /**
     * Checks if all players have selected their colors.
     *
     * @return {@code true} if all players have selected their colors, {@code false} otherwise
     */
    public boolean colorStateCompleted() {
        for (Player player : game.getPlayers()) {
            if (player.getColour() == null) return false;
        }
        return true;
    }

    /**
     * Initiates the state where players choose their starter cards.
     * Assigns each player a starter card from the game's deck of starter cards.
     * Updates the game state to indicate that players are choosing starter cards.
     */
    public void starterCardState() {
        System.out.println("Choosing starter cards");
        for (Player player : game.getPlayers()) {
            player.setStarterCard(game.getStarterCards().removeTop());
        }
        gameStateEnum = GameStateEnum.ChoosingStarterCard;
    }

    /**
     * Places a starter card for the specified player on their player board.
     * If all players have placed their starter cards, advances the game state to players' turn.
     *
     * @param username the username of the player whose starter card is to be placed
     * @param card the {@link StarterCard} to be placed on the player's board
     */
    public void placeStarterCard(String username, StarterCard card) {
        this.game.getPlayer(username).getPlayerBoard().placeStarterCard(card);

        if (everyoneHasStarterCard()) playersCards();
    }

    /**
     * Checks if all players have placed their starter cards on their player boards.
     *
     * @return {@code true} if all players have placed their starter cards, {@code false} otherwise
     */
    private boolean everyoneHasStarterCard() {
        for (Player player : game.getPlayers()) {
            if (player.getPlayerBoard().getBoard().values().isEmpty()) return false;
        }
        return true;
    }

    /**
     * Initializes the phase where players receive their initial hand cards and select objective cards.
     * Updates the game state to indicate that players are choosing secret objective cards.
     * Adds resource cards and common objectives to each player, and assigns objective cards to each player's choices.
     * Prints messages to indicate each step of the initialization process.
     */
    public void playersCards() {
        gameStateEnum = GameStateEnum.ChoosingSecretObjective;

        System.out.println("Setting players hand cards");
        for (Player player : game.getPlayers()) {
            player.addCardToHand(game.popResourceDeck());
            player.addCardToHand(game.popResourceDeck());
            player.addCardToHand(game.popGoldDeck());
        }

        System.out.println("Adding common objective");

        game.addCommonObjective(game.getObjectiveDeck().removeTop());
        game.addCommonObjective(game.getObjectiveDeck().removeTop());

        System.out.println("Choosing objective cards");

        //  I don't know if it's necessary or not to have 2 for loops (graphical reasons maybe?)
        for (Player player : game.getPlayers()) {
            ArrayList<ObjectiveCard> objCards = new ArrayList<>();
            objCards.add(game.getObjectiveDeck().removeTop());
            objCards.add(game.getObjectiveDeck().removeTop());
            objectiveCardChoices.put(player.getPlayerNickname(), objCards);
        }
    }

    /**
     * Retrieves the list of objective cards choices available to the specified player.
     *
     * @param playerNickname the nickname of the player whose objective card choices are to be retrieved
     * @return an {@link ArrayList} of {@link ObjectiveCard} representing the player's objective card choices,
     *         or {@code null} if the player's nickname is not found in the objective card choices map
     */
    public ArrayList<ObjectiveCard> getObjectiveCardChoices(String playerNickname) {
        return objectiveCardChoices.get(playerNickname);
    }

    /**
     * Sets the secret objective card for the specified player if the game state allows and the player has not yet set their secret objective.
     * If setting the objective card completes the secret objective phase, it proceeds to set the first player.
     *
     * @param player the player whose secret objective card is to be set
     * @param objectiveCard the {@link ObjectiveCard} to be set as the player's secret objective
     */
    public void setObjectiveCard(Player player, ObjectiveCard objectiveCard) {
        if (gameStateEnum == GameStateEnum.ChoosingSecretObjective && player.getSecretObjective() == null) {
            this.game.getPlayer(player.getPlayerNickname()).setObjectiveCard(objectiveCard);

            if (objectiveCardStateCompleted()) setFirstPlayer();
        }
    }

    /**
     * Checks if all players have set their secret objective cards.
     *
     * @return {@code true} if all players have set their secret objective cards, {@code false} otherwise
     */
    public boolean objectiveCardStateCompleted() {
        for (Player player : game.getPlayers()) {
            if (player.getSecretObjective() == null) return false;
        }
        return true;
    }

    /**
     * Randomly selects the first player among all players in the game.
     * Marks the selected player as the first player and updates the game state to 'Move'.
     * The graphical interface (VIEW) is responsible for visually assigning a black token to the first player.
     */
    public void setFirstPlayer() {
        //First player is randomly chosen; after first player is chosen, he will have black token as well
        Random random = new Random();
        int firstPlayerIndex = random.nextInt(this.game.getNumPlayers());
        this.game.setIndexActivePlayer(firstPlayerIndex);
        this.game.getPlayers().get(firstPlayerIndex).setFirstPlayer(true); //--> the VIEW will graphically assign the black token to the first player

        gameStateEnum = GameStateEnum.Move;
    }

    /**
     * Places a playable card from a player's hand onto their player board under certain conditions.
     * The card placement must satisfy specific conditions based on its side (front or back).
     * Updates the game state after placing the card, transitioning to the drawing phase.
     *
     * @param username the username of the player placing the card
     * @param handCardIndex the index of the card in the player's hand to be placed
     * @param cardToOverlap the coordinates on the player board where the card will overlap
     * @param cornerIndex the index of the corner where the card will be placed, if applicable
     * @throws IllegalStateException if the placement conditions or game state requirements are not met
     */
    public void putCard(String username, int handCardIndex, Coordinates cardToOverlap, int cornerIndex) {
        boolean placementConditionSatisfied = false;
        Player player = game.getPlayer(username);
        PlayableCard cardToPlay = player.getPlayerHand().get(handCardIndex);

        if ((cardToPlay.getSide() == EnumSide.FRONT && cardToPlay.getPlacementCondition().isSatisfied(player.getPlayerBoard())) || cardToPlay.getSide() == EnumSide.BACK) {
            placementConditionSatisfied = true;
        }

        if ((gameStateEnum == GameStateEnum.Move || gameStateEnum == GameStateEnum.LastMove) && game.getActivePlayer().getPlayerNickname().equals(username) && placementConditionSatisfied) {
            player.placeCard(cardToOverlap, cardToPlay, cornerIndex);
            lastPlayedCard = cardToPlay;

            if (gameStateEnum == GameStateEnum.Move) {
                gameStateEnum = GameStateEnum.Draw;
            } else if (gameStateEnum == GameStateEnum.LastMove) {
                gameStateEnum = GameStateEnum.LastDraw;
            }
        } else {
            System.out.println("Illegal state");
            throw new IllegalStateException("Illegal state");
        }
    }

    /**
     * Allows the specified player to draw a gold card during their turn, under certain game state conditions.
     * Adds the drawn gold card to the player's hand and ends the player's turn after drawing.
     *
     * @param username the username of the player drawing the gold card
     * @throws IllegalStateException if the game state does not allow drawing a gold card or if it is not the player's turn
     */
    public void drawGoldCard(String username) {
        Player player = game.getPlayer(username);

        // Check if it's the player's turn and the game state allows drawing a gold card
        if ((gameStateEnum == GameStateEnum.Draw || gameStateEnum == GameStateEnum.LastDraw) && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.popGoldDeck());
            this.endTurn();
        }
    }

    /**
     * Allows the specified player to draw a resource card during their turn, under certain game state conditions.
     * Adds the drawn resource card to the player's hand and ends the player's turn after drawing.
     *
     * @param username the username of the player drawing the resource card
     * @throws IllegalStateException if the game state does not allow drawing a resource card or if it is not the player's turn
     */
    public void drawResourceCard(String username) {
        Player player = game.getPlayer(username);

        // Check if it's the player's turn and the game state allows drawing a gold card
        if ((gameStateEnum == GameStateEnum.Draw || gameStateEnum == GameStateEnum.LastDraw) && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.popResourceDeck());
            this.endTurn();
        }
    }

    /**
     * Allows the specified player to draw a face-up card from the display during their turn, under certain game state conditions.
     * Adds the drawn face-up card to the player's hand and ends the player's turn after drawing.
     *
     * @param username the username of the player drawing the face-up card
     * @param cardIndex the index of the face-up card in the display to be drawn
     * @throws IllegalStateException if the game state does not allow drawing a face-up card or if it is not the player's turn
     * @throws IndexOutOfBoundsException if the specified card index is out of bounds
     */
    public void drawFaceUpCard(String username, int cardIndex) {
        Player player = game.getPlayer(username);

        // Check if it's the player's turn and the game state allows drawing a gold card
        if ((gameStateEnum == GameStateEnum.Draw || gameStateEnum == GameStateEnum.LastDraw) && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.drawFaceUpCard(cardIndex));
            this.endTurn();
        }
    }

    /**
     * Ends the current player's turn, updates scores, changes active player, and transitions game state based on game conditions.
     * If it is the last player's turn and the game state is {@code LastDraw}, ends the game and performs a final score update.
     * If a player reaches 20 points and it's the beginning of a round, transitions to {@code LastMove} state.
     */
    private void endTurn() {
        this.updateScore();

        if (gameStateEnum == GameStateEnum.LastDraw && game.getIndexActivePlayer() == game.getNumPlayers() - 1) {
            System.out.println("Game ended");
            finalUpdateScore();
            gameStateEnum = GameStateEnum.EndGame;
            return;
        }

        this.game.changeActivePlayer();

        if (((someoneHas20points() || game.areDecksEmpty()) && game.getIndexActivePlayer() == 0) || gameStateEnum == GameStateEnum.LastDraw) {
            this.gameStateEnum = GameStateEnum.LastMove;
        } else {
            this.gameStateEnum = GameStateEnum.Move;
        }
    }

    /**
     * Updates the score of the active player based on the last played card's point conditions.
     * Calculates earned points from the last played card's front side based on conditions satisfied on the player's board.
     * Prints debug messages showing the updated score and earned points.
     */
    private void updateScore() {
            System.out.println("Updating score");
            int earnedPoints = lastPlayedCard.getSide() == EnumSide.FRONT ? lastPlayedCard.getPoints() * lastPlayedCard.getPointCondition().numSatisfied(game.getActivePlayer().getPlayerBoard()) : 0;
            System.out.println("Earned points: " + earnedPoints);
            //Sets player score to his old score + the points given by the satisfied condition on the gold card
            game.getActivePlayer().setScore(game.getActivePlayer().getScore() + earnedPoints);
            System.out.println(game.getActivePlayer().getPlayerNickname() + " has " + game.getActivePlayer().getScore() + " points");
    }

    /**
     * Checks if any player in the game has reached or exceeded 20 points.
     *
     * @return {@code true} if at least one player has 20 or more points, {@code false} otherwise
     */
    private boolean someoneHas20points() {
        for (Player player : game.getPlayers()) {
            if (player.getScore() >= 20) return true;
        }
        return false;
    }

    /**
     * Performs a final update of scores for all players at the end of the game.
     * Updates each player's score based on their secret objective and common objectives completed on their player board.
     * Prints debug messages showing the final updated scores for each player.
     */
    private void finalUpdateScore() {
        System.out.println("Updating score for the last time");
        for (Player player : game.getPlayers()) {
            int score = player.getScore();
            score += player.getSecretObjective().getCondition().numSatisfied(player.getPlayerBoard()) * player.getSecretObjective().getPoints();
            score += game.getCommonObjective().getFirst().getCondition().numSatisfied(player.getPlayerBoard()) * game.getCommonObjective().getFirst().getPoints();
            score += game.getCommonObjective().get(1).getCondition().numSatisfied(player.getPlayerBoard()) * game.getCommonObjective().get(1).getPoints();
            player.setScore(score);
        }
    }

    /**
     * Adds a new message to the collection of messages.
     *
     * @param sender   the sender of the message
     * @param receiver the receiver of the message
     * @param message  the content of the message
     */
    public void addMessage(String sender, String receiver, String message) {
        this.messages.add(new Message(sender, receiver, message));
    }

    /**
     * Retrieves the list of messages stored.
     *
     * @return an {@code ArrayList} of {@code Message} objects representing the stored messages
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }
}
