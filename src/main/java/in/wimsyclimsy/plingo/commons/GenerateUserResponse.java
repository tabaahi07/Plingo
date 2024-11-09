package in.wimsyclimsy.plingo.commons;
import lombok.*;

@Data
@Builder
public class GenerateUserResponse {
    private String userToken;
    private String userId;
    private String userName;
}
