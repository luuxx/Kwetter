package Service.Business;

import Moduls.DAO.Interfaces.IUsersDAO;
import Moduls.Users;
import Service.Business.IUserServicce.IUserService;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by Luxiam on 8/30/2017.
 */
public class UserService implements IUserService {

    @EJB
    IUsersDAO dao;

    @Override
    public Users findByUserName(String username) {
        return dao.findByUserName(username);
    }

    @Override
    public Users findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Users findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public List<Users> findAllFollowing(int user_id) {
        return dao.findAllFollowing(user_id);
    }

    @Override
    public long countAllFollowing(int user_id) {
        return dao.countAllFollowing(user_id);
    }

    @Override
    public long countAllFollowers(int user_id) {
        return dao.countAllFollowers(user_id);
    }
}
