package in.wimsyclimsy.plingo.game.Enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Card {
    DIAMOND_A(1, Suit.DIAMOND, CardValue.A),
    DIAMOND_2(2, Suit.DIAMOND, CardValue._2),
    DIAMOND_3(3, Suit.DIAMOND, CardValue._3),
    DIAMOND_4(4, Suit.DIAMOND, CardValue._4),
    DIAMOND_5(5, Suit.DIAMOND, CardValue._5),
    DIAMOND_6(6, Suit.DIAMOND, CardValue._6),
    DIAMOND_7(7, Suit.DIAMOND, CardValue._7),
    DIAMOND_8(8, Suit.DIAMOND, CardValue._8),
    DIAMOND_9(9, Suit.DIAMOND, CardValue._9),
    DIAMOND_10(10, Suit.DIAMOND, CardValue._10),
    DIAMOND_J(11, Suit.DIAMOND, CardValue.J),
    DIAMOND_Q(12, Suit.DIAMOND, CardValue.Q),
    DIAMOND_K(13, Suit.DIAMOND, CardValue.K),

    HEART_A(14, Suit.HEART, CardValue.A),
    HEART_2(15, Suit.HEART, CardValue._2),
    HEART_3(16, Suit.HEART, CardValue._3),
    HEART_4(17, Suit.HEART, CardValue._4),
    HEART_5(18, Suit.HEART, CardValue._5),
    HEART_6(19, Suit.HEART, CardValue._6),
    HEART_7(20, Suit.HEART, CardValue._7),
    HEART_8(21, Suit.HEART, CardValue._8),
    HEART_9(22, Suit.HEART, CardValue._9),
    HEART_10(23, Suit.HEART, CardValue._10),
    HEART_J(24, Suit.HEART, CardValue.J),
    HEART_Q(25, Suit.HEART, CardValue.Q),
    HEART_K(26, Suit.HEART, CardValue.K),

    CLUB_A(27, Suit.CLUB, CardValue.A),
    CLUB_2(28, Suit.CLUB, CardValue._2),
    CLUB_3(29, Suit.CLUB, CardValue._3),
    CLUB_4(30, Suit.CLUB, CardValue._4),
    CLUB_5(31, Suit.CLUB, CardValue._5),
    CLUB_6(32, Suit.CLUB, CardValue._6),
    CLUB_7(33, Suit.CLUB, CardValue._7),
    CLUB_8(34, Suit.CLUB, CardValue._8),
    CLUB_9(35, Suit.CLUB, CardValue._9),
    CLUB_10(36, Suit.CLUB, CardValue._10),
    CLUB_J(37, Suit.CLUB, CardValue.J),
    CLUB_Q(38, Suit.CLUB, CardValue.Q),
    CLUB_K(39, Suit.CLUB, CardValue.K),

    SPADE_A(40, Suit.SPADE, CardValue.A),
    SPADE_2(41, Suit.SPADE, CardValue._2),
    SPADE_3(42, Suit.SPADE, CardValue._3),
    SPADE_4(43, Suit.SPADE, CardValue._4),
    SPADE_5(44, Suit.SPADE, CardValue._5),
    SPADE_6(45, Suit.SPADE, CardValue._6),
    SPADE_7(46, Suit.SPADE, CardValue._7),
    SPADE_8(47, Suit.SPADE, CardValue._8),
    SPADE_9(48, Suit.SPADE, CardValue._9),
    SPADE_10(49, Suit.SPADE, CardValue._10),
    SPADE_J(50, Suit.SPADE, CardValue.J),
    SPADE_Q(51, Suit.SPADE, CardValue.Q),
    SPADE_K(52, Suit.SPADE, CardValue.K);

    private int cardNumber;
    private Suit suit;
    private CardValue value;

}
