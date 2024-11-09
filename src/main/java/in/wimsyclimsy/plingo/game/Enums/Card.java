package in.wimsyclimsy.plingo.game.Enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Card {
    DIAMOND_A(1,Suit.DIAMOND,CardValue.A);


    private int cardNumber;
    private Suit suit;
    private CardValue value;

}
