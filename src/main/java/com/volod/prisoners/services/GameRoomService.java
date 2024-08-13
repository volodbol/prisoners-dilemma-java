package com.volod.prisoners.services;

import com.volod.prisoners.domain.exception.GameNotFoundException;
import com.volod.prisoners.domain.exception.GameRoomNotFoundException;
import com.volod.prisoners.domain.game.Decision;
import com.volod.prisoners.domain.game.GameRoom;
import com.volod.prisoners.domain.id.GameId;
import com.volod.prisoners.domain.id.GameRoomId;

public interface GameRoomService {
    GameRoom get(GameRoomId id) throws GameRoomNotFoundException;
    GameRoom create(GameId gameId) throws GameNotFoundException;
    void saveDecision(Decision decision);
}
