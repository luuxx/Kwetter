package View;

import Moduls.Users;
import Provider.Authenticator;
import Provider.MyJacksonJsonProvider;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Luxiam on 8/27/2017.
 */

@ManagedBean(eager = true)
@Named
@RequestScoped
public class UserController implements Serializable {


    Client client = ClientBuilder.newClient().register(new Authenticator("Luxiam", "admin"));

    private static final String BASE_URI = "https://localhost:8181/KwetterVakantie_war_exploded/api";

    public Users findByUsername(String username){
        WebTarget resource = client.target(BASE_URI+"/user/username/"+username);
        String jsonMessage = resource.request(MediaType.APPLICATION_JSON).get(String.class);

        return getUsersFromJson(jsonMessage);
    }

    public Users findById(int id){
        WebTarget resource = client.target(BASE_URI+"/user/id/"+id);
        String jsonMessage = resource.request(MediaType.APPLICATION_JSON).get(String.class);

        return getUsersFromJson(jsonMessage);

    }

    public List<Users> getFollowing(int id){
        WebTarget resource = client.target(BASE_URI+"/user/following/"+id);
        JSONArray jsonMessage = new JSONArray(resource.request(MediaType.APPLICATION_JSON).get(String.class));
        try {
            return getUserListFromJson(jsonMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Users getUsersFromJson(String json){
        try {
            JSONObject single = new JSONObject(json);
            return MyJacksonJsonProvider.MAPPER.readValue(single.toString(),Users.class);
        }
        catch (JSONException | IOException jsonE){
            jsonE.printStackTrace();
        }
        return null;
    }

    private List<Users> getUserListFromJson(JSONArray json) throws IOException {
        try {
            List<Users> messageList = MyJacksonJsonProvider.MAPPER.readValue(json.toString(), List.class);
            System.err.println(messageList.toString());
            return messageList;
        }
        catch (JSONException jsonE){
            System.err.println(jsonE.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
