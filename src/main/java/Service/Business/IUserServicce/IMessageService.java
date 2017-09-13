package Service.Business.IUserServicce;

import Moduls.Message;

import java.io.IOException;
import java.util.List;

public interface IMessageService {
    public Message getMessageById(int id);
    public List<Message> getById(int id);
    public List<Message> getAll();
    public List<Message> getAll(int limit);
    public List<Message> MessagesByOwner(int owner_id);
    public Message pressistNewMessage (int likes, String message, int owner_id) throws IOException;
    public void removeMessage(int id);
}
