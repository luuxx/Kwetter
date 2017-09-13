package Moduls.DAO.Interfaces;

import Moduls.Users;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Luxiam on 8/26/2017.
 */

@Local
public interface IUsersDAO {

    void persist(Users entity);

    void remove(Users entity);

    Users findById(int id);

    Users findByName(String name);

    Users findByUserName(String username);

    List<Users> findAllFollowing(int user_id);

    long countAllFollowing(int user_id);

    long countAllFollowers(int user_id);

    void detach(Users user);
}
