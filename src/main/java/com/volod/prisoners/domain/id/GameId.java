package com.volod.prisoners.domain.id;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public record GameId(String value) {

    @JsonCreator
    public static GameId of(String value) {
        return new GameId(value);
    }

    public static GameId random() {
        return new GameId(UUID.randomUUID().toString());
    }

}
