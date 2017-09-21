package Moduls.DAO;

import Moduls.DAO.Interfaces.IMessageDAO;
import Moduls.Message;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Luxiam on 8/26/2017.
 */


@Stateless
public class MessageDAO implements IMessageDAO {

    @PersistenceContext
    private EntityManager entitymanager;


    public void persist(Message entity) {
            entitymanager.persist(entity);

    }

    public void remove(Message entity) {
            entitymanager.remove(entity);
    }

    public Message findById(int id) {
        return entitymanager.find(Message.class,id);
    }

    public List<Message> MessagesByOwner(int owner_id) {
        return entitymanager.createNamedQuery("Message.listByOwner", Message.class).setParameter("id",owner_id).getResultList();
    }

    public List<Message> getAllMessages() {
        return entitymanager.createNamedQuery("Message.getAllMessages", Message.class).getResultList();
    }

    public List<Message> getAllMessages(int number) {
        return entitymanager.createNamedQuery("Message.getAllMessages", Message.class).setMaxResults(number).getResultList();
    }

    private void setEntityManager(EntityManager entityManager){
        entitymanager = entityManager;
    }
}
