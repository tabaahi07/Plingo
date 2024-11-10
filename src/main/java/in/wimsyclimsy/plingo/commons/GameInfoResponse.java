package in.wimsyclimsy.plingo.commons;
import java.util.List;
import in.wimsyclimsy.plingo.game.Enums.Card;
import in.wimsyclimsy.plingo.game.Enums.CardValue;
import lombok.Builder;

@Builder
public class GameInfoResponse {
    private List<Player> players;
    private Boolean isMyTurn;
    private List<Card> myCards;
    private Card openCards;
    private CardValue trumpCard;

    @Builder
    public static class Player{
        String userId ;
        String userName;
    }
}
