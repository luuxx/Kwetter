package Moduls.DAO;

import Moduls.DAO.Interfaces.IUsersDAO;
import Moduls.Users;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Luxiam on 8/26/2017.
 */

@Stateless
public class UsersDAO implements IUsersDAO {

    @PersistenceContext
    protected EntityManager entitymanager;

    public void persist(Users entity) {
        entitymanager.persist(entity);
    }

    public void remove(Users entity) {
        entitymanager.remove(entity);
    }

    public Users findById(int id) {
        return entitymanager.find(Users.class, id);
    }

    public Users findByName(String name) {
        return entitymanager.createNamedQuery("Users.findByName", Users.class).setParameter("name", name).getSingleResult();
    }

    public Users findByUserName(String username) {
        return entitymanager.createNamedQuery("Users.findByUserName", Users.class).setParameter("name", username).getSingleResult();
    }

    public List<Users> findAllFollowing(int user_id) {
        return entitymanager.createNamedQuery("Users.findAllFollowing", Users.class).setParameter("id", user_id).getResultList();
    }

    public long countAllFollowing(int user_id) {
        return entitymanager.createNamedQuery("Users.CountAllFollowing", Long.class).setParameter("id", user_id).getSingleResult();
    }

    public long countAllFollowers(int user_id) {
        return entitymanager.createNamedQuery("Users.CountAllFollowers", Long.class).setParameter("id", user_id).getSingleResult();
    }

    public void detach(Users user) {
        entitymanager.detach(user);
    }
}
