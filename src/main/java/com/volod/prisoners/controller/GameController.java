package com.volod.prisoners.controller;

import com.volod.prisoners.domain.exception.GameNotFoundException;
import com.volod.prisoners.domain.exception.GameRoomNotFoundException;
import com.volod.prisoners.domain.game.Decision;
import com.volod.prisoners.domain.id.GameId;
import com.volod.prisoners.domain.id.GameRoomId;
import com.volod.prisoners.services.GameRoomService;
import com.volod.prisoners.services.GameService;
import com.volod.prisoners.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GameController {

    // Services
    private final GameService gameService;
    private final GameRoomService gameRoomService;
    private final UserSessionService userSessionService;
    // WebSockets
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/games")
    public String getGames(Model model) {
        var games = this.gameService.getGames();
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/room/{roomId}")
    public String getRoom(@PathVariable("roomId") GameRoomId gameRoomId, Model model) throws GameRoomNotFoundException {
        var gameRoom = this.gameRoomService.get(gameRoomId);
        model.addAttribute("gameRoom", gameRoom);
        model.addAttribute("isRoundOver", gameRoom.roundOver().get());
        model.addAttribute("reversedDecisions", gameRoom.reversedDecisions());
        model.addAttribute("userId", this.userSessionService.getCurrentUser().id());
        return "room";
    }

    @PostMapping("/create-room")
    public String createRoom(@RequestParam("gameId") GameId gameId) throws GameNotFoundException {
        var gameRoom = this.gameRoomService.create(gameId);
        return "redirect:/room/" + gameRoom.id().value();
    }

    @MessageMapping("/play/{roomId}")
    public void saveDecision(@Payload Decision decision, @DestinationVariable("roomId") GameRoomId roomId) {
        this.gameRoomService.saveDecision(decision);
        this.simpMessagingTemplate.convertAndSend("/topic/room/" + roomId.value(), decision);
    }

}
