package in.wimsyclimsy.plingo.connection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.wimsyclimsy.plingo.commons.KickoutRequest;
import in.wimsyclimsy.plingo.commons.PickCardRequest;
import in.wimsyclimsy.plingo.commons.ThrowCardRequest;
import in.wimsyclimsy.plingo.dao.UserDAO;
import in.wimsyclimsy.plingo.engine.PlingoEngine;


@Component 
// This is where we create the behavior for the WebSocket.
public class WebSocketHandler extends TextWebSocketHandler{
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PlingoEngine plingoEngine;
    @Autowired
    private UserDAO userDAO;

    Map<WebSocketSession,Pair<String,String>> sessionMap = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        // Get the message sent by the client
        EventPayload content = objectMapper.convertValue(message.getPayload(), EventPayload.class); 
        
        String userId = session.getHandshakeHeaders().get("X-User-Id").get(0);
        String roomCode = userDAO.getUser(userId).get().getRoomCode();

        switch (EventType.valueOf(content.getEventType())) {
            case THROW_CARD:
                ThrowCardRequest request = objectMapper.convertValue(content.getPayload(), ThrowCardRequest.class);
                plingoEngine.throwCard(roomCode, request); 
                break;
        
            case PICK_CARD:
                PickCardRequest pickCardRequest = objectMapper.convertValue(content.getPayload(), PickCardRequest.class);
                plingoEngine.pickCard(roomCode, pickCardRequest);
                break;
            
            case LEAVE_ROOM:
                plingoEngine.leaveRoom(roomCode, userId);
                break;
            
            case READY:
                plingoEngine.ready(roomCode, userId);
                break;
            
            case VERIFY_WINNER:
                plingoEngine.verifyWinner(roomCode, userId);
                break;
            
            case KICKOUT:
                KickoutRequest kickoutRequest = objectMapper.convertValue(content.getPayload(), KickoutRequest.class);
                plingoEngine.kickOut(roomCode, kickoutRequest);
                break;
            
            default:
                break;
        }
        // Send a response back to the client
        session.sendMessage(new TextMessage("Echo: " ));
    }

    @Override 
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = session.getHandshakeHeaders().get("X-User-Id").get(0);
        String roomCode = userDAO.getUser(userId).get().getRoomCode();
        userDAO.getUser(userId).get().setUserSession(session);
        sessionMap.put(session, Pair.<String, String>builder()
                                .first(userId)
                                .second(roomCode)
                                .build());
	}

    @Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session);
	}
  
}
