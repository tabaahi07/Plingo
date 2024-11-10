package in.wimsyclimsy.plingo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.wimsyclimsy.plingo.commons.GameInfoResponse;
import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.dao.Room;
import in.wimsyclimsy.plingo.dao.RoomDAO;
import in.wimsyclimsy.plingo.dao.UserDAO;
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

}
