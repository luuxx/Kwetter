package Temp;

import Moduls.Hashtag;
import Moduls.Message;
import Moduls.Users;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Luxiam on 8/26/2017.
 */
public class CreatMessage {

    public static void main(String[] args) {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Message message = new Message();
        message.setLikes(0);
        message.setMessage(" Please Anyone out there? :( N0 I M3an 1t #HELPIMSTUCK");

        Users owner = entitymanager.find(Users.class, 352);
        message.setOwner(owner);

        Hashtag hashtag = new Hashtag();
        Query query = entitymanager.createNamedQuery("Hashtag.findTag");
        query.setParameter("tag", "#HELPIMSTUCK");
        try {
            hashtag = (Hashtag) query.getSingleResult();
        } catch (NoResultException e) {
            hashtag = new Hashtag();
            hashtag.setTag("#HELPIMSTUCK");
        }

        entitymanager.persist(hashtag);

        final Hashtag finalHashtag = hashtag;
        message.setHashtags(new ArrayList<Hashtag>() {{
            add(finalHashtag);
        }});

        entitymanager.persist(message);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }
}
