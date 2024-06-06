package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.NotYourColorTurnException;

import java.io.Serializable;
import java.util.*;

public class GameContext implements Serializable {
    Game game;
    GameStateEnum gameStateEnum;
    ArrayList<TokenColour> colors;
    HashMap<String, ArrayList<ObjectiveCard>> objectiveCardChoices;

    public GameContext(Game game) {
        this.game = game;
        colors = new ArrayList<>(Arrays.asList(TokenColour.values()));
        colors.remove(TokenColour.BLACK);
        objectiveCardChoices = new HashMap<>();
    }

    public GameStateEnum getGameStateEnum() {
        return gameStateEnum;
    }

    public void setGameStateEnum(GameStateEnum gameStateEnum) {
        this.gameStateEnum = gameStateEnum;
    }

    public Game getGame() {
        return game;
    }

    public void setColor(String player, TokenColour color) throws Exception {
        int playerIndex = game.getPlayers().indexOf(game.getPlayer(player));
        if (playerIndex == -1) {throw new RuntimeException();}
        for (int i = 0; i < playerIndex; i++) {
            if (game.getPlayers().get(i).getColour() == null) throw new NotYourColorTurnException();
        }
        colors.remove(color);
        game.getPlayer(player).setColour(color);
        if (colorStateCompleted()) playersCards();
    }

    public List<TokenColour> getColors() {
        return colors;
    }

    public ArrayList<ObjectiveCard> getObjectiveCards(String user) {
        return objectiveCardChoices.get(user);
    }

    public boolean colorStateCompleted() {
        for (Player player : game.getPlayers()) {
            if (player.getColour() == null) return false;
        }
        return true;
    }

    public void playersCards() {
        System.out.println("Choosing objective cards");
        for (Player player : game.getPlayers()) {
            player.addCardToHand(game.popResourceDeck());
            player.addCardToHand(game.popResourceDeck());
            player.addCardToHand(game.popGoldDeck());
        }

        //  I don't know if it's necessary or not to have 2 for loops (graphical reasons maybe?)
        for (Player player : game.getPlayers()) {
            ArrayList<ObjectiveCard> objCards = new ArrayList<>();
            objCards.add(game.getObjectiveDeck().removeTop());
            objCards.add(game.getObjectiveDeck().removeTop());
            objectiveCardChoices.put(player.getPlayerNickname(), objCards);
        }
        gameStateEnum = GameStateEnum.ChoosingSecretObjective;
    }

    public ArrayList<ObjectiveCard> getObjectiveCardChoices(String playerNickname) {
        return objectiveCardChoices.get(playerNickname);
    }

    public void setObjectiveCard(Player player, ObjectiveCard objectiveCard) {
        this.game.getPlayer(player.getPlayerNickname()).setObjectiveCard(objectiveCard);

        if (objectiveCardStateCompleted()) setFirstPlayer();
    }

    public boolean objectiveCardStateCompleted() {
        for (Player player : game.getPlayers()) {
            if (player.getSecretObjective() == null) return false;
        }
        return true;
    }

    public void setFirstPlayer() {
        //First player is randomly chosen; after first player is chosen, he will have black token as well
        Random random = new Random();
        int firstPlayerIndex = random.nextInt(this.game.getNumPlayers());
        this.game.setIndexActivePlayer(firstPlayerIndex);
        this.game.getPlayers().get(firstPlayerIndex).setFirstPlayer(true); //--> the VIEW will graphically assign the black token to the first player

        gameStateEnum = GameStateEnum.Move;
    }

    public void putCard(String username, int handCardIndex, Coordinates cardToOverlap, int cornerIndex) {
        Player player = game.getPlayer(username);
        if (gameStateEnum == GameStateEnum.Move && game.getActivePlayer().getPlayerNickname().equals(username)) {
            PlayableCard cardToPlay = player.getPlayerHand().get(handCardIndex);
            player.placeCard(cardToOverlap, cardToPlay, cornerIndex);
        }
    }

    public void drawGoldCard(String username) {
        Player player = game.getPlayer(username);
        if (gameStateEnum == GameStateEnum.Draw && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.popGoldDeck());
        }
    }

    public void drawResourceCard(String username) {
        Player player = game.getPlayer(username);
        if (gameStateEnum == GameStateEnum.Draw && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.popResourceDeck());
        }
    }

    public void drawFaceUpCard(String username, int cardIndex) {
        Player player = game.getPlayer(username);
        if (gameStateEnum == GameStateEnum.Draw && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.drawFaceUpCard(cardIndex));
        }
    }
}
