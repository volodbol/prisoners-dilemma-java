package com.volod.prisoners.domain.game;

import com.volod.prisoners.domain.id.GameRoomId;
import com.volod.prisoners.domain.id.UserId;

public record Decision(
        GameRoomId gameRoomId,
        UserId userId,
        String value
) {
}
