package in.wimsyclimsy.plingo.commons;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PickCardRequest {
    private Boolean isOpen;
    private String userId;
}
