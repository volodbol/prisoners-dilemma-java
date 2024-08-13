package com.volod.prisoners.domain.game;

import com.volod.prisoners.domain.id.GameRoomId;
import com.volod.prisoners.domain.id.UserId;

public record Decision(
        GameRoomId gameRoomId,
        Integer round,
        UserId userId,
        String value
) {
}
