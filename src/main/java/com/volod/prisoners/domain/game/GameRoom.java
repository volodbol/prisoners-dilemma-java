package com.volod.prisoners.domain.game;

import com.volod.prisoners.domain.id.GameRoomId;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.commons.collections4.queue.SynchronizedQueue;

import static org.apache.commons.collections4.queue.SynchronizedQueue.synchronizedQueue;

public record GameRoom(
        GameRoomId id,
        Game game,
        SynchronizedQueue<Decision> decisions
) {

    public static GameRoom of(Game game) {
        return new GameRoom(
                GameRoomId.random(),
                game,
                synchronizedQueue(new CircularFifoQueue<>(20))
        );
    }

}
