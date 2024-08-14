package com.volod.prisoners.domain.game;

import com.volod.prisoners.domain.id.GameId;
import org.apache.commons.lang3.tuple.Pair;

public record Game(
        GameId id,
        String name,
        String description,
        Pair<String, String> decisions
) {

    public static Game prisonersBasic() {
        return Game.of(
                "Prisoners Basic",
                """
                        Always Cooperate: Always chooses to cooperate, regardless of the opponent's previous moves.
                        Always Defect: Always chooses to defect, no matter what the opponent does.
                        Tit for Tat: Starts by cooperating, then mimics the opponent's previous move.
                        Grim Trigger: Cooperates until the opponent defects, then defects for the rest of the game.
                        Random: Randomly chooses to cooperate or defect with a set probability for each.
                        Tit for Two Tats: Cooperates unless the opponent defects twice in a row, then it defects.
                        Pavlov: Repeats its previous move if it was rewarded (both cooperated or both defected), otherwise switches.
                        Soft Grudger: Cooperates until the opponent defects, then retaliates with a defect but eventually forgives and returns to cooperation.""",
                Pair.of("Cooperate", "Defect")
        );
    }

    public static Game of(
            String name,
            String description,
            Pair<String, String> decisions
    ) {
        return new Game(
                GameId.random(),
                name,
                description,
                decisions
        );
    }

}
