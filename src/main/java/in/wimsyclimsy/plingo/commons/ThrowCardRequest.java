package in.wimsyclimsy.plingo.commons;

import in.wimsyclimsy.plingo.game.Enums.Card;
import lombok.Builder;
import lombok.Data;
@Data
@Builder

public class ThrowCardRequest {
    private String userId;
    private Card thrownCard;
}
