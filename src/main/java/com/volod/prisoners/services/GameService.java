package com.volod.prisoners.services;

import com.volod.prisoners.domain.exception.GameNotFoundException;
import com.volod.prisoners.domain.game.Game;
import com.volod.prisoners.domain.id.GameId;

import java.util.List;

public interface GameService {
    List<Game> getGames();
    Game get(GameId gameId) throws GameNotFoundException;
}
