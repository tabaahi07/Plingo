package in.wimsyclimsy.plingo.dao;
import java.util.List;

import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.game.Game;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Room {
    private String roomCode;
    private Game game;
    private List<User> userList;
}
