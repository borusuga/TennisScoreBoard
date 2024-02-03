package com.example.tennisscoreboard.services.newScore;

import lombok.Getter;

@Getter
public abstract class GameScore<T> extends Score<T> {
    Integer serve;
}