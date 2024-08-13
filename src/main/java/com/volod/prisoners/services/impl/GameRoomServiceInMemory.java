package com.volod.prisoners.services.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.volod.prisoners.domain.exception.GameNotFoundException;
import com.volod.prisoners.domain.exception.GameRoomNotFoundException;
import com.volod.prisoners.domain.game.Decision;
import com.volod.prisoners.domain.game.GameRoom;
import com.volod.prisoners.domain.id.GameId;
import com.volod.prisoners.domain.id.GameRoomId;
import com.volod.prisoners.services.GameRoomService;
import com.volod.prisoners.services.GameService;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static java.util.Objects.nonNull;

@Service
public class GameRoomServiceInMemory implements GameRoomService {

    private final GameService gameService;

    private final Cache<GameRoomId, GameRoom> gameRoomCache;

    public GameRoomServiceInMemory(
            GameService gameService
    ) {
        this.gameService = gameService;
        this.gameRoomCache = Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(30)).build();
    }

    @Override
    public GameRoom get(GameRoomId id) throws GameRoomNotFoundException {
        var gameRoom = this.gameRoomCache.getIfPresent(id);
        if (nonNull(gameRoom)) {
            return gameRoom;
        } else {
            throw new GameRoomNotFoundException();
        }
    }

    @Override
    public GameRoom create(GameId gameId) throws GameNotFoundException {
        var game = this.gameService.get(gameId);
        var gameRoom = GameRoom.of(game);
        this.gameRoomCache.put(gameRoom.id(), gameRoom);
        return gameRoom;
    }

    @Override
    public void saveDecision(Decision decision) {
        try {
            var gameRoom = this.get(decision.gameRoomId());
            gameRoom.decisions().add(decision);
        } catch (GameRoomNotFoundException ignored) {
            // ignored
        }
    }

}
