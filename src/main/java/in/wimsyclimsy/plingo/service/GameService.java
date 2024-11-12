package in.wimsyclimsy.plingo.service;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.wimsyclimsy.plingo.commons.GameInfoResponse;
import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.dao.Room;
import in.wimsyclimsy.plingo.dao.RoomDAO;
import in.wimsyclimsy.plingo.dao.UserDAO;
import in.wimsyclimsy.plingo.game.Enums.Card;
import in.wimsyclimsy.plingo.game.Enums.CardValue;
import in.wimsyclimsy.plingo.game.Enums.Suit;
import lombok.Builder;

@Builder
@Service
public class GameService {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private UserDAO userDAO;

    public Optional<GameInfoResponse> getGameInfo(String roomCode , String userId){
        Optional<Room> room = roomDAO.getRoom(roomCode);
        Optional<User> myUser = userDAO.getUser(userId);
        if (room.isEmpty() || myUser.isEmpty()) return Optional.empty();

        List<GameInfoResponse.Player> players = room.get().getUserList()
                .stream()
                .map(user -> GameInfoResponse.Player.builder()
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .build())
                .toList();

        return Optional.of(GameInfoResponse.builder().players(players)
                        .isMyTurn(room.get().getGame().getTurn() == userId)
                        .myCards(myUser.get().getCards())
                        .openCards(room.get().getGame().getOpenCards().peek())
                        .trumpCard(room.get().getGame().getTrumpCard())
                        .build());
    }


    public CRUDStatus pickCard(String userId , String roomCode , Boolean isOpen){
        Optional<User> user = userDAO.getUser(userId);
        Optional<Room> room = roomDAO.getRoom(roomCode);
        if(user.isEmpty() || room.isEmpty()) return CRUDStatus.REJECTED;
        Card pickedCard ;
        // updating game in room
        if(isOpen){
            Stack<Card> openCards = room.get().getGame().getOpenCards();
            pickedCard = room.get().getGame().getOpenCards().peek();
            openCards.pop();
        }
        else{
            Stack<Card> closedCards = room.get().getGame().getCloseCards();
            pickedCard = room.get().getGame().getCloseCards().peek();
            closedCards.pop();
        }
        // updating user
        List<Card> userCards = user.get().getCards();
        userCards.add(pickedCard);
        // udating in db
        userDAO.updateUser(userId, user.get());
        roomDAO.updateRoom(roomCode, room.get());
        return CRUDStatus.APPROVED;
    }

    public CRUDStatus throwCard(String userId , String roomCode , Card thrownCard){
        Optional<User> user = userDAO.getUser(userId);
        Optional<Room> room = roomDAO.getRoom(roomCode);
        if(user.isEmpty() || room.isEmpty()) return CRUDStatus.REJECTED;
        // updating user
        List<Card> userCards = user.get().getCards();
        userCards.remove(thrownCard);
        // updating game in room
        Stack<Card> openCards = room.get().getGame().getOpenCards();
        openCards.push(thrownCard);
        // udating in db
        userDAO.updateUser(userId, user.get());
        roomDAO.updateRoom(roomCode, room.get());
        return CRUDStatus.APPROVED;
    }

    public Boolean verifyWinner(String roomCode, String userId){
        List<Card> playerCards = userDAO.getUser(userId).get().getCards();
        CardValue trumpValue = roomDAO.getRoom(roomCode).get().getGame().getTrumpCard();
        
        if (playerCards.size() != 10) {
            return false;
        }

        // Step 1: Check the first four cards for increasing order and same suit, with trump card substitution
        Suit suitForSequence = null;
        int sequenceCount = 0; // Tracks cards in increasing sequence

        for (int i = 0; i < 4; i++) {
            Card currentCard = playerCards.get(i);

            // Determine the suit if we encounter the first non-trump card
            if (suitForSequence == null && currentCard.getValue() != trumpValue) {
                suitForSequence = currentCard.getSuit();
            }

            // Check if the card matches the sequence suit or is a trump card
            if (currentCard.getSuit() == suitForSequence || currentCard.getValue() == trumpValue) {
                if (i > 0) { // Check sequence order starting from the second card
                    Card previousCard = playerCards.get(i - 1);
                    boolean inSequenceOrTrump = (previousCard.getValue().ordinal() + 1 == currentCard.getValue().ordinal() 
                                                 || currentCard.getValue() == trumpValue);
                    
                    if (!inSequenceOrTrump) {
                        return false; // Out of sequence
                    }
                }
                sequenceCount++;
            } else {
                return false; // Card does not match sequence suit or is not trump
            }
        }

        if (sequenceCount < 4) {
            return false; // Insufficient cards in sequence with trump support
        }

        // Step 2: Check the next set of three cards for the same value, allowing for trump card substitution
        CardValue secondSetValue = null;
        int secondSetCount = 0;
        for (int i = 4; i < 7; i++) {
            Card currentCard = playerCards.get(i);
            // Determine the set value if we encounter the first non-trump card
            if (secondSetValue == null && currentCard.getValue() != trumpValue) {
                secondSetValue = currentCard.getValue();
            }

            if (currentCard.getValue() == secondSetValue || currentCard.getValue() == trumpValue) {
                secondSetCount++;
            } else {
                return false;
            }
        }

        // If we don't have 3 cards in the second set with the same value (including trump), return false
        if (secondSetCount < 3) return false;

        // Step 3: Check the last set of three cards for the same value, allowing for trump card substitution
        CardValue thirdSetValue = null;
        int thirdSetCount = 0;
        for (int i = 7; i < 10; i++) {
            Card currentCard = playerCards.get(i);
            // Determine the set value if we encounter the first non-trump card
            if (thirdSetValue == null && currentCard.getValue() != trumpValue) {
                thirdSetValue = currentCard.getValue();
            }

            if (currentCard.getValue() == thirdSetValue || currentCard.getValue() == trumpValue) {
                thirdSetCount++;
            } else {
                return false;
            }
        }

        // If we don't have 3 cards in the third set with the same value (including trump), return false
        return thirdSetCount == 3;
    }


    public CRUDStatus kickoutUser(String roomCode, String userId , String kickOutId){
        Optional<User> kickout = userDAO.getUser(kickOutId);
        Optional<User> user = userDAO.getUser(userId);
        Optional<Room> room = roomDAO.getRoom(roomCode);
        if(user.isEmpty() || room.isEmpty() || kickout.isEmpty()) return CRUDStatus.REJECTED;

        // updating kickoutCount of a kickOutUser in the users list
        int kickoutCount = kickout.get().getKickoutCount();
        userDAO.updateUser(kickOutId, User.builder()
                    .userId(kickOutId)
                    .userName(kickout.get().getUserName())
                    .userToken(kickout.get().getUserToken())
                    .cards(kickout.get().getCards())
                    .isReady(kickout.get().getIsReady())
                    .roomCode(kickout.get().getRoomCode())
                    .kickoutCount(kickoutCount+1)
                    .build());

        // If all other members in room approved kickout , then user will leave the room
        if(kickoutCount == roomDAO.getRoom(roomCode).get().getUserList().size()) roomService.leaveRoom(kickOutId, roomCode);

        return CRUDStatus.APPROVED;
    }
}
