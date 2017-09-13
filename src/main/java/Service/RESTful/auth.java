package Service.RESTful;

import Moduls.DAO.Interfaces.IUsersDAO;
import Moduls.Link;
import Moduls.Users;
import Provider.JsonEnvlope.JsonBasicEnvelope;
import Provider.JsonEnvlope.JsonDataEnvelope;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Luxiam on 8/27/2017.
 */
@Path("/auth")
public class auth {

    @EJB
    IUsersDAO dao;

    // The Java method will process HTTP GET requests
    @GET
    @Path("ping")
    public String ping() {
        return "pong";
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username, @FormParam("password") String password, @Context HttpServletRequest req) {

        JsonBasicEnvelope json = new JsonBasicEnvelope();
        JsonDataEnvelope data = new JsonDataEnvelope();
        if (req.getUserPrincipal() == null) {
            try {
                req.login(username, password);
                req.getServletContext().log("Authentication Demo: successfully logged in " + username);
            } catch (ServletException e) {
                e.printStackTrace();
                json.setStatus("FAILED");
                json.setErrorMsg("Authentication failed");
                return Response.ok().entity(json).build();
            }
        } else {
            req.getServletContext().log("Skip logged because already logged in: " + username);
        }
        //read the user data from db and return to caller
        json.setStatus("SUCCESS");

        final Users user = dao.findByUserName(username);
        req.getServletContext().log("Authentication Demo: successfully retrieved User Profile from DB for " + username);
        data.setData(user);
        data.setLink(new ArrayList<Link>()
        {{add(new Link()
            {{
                setHref("/user/"+user.getId());
                setRel("resource");
            }});
        }});
        json.setData(data);
        json.setLink(new ArrayList<Link>()
            {{
                add(new Link("/api/auth/login","self"));
            }}
        );

        //we don't want to send the hashed password out in the json response
        dao.detach(user);
        user.setPassword(null);
        return Response.ok().entity(json).build();
    }

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest req) {

        JsonBasicEnvelope json = new JsonBasicEnvelope();

        try {
            req.logout();
            json.setStatus("SUCCESS");
            req.getSession().invalidate();
        } catch (ServletException e) {
            e.printStackTrace();
            json.setStatus("FAILED");
            json.setErrorMsg("Logout failed on backend");
        }
        return Response.ok().entity(json).build();
    }
}

