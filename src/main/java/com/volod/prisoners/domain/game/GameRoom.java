package com.volod.prisoners.domain.game;

import com.volod.prisoners.domain.id.GameRoomId;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.commons.collections4.queue.SynchronizedQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.apache.commons.collections4.queue.SynchronizedQueue.synchronizedQueue;

public record GameRoom(
        GameRoomId id,
        Game game,
        AtomicBoolean roundOver,
        SynchronizedQueue<Decision> decisions
) {

    public static GameRoom of(Game game) {
        return new GameRoom(
                GameRoomId.random(),
                game,
                new AtomicBoolean(true),
                synchronizedQueue(new CircularFifoQueue<>(10))
        );
    }

    public List<Decision> reversedDecisions() {
        var reversedDecisions = new ArrayList<>(this.decisions);
        Collections.reverse(reversedDecisions);
        return reversedDecisions;
    }

    public void add(Decision decision) {
        this.decisions.add(decision);
        this.roundOver.set(!this.roundOver.get());
    }
}
