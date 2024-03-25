package is24am14;

import java.util.ArrayList;

public class Player {
    private String nickname;
    private int points;
    private boolean isFirstPlayer;

    private enum tokenColor {BLACK, RED, YELLOW, GREEN, BLUE}

    ;
    private Card starterCard;
    private ArrayList<Card> playerHand;
    private Condition secretObjective; //vecchio: private Objective secretObjective;

    //Constructor
    public Player(String nickname, boolean isFirstPlayer) {
        this.nickname = nickname;
        this.isFirstPlayer = isFirstPlayer;
        this.points = 0;
        this.playerHand = new ArrayList<Card>();
    }

    //Getters
    public String getPlayerNickname() {
        return this.nickname;
    }

    public int getScore() {
        return this.points;
    }

    public Card getStarterCard() {
        return this.starterCard;
    }

    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    //Setters
    public void setPlayerNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setScore(int points) {
        this.points = points;
    }

    public void setStarterCard(Card starterCard) {
        this.starterCard = starterCard;
    }

    //Methods
    public void addCardToHand(Card card) {
        this.playerHand.add(card);
    }

    public void removeCardFromHand(int index) {
        this.playerHand.remove(index);
    }

    public void addGameArea(Card cardToPlace, Card alreadyPlacedCard, int index) {
        //UNDER IMPLEMENTATION
    }

    public boolean checkLegalMove(Card attachedCard, int h, int w) {
      //USEFUL FOR addGameArea(), to be implemented
    }

}
