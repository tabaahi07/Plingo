package in.wimsyclimsy.plingo.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration  // Marks this as a configuration file
@EnableWebSocket // Enables WebSocket support in the application
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    // The registry object is used to register WebSocket endpoints and associate them with WebSocket handlers.
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws")
        .setAllowedOrigins("*") ; // allow all origins
        
        // specifies which origins (domains) are allowed to connect to your WebSocket endpoint.
    }
}
