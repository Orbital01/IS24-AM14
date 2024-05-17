package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.player.*;

public class EndGameState implements GameState {

    private final GameContext context;

    public EndGameState(GameContext context) {
        this.context = context;
    }

    public void execute() {
        //controlla se gli obbiettivi del giocatore sono stati raggiunti
        for (Player player : context.getGame().getPlayers()) {
            //attenzione!!
            //controllo se il giocatore ha raggiunto l'obbiettivo segreto
            if (player.getSecretObjective().getCondition().isSatisfied(player.getPlayerBoard())) {
                player.setScore(player.getScore() + player.getSecretObjective().getPoints());
                //questo if accumula nel punteggio del giocatore i punti dell'obbiettivo segreto
            }

            //controllo se il giocatore ha raggiunto l'obbiettivo pubblico
            for(ObjectiveCard objective : context.getGame().getObjectiveDeck()){
                if (objective.getCondition().isSatisfied(player.getPlayerBoard())) {
                    player.setScore(player.getScore() + objective.getPoints());
                    //questo if accumula nel punteggio del giocatore i punti dell'obbiettivo pubblico
                }
            }
        }

        //Per quanto riguarda constatare il vincitore, ci pensa il game handler?

        //il context avrà un winner e quindi dovrò settarlo
        //cerco il giocatore con il punteggio maggiore
        Player winner = context.getGame().getPlayers().getFirst();
        for (Player player : context.getGame().getPlayers()) {
            if (player.getScore() > winner.getScore()) {
                winner = player;
            }
        }
        //setto il vincitore
        try {
            for (Player player : context.getGame().getPlayers()) {
                player.getConnection().sendWinner(winner.getPlayerNickname());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //cancello il gioco perché il è finito, vediamo come si comporta il context

        //disconnetto prima i giocatori


    }
}