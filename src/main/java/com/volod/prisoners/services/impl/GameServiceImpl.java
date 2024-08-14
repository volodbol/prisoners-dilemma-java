package com.volod.prisoners.services.impl;

import com.volod.prisoners.domain.exception.GameNotFoundException;
import com.volod.prisoners.domain.game.Game;
import com.volod.prisoners.domain.id.GameId;
import com.volod.prisoners.services.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.nonNull;

@Service
public class GameServiceImpl implements GameService {

    private final ConcurrentHashMap<GameId, Game> games;

    public GameServiceImpl() {
        this.games = new ConcurrentHashMap<>();
        var game = Game.prisonersBasic();
        this.games.put(game.id(), game);
    }

    @Override
    public List<Game> getGames() {
        return new ArrayList<>(this.games.values());
    }

    @Override
    public Game get(GameId gameId) throws GameNotFoundException {
        var game = this.games.get(gameId);
        if (nonNull(game)) {
            return game;
        } else {
            throw new GameNotFoundException();
        }
    }
}
