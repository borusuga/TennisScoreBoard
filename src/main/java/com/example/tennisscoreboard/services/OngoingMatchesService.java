package com.example.tennisscoreboard.services;

import com.example.tennisscoreboard.models.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService(); // используется для реализации шаблона проектирования Singleton
    private final Map<UUID, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    private OngoingMatchesService() {
    }

    public static OngoingMatchesService getOngoingMatchesService() {
        return INSTANCE;
    }


    public void createNewMatch(UUID uuid, Player firstPlayer, Player secondPlayer, int setsInMatch) {
        CurrentMatch currentMatch = new CurrentMatch(uuid, firstPlayer, secondPlayer, setsInMatch);
        currentMatches.put(uuid, currentMatch);
    }

    public void remove(UUID uuid) {
        currentMatches.remove(uuid);
    }

    public CurrentMatch getCurrentMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }

}