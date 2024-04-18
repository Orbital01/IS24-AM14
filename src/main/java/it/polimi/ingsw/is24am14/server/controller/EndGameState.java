package it.polimi.ingsw.is24am14.server.controller;

import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.Game;
import it.polimi.ingsw.is24am14.server.model.player.*;


public class EndGameState implements GameState {

        private GameContext context;

        public EndGameState(GameContext context) {
            this.context = context;
        }

        public void execute() {
            //controlla se gli obbiettivi del giocatore sono stati raggiunti
            for (Player player : context.getGame().getPlayers) {
                //attenzione!!
                //controllo se il giocatore ha raggiunto l'obbiettivo segreto
                if (player.getSecretObjective().getCondition().isSatisfied(player.getPlayerBoard())) {
                    player.setScore(player.getScore() + player.getSecretObjective().getPoints());
                    //questo if accumula nel punteggio del giocatore i punti dell'obbiettivo segreto
                }

                //controllo se il giocatore ha raggiunto l'obbiettivo pubblico
                for(ObjectiveCard objective : context.getGame().getObjectiveCards()){
                    if (objective.getCondition().isSatisfied(player.getPlayerBoard())) {
                        player.setScore(player.getScore() + objective.getPoints());
                        //questo if accumula nel punteggio del giocatore i punti dell'obbiettivo pubblico
                    }
                }
            }

            //Per quanto riguarda constatare il vincitore, ci pensa il game handler?
        }

}
