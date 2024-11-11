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
import lombok.Builder;

@Builder
@Service
public class GameService {
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

}
