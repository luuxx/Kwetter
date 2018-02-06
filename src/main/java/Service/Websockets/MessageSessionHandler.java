package Service.Websockets;

import Moduls.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class MessageSessionHandler {
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Message> messages = new HashSet<>();

    public void addSession(Session session) {

        sessions.add(session);
        Logger.getLogger(MessageSessionHandler.class.getName()).log(Level.INFO,"Added new Session.");
        sendToSession(session,"Hello Sir");
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public void sendToAllConnectedSessions(String message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(MessageSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
