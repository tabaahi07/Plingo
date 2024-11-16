package in.wimsyclimsy.plingo.commons;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Builder
public class KickoutRequest {
    private String userId;
    private String kickoutId;
}
