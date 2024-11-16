package in.wimsyclimsy.plingo.connection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventPayload {
    private String eventType;
    private String payload;
} 
