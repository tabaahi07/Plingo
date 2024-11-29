package in.wimsyclimsy.plingo.commons;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import in.wimsyclimsy.plingo.game.Enums.Card;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder 
@Getter
@Setter
public class User {
    private String userId;
    private String userToken;
    private String userName;
    private String roomCode;
    private Boolean isReady;
    private List<Card> cards;
    private int kickoutCount;
    private WebSocketSession userSession;
}
