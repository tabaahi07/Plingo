package in.wimsyclimsy.plingo.commons;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomInfoResponse {
    List<RoomPlayer> players;
    @Data
    @Builder
    public static class RoomPlayer{
        private String userName;
        private String userId;
        private Boolean ready;
    }
}
