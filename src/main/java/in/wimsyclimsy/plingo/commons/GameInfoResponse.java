package in.wimsyclimsy.plingo.commons;
import java.util.List;
import in.wimsyclimsy.plingo.game.Enums.Card;

public class GameInfoResponse {
    private List<Player> players;
    private Boolean isMyTurn;
    private List<Card> myCards;
    private Card openCards;
    static class Player{
        String userId ;
        String userName;
    }
}
