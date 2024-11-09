package in.wimsyclimsy.plingo.commons;
import java.util.List;
import in.wimsyclimsy.plingo.game.Enums.Card;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String userId;
    private String userToken;
    private String userName;
    private List<Card> cards;
}
