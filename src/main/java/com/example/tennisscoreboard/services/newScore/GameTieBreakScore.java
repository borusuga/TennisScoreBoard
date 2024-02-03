package com.example.tennisscoreboard.services.newScore;

public class GameTieBreakScore extends GameScore<Integer> {

    public GameTieBreakScore(Integer serve) {
        this.serve = serve;
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    State pointWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        if ((getTotalScore() == 1) || ((getTotalScore() - 1) % 2  == 0)) {
            this.serve = this.serve == 0? 1 : 0;
        }

        if (getPlayerScore(playerNumber) >= 7 && (getPlayerScore(playerNumber) - getOppositePlayerScore(playerNumber)) > 1) {
            if (playerNumber == 0) {
                return State.PLAYER_ONE_WON;
            }
            if (playerNumber == 1) {
                return State.PLAYER_TWO_WON;
            }
        }

        return State.ONGOING;
    }

    Integer getTotalScore() {
        return getPlayerScore(0) + getPlayerScore(1);
    }
}
