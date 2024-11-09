package in.wimsyclimsy.plingo.commons;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinRoomRequest {
    private String roomCode;
}
