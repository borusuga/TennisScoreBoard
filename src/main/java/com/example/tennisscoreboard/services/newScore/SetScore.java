package com.example.tennisscoreboard.services.newScore;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SetScore extends Score<Integer> {

    @Setter
    private int serve;

    private GameScore<?> currentGame;

    public SetScore() {
        this.currentGame = new GameRegularScore();
        this.currentGame.serve = this.serve;
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    State pointWon(int playerNumber) {
        State gameState = currentGame.pointWon(playerNumber);

        if (gameState == State.PLAYER_ONE_WON || gameState == State.PLAYER_TWO_WON) {
            return gameWon(playerNumber);
        }
        this.serve = currentGame.getServe();
        return State.ONGOING;
    }

    private State gameWon(int playerNumber) {

        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        this.currentGame = new GameRegularScore();
        this.serve = this.serve == 0? 1 : 0;
        this.currentGame.serve = this.serve;

        if (getPlayerScore(playerNumber) == 6 || getPlayerScore(playerNumber) == 7) {

            // won with ADVANTAGE in 2 games
            if (getPlayerScore(playerNumber) - getOppositePlayerScore(playerNumber) > 1) {
                if (playerNumber == 0) {
                    return State.PLAYER_ONE_WON;
                }
                if (playerNumber == 1) {
                    return State.PLAYER_TWO_WON;
                }
            }

            // play tie-brake
            if (getPlayerScore(playerNumber) == 6 && getOppositePlayerScore(playerNumber) == 6) {
                this.currentGame = new GameTieBreakScore(this.serve);
                return State.ONGOING;
            }

            // won after tie-brake
            if (getPlayerScore(playerNumber) == 7 && getOppositePlayerScore(playerNumber) == 6) {
                this.currentGame = new GameRegularScore();
                if (playerNumber == 0) {
                    return State.PLAYER_ONE_WON;
                }
                if (playerNumber == 1) {
                    return State.PLAYER_TWO_WON;
                }
            }
        }

        return State.ONGOING;
    }

}
