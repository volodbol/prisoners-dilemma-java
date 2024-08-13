package com.volod.prisoners.domain.exception;

public class GameRoomNotFoundException extends Exception {

    public GameRoomNotFoundException() {
        super("This game room does not exist");
    }

}
