package Temp;

import Moduls.Users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luxiam on 8/24/2017.
 */
public class CreatUsers {
    public static void main(String[] args) {
        List<Users> TestList = new ArrayList<Users>();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

        final EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        Users user = new Users();
        user.setName("Logic McTester");
        user.setPassword("TestPW");
        user.setBio("I'm not a person I'm just a figment of my creators imagination.");
        user.setLocation("The Mainframe");
        user.setWebAddress("Localhost:5432");
        user.setFollowing(new ArrayList<Users>(){{
            add( entitymanager.find(Users.class, 352));}});

        entitymanager.persist(user);
        entitymanager.getTransaction().commit();

        entitymanager.close();
        emfactory.close();
    }
}
