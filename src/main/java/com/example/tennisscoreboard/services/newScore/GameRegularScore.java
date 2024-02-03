package com.example.tennisscoreboard.services.newScore;

public class GameRegularScore extends GameScore<GamePoints> {

    @Override
    protected GamePoints getZeroScore() {
        return GamePoints.ZERO;
    }

    @Override
    State pointWon(int playerNumber) {
        GamePoints playerScore = getPlayerScore(playerNumber);
        if (playerScore.ordinal() <= GamePoints.THIRTY.ordinal()) {
            // 0:X, 15:X, 30:X
            setPlayerScore(playerNumber, playerScore.next());
            // 15:X, 30:X, 40:X
        } else if (playerScore == GamePoints.FORTY) {
            // 40:X
            GamePoints oppositePlayerScore = getOppositePlayerScore(playerNumber);

            if (oppositePlayerScore == GamePoints.ADVANTAGE) {
                // 40 : AD
                setOppositePlayerScore(playerNumber, GamePoints.FORTY);
                // 40 : 40
            } else if (oppositePlayerScore == GamePoints.FORTY) {
                // 40 : 40
                setPlayerScore(playerNumber, GamePoints.ADVANTAGE);
                // AD : 40
            } else {
                // 40 : 0, 40 : 15, 40 : 30 -> wins the game
                return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
            }
        } else if (playerScore == GamePoints.ADVANTAGE) {
            // AD : 40 -> wins the game
            return playerNumber == 0 ? State.PLAYER_ONE_WON : State.PLAYER_TWO_WON;
        } else {
            throw new IllegalStateException("Can't call pointWon on ADVANTAGE");
        }

        return State.ONGOING;
    }
}
