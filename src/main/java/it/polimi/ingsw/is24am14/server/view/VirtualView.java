package it.polimi.ingsw.is24am14.server.view;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.TokenColour;

import java.util.ArrayList;
import java.util.List;

public interface VirtualView {
    String askForUsername();

    void printLobbyOption(ArrayList<String> lobbiesNames);

    int getLobbyOption();

    int getLobbyIndex(ArrayList<String> lobbiesNames);

    int getLobbyNumPlayers();

    void printPlayersInLobby(ArrayList<String> players);

    void printColors(List<TokenColour> colors);

    TokenColour chooseColor(List<TokenColour> colors);

    void printSecretObjective(ObjectiveCard card1, ObjectiveCard card2);

    ObjectiveCard chooseSecretObjective(ObjectiveCard card1, ObjectiveCard card2);

    void printBlackToken();

    void printBoard(GameArea board);

    void printHand(ArrayList<PlayableCard> hand);

    int moveChoice();

    int chooseCardToFlip(ArrayList<PlayableCard> hand);

    int chooseCardToPlay(ArrayList<PlayableCard> hand);

    Coordinates chooseCardToOverlap(GameArea board);

    int chooseCornerIndex(GameArea board);

    int pickChoices(Deck<GoldCard> goldDeck, Deck<ResourceCard> resourceDeck, ArrayList<PlayableCard> faceUpCards);

    int chooseFaceUpCard(ArrayList<PlayableCard> faceUpCards);

    void showFaceUpCards(ArrayList<PlayableCard> faceUpCards);

    void printScore(int score);

    void printWinner(String winner);
}
