package View;

import Moduls.Message;
import Provider.Authenticator;
import Provider.MyJacksonJsonProvider;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import java.util.List;

/**
 * Created by Luxiam on 8/27/2017.
 */

@ManagedBean(eager = true)
@Named
@RequestScoped
public class MessageService {
    Client client = ClientBuilder.newClient().register(new Authenticator("Luxiam", "admin"));

    private static final String BASE_URI = "https://localhost:8181/KwetterVakantie_war_exploded/api";

    public Message getMessage(){
        WebTarget resource = client.target(BASE_URI+"/message/all/1");
        String jsonMessage = resource.request(MediaType.APPLICATION_JSON).get(String.class);
        try {
            JSONArray jobj = new JSONArray(jsonMessage);
            JSONObject single = jobj.getJSONObject(0);
            Message message = MyJacksonJsonProvider.MAPPER.readValue(single.toString(),Message.class);
            return message;
        }
        catch (JSONException jsonE){
            jsonE.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Message> getAllMessages(){
        WebTarget resource = client.target(BASE_URI+"/message/all");
        JSONArray jsonMessage = new JSONArray(resource.request(MediaType.APPLICATION_JSON).get(String.class));

        try {
            List<Message> messageList = MyJacksonJsonProvider.MAPPER.readValue(jsonMessage.toString(),List.class);
            System.err.println(messageList.toString());
            return messageList;
        }
        catch (JSONException jsonE){
            System.err.println(jsonE.toString());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Message> getMessagesOfUser(int id){
        WebTarget resource = client.target(BASE_URI+"/message/user/"+id);
        JSONArray jsonMessage = new JSONArray(resource.request(MediaType.APPLICATION_JSON).get(String.class));

        try {
            List<Message> messageList = MyJacksonJsonProvider.MAPPER.readValue(jsonMessage.toString(),List.class);
            System.err.println(messageList.toString());
            return messageList;
        }
        catch (JSONException jsonE){
            System.err.println(jsonE.toString());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
