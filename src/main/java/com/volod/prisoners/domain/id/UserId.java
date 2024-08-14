package com.volod.prisoners.domain.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public record UserId(String value) {

    @JsonCreator
    public static UserId of(String value) {
        return new UserId(value);
    }

}
