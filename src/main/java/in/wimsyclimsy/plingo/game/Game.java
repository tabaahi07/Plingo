package in.wimsyclimsy.plingo.game;
import java.util.List;
import java.util.Stack;

import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.game.Enums.Card;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    private String turn;
    private List<User> players;
    private Stack<Card> openCards;
    private Stack<Card> closeCards;
}
