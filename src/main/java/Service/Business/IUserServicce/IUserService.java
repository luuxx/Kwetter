package Service.Business.IUserServicce;

import Moduls.Users;

import java.util.List;

public interface IUserService {
    Users findByUserName (String username);
    Users findById(int id);
    Users findByName(String name);
    List<Users> findAllFollowing(int user_id);
    long countAllFollowing(int user_id);
    long countAllFollowers(int user_id);
}
