package com.example.tennisscoreboard.services.newScore;

import lombok.Getter;

@Getter
public enum GamePoints {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    public GamePoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException("Can't call next() on ADVANTAGE");
        } else {
            return GamePoints.values()[this.ordinal() + 1];
        }
    }

    private final String pointCode;

    GamePoints(String pointCode) {
        this.pointCode = pointCode;
    }

}