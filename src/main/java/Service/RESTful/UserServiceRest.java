package Service.RESTful;

import Moduls.Users;
import Service.Business.IUserServicce.IUserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Luxiam on 8/27/2017.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/user")
public class UserServiceRest{

    @Inject
    IUserService userService;

    // The Java method will process HTTP GET requests
    @GET
    @Path("/username/{username}")
    @Produces("application/json")
    public Users findByUserName(@PathParam("username") String username) {
         return userService.findByUserName(username);
    }

    @GET
    @Path("/id/{id}")
    @Produces("application/json")
    public Users findById(@PathParam("id") int id) {
        return userService.findById(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces("application/json")
    public Users findByName(@PathParam("name")String name) {
        return userService.findByName(name);
    }

    @GET
    @Path("/following/{id}")
    @Produces("application/json")
    public List<Users> findAllFollowing(@PathParam("id")int user_id) {
        return userService.findAllFollowing(user_id);
    }

    @GET
    @Path("/countFollowing/{id}")
    @Produces("application/json")
    public long countAllFollowing(@PathParam("id")int user_id) {
        return userService.countAllFollowing(user_id);
    }

    @GET
    @Path("/countFollowers/{id}")
    @Produces("application/json")
    public long countAllFollowers(@PathParam("id")int user_id) {
        return userService.countAllFollowers(user_id);
    }
}
