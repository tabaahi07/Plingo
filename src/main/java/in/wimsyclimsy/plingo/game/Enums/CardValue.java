package in.wimsyclimsy.plingo.game.Enums;
import java.util.stream.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CardValue {
    A(1),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9),
    _10(10),
    J(11),
    Q(12),
    K(13);

    private int value ;
    // public static CardValue getCardValue(int value){
    //     return Stream.of(CardValue.values());
    // }
}
