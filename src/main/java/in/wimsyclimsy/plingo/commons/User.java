package in.wimsyclimsy.plingo.commons;
import java.util.List;
import in.wimsyclimsy.plingo.game.Enums.Card;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class User {
    private String userId;
    private String userToken;
    private String userName;
    private String roomCode;
    private Boolean isReady;
    private List<Card> cards;
}
