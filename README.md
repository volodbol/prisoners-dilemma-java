# prisoners-dilemma-java

It's simple in-memory game server that allows for two players to repeatedly play the Prisoner's Classic game against 
each other employing different strategies to maximize their own payoff while considering the potential outcomes of their
opponent's decisions.

Players can play using following strategies:

- Always Cooperate: Always chooses to cooperate, regardless of the opponent's previous moves.
- Always Defect: Always chooses to defect, no matter what the opponent does.
- Tit for Tat: Starts by cooperating, then mimics the opponent's previous move.
- Grim Trigger: Cooperates until the opponent defects, then defects for the rest of the game.
- Random: Randomly chooses to cooperate or defect with a set probability for each.
- Tit for Two Tats: Cooperates unless the opponent defects twice in a row, then it defects.
- Pavlov: Repeats its previous move if it was rewarded (both cooperated or both defected), otherwise switches.
- Soft Grudger: Cooperates until the opponent defects, then retaliates with a defect but eventually forgives and returns
to cooperation.

For my opinion, Tit for Tat is one of the best strategies for iterated Prisoner's Dilemma games. 

- Effectiveness: It’s simple yet effective; it promotes cooperation and punishes defection, while being forgiving.
- Balanced: It balances retaliation with cooperation, making it robust against a wide range of strategies.
- Adaptability: It can adjust to different opponent strategies and is effective in encouraging a cooperative environment.

[API Performance Results](Prisoner's-Web-Server-Performance-Results.pdf)

---

To run application simply execute via shell terminal 
```shell
./mvnw spring-boot:run 
```
and open
```
localhost:8080
```
in web browser