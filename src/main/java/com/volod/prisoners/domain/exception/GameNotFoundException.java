package com.volod.prisoners.domain.exception;

public class GameNotFoundException extends Exception {

    public GameNotFoundException() {
        super("Game not found");
    }
}
