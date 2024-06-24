package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.Player;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;
import it.polimi.ingsw.is24am14.server.network.Message;
import it.polimi.ingsw.is24am14.server.network.NotYourColorTurnException;

import java.io.Serializable;
import java.util.*;

public class GameContext implements Serializable {
    Game game;
    GameStateEnum gameStateEnum;
    ArrayList<TokenColour> colors;
    HashMap<String, ArrayList<ObjectiveCard>> objectiveCardChoices;
    PlayableCard lastPlayedCard;
    ArrayList<Message> messages;

    public GameContext(Game game) {
        this.game = game;
        colors = new ArrayList<>(Arrays.asList(TokenColour.values()));
        colors.remove(TokenColour.BLACK);
        objectiveCardChoices = new HashMap<>();
        messages = new ArrayList<>();
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
        if (colorStateCompleted()) starterCardState();
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

    public void starterCardState() {
        System.out.println("Choosing starter cards");
        for (Player player : game.getPlayers()) {
            player.setStarterCard(game.getStarterCards().removeTop());
        }
        gameStateEnum = GameStateEnum.ChoosingStarterCard;
    }

    public void placeStarterCard(String username, StarterCard card) {
        this.game.getPlayer(username).getPlayerBoard().placeStarterCard(card);

        if (everyoneHasStarterCard()) playersCards();
    }

    private boolean everyoneHasStarterCard() {
        for (Player player : game.getPlayers()) {
            if (player.getPlayerBoard().getBoard().values().isEmpty()) return false;
        }
        return true;
    }

    public void playersCards() {
        System.out.println("Setting players hand cards");
        for (Player player : game.getPlayers()) {
            player.addCardToHand(game.popResourceDeck());
            player.addCardToHand(game.popResourceDeck());
            player.addCardToHand(game.popGoldDeck());
        }

        System.out.println("Choosing objective cards");
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
        if (gameStateEnum == GameStateEnum.ChoosingSecretObjective && player.getSecretObjective() == null) {
            this.game.getPlayer(player.getPlayerNickname()).setObjectiveCard(objectiveCard);

            if (objectiveCardStateCompleted()) setFirstPlayer();
        }
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
        boolean placementConditionSatisfied = false;
        Player player = game.getPlayer(username);
        PlayableCard cardToPlay = player.getPlayerHand().get(handCardIndex);

        if ((cardToPlay.getSide() == EnumSide.FRONT && cardToPlay.getPlacementCondition().isSatisfied(player.getPlayerBoard())) || cardToPlay.getSide() == EnumSide.BACK) {
            placementConditionSatisfied = true;
        }

        if (gameStateEnum == GameStateEnum.Move && game.getActivePlayer().getPlayerNickname().equals(username) && placementConditionSatisfied) {
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

    public void drawGoldCard(String username) {
        Player player = game.getPlayer(username);
        if (gameStateEnum == GameStateEnum.Draw && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.popGoldDeck());
            this.endTurn();
        }
    }

    public void drawResourceCard(String username) {
        Player player = game.getPlayer(username);
        if (gameStateEnum == GameStateEnum.Draw && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.popResourceDeck());
            this.endTurn();
        }
    }

    public void drawFaceUpCard(String username, int cardIndex) {
        Player player = game.getPlayer(username);
        if (gameStateEnum == GameStateEnum.Draw && game.getActivePlayer().getPlayerNickname().equals(username)) {
            player.addCardToHand(game.drawFaceUpCard(cardIndex));
            this.endTurn();
        }
    }

    private void endTurn() {
        this.updateScore();

        if (gameStateEnum == GameStateEnum.LastDraw && game.getIndexActivePlayer() == game.getNumPlayers()) {
            gameStateEnum = GameStateEnum.EndGame;
            return;
        }

        this.game.changeActivePlayer();

        if (someoneHas20points() && game.getIndexActivePlayer() == 0) {
            this.gameStateEnum = GameStateEnum.LastMove;
        } else {
            this.gameStateEnum = GameStateEnum.Move;
        }
    }

    private void updateScore() {
            System.out.println("Updating score");
            int earnedPoints = lastPlayedCard.getPoints() * lastPlayedCard.getPointCondition().numSatisfied(game.getActivePlayer().getPlayerBoard());
            System.out.println("Earned points: " + earnedPoints);
            //Sets player score to his old score + the points given by the satisfied condition on the gold card
            game.getActivePlayer().setScore(game.getActivePlayer().getScore() + earnedPoints);
            System.out.println(game.getActivePlayer().getPlayerNickname() + " has " + game.getActivePlayer().getScore() + " points");
    }

    private boolean someoneHas20points() {
        for (Player player : game.getPlayers()) {
            if (player.getScore() >= 20) return true;
        }
        return false;
    }

    public void addMessage(String sender, String receiver, String message) {
        this.messages.add(new Message(sender, receiver, message));
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}
