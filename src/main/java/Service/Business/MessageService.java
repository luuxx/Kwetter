package Service.Business;

import Moduls.DAO.Interfaces.IMessageDAO;
import Moduls.Message;
import Moduls.Users;
import Provider.Authenticator;
import Provider.MyJacksonJsonProvider;
import Service.Business.IUserServicce.IMessageService;

import javax.ejb.EJB;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by Luxiam on 8/30/2017.
 */
public class MessageService implements IMessageService{

    private static final String BASE_URI = "https://localhost:8181/api";
    private static final String REST_URI = "/message";

    @EJB
    IMessageDAO dao;

    @Override
    public Message getMessageById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Message> getById(int id) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return dao.getAllMessages();
    }

    @Override
    public List<Message> getAll(int limit) {
        return dao.getAllMessages(limit);
    }

    @Override
    public List<Message> MessagesByOwner(int owner_id) {
        return dao.MessagesByOwner(owner_id);
    }

    @Override
    public Message pressistNewMessage(int likes, String message, int owner_id) throws IOException {
        final Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setLikes(likes);

        //Get user via rest API
        WebTarget resource = ClientBuilder.newClient().target(BASE_URI+"/user/id/"+owner_id).register(new Authenticator("Luxiam","admin"));
        String jsonMessage = resource.request(MediaType.APPLICATION_JSON).get(String.class);
        Users owner = MyJacksonJsonProvider.MAPPER.readValue(jsonMessage, Users.class);
        newMessage.setOwner(owner);
        dao.persist(newMessage);
        return newMessage;
    }

    @Override
    public void removeMessage(int id) {

    }
}
