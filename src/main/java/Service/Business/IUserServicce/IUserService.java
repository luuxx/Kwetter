package Service.Business.IUserServicce;

import Moduls.Users;

import java.util.List;

public interface IUserService {
    public Users findByUserName (String username);
    public Users findById(int id);
    public Users findByName(String name);
    public List<Users> findAllFollowing(int user_id);
    public long countAllFollowing(int user_id);
    public long countAllFollowers(int user_id);
}
