package com.volod.prisoners.domain.id;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public record GameRoomId(String value) {

    @JsonCreator
    public static GameRoomId of(String value) {
        return new GameRoomId(value);
    }

    public static GameRoomId random() {
        return new GameRoomId(UUID.randomUUID().toString());
    }

}
