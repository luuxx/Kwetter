package Moduls.DAO.Interfaces;

import Moduls.Message;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Luxiam on 8/26/2017.
 */

@Local
public interface IMessageDAO {
    void persist(Message entity);

    void remove(Message entity);

    Message findById(int id);

    List<Message> MessagesByOwner(int owner_id);

    List<Message> getAllMessages();

    List<Message> getAllMessages(int number);
}
