package Service.RESTful;

import Moduls.DAO.Interfaces.IMessageDAO;
import Moduls.Link;
import Moduls.Message;
import Moduls.Users;
import Provider.Authenticator;
import Provider.JsonEnvlope.JsonBasicEnvelope;
import Provider.JsonEnvlope.JsonDataEnvelope;
import Provider.MyJacksonJsonProvider;
import org.primefaces.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luxiam on 8/27/2017.
 */

@Path("/message")
public class MessageServiceRest {

    @EJB
    IMessageDAO dao;

    private static final String BASE_URI = "https://localhost:8181/api";
    private static final String REST_URI = "/message";

    @GET
    @Produces("application/json")
    @Path("/owner/{Id}")
    public Response getByOwner(@PathParam("Id") int id) {
        JsonBasicEnvelope json = new JsonBasicEnvelope();
        List<Message> messageList = dao.MessagesByOwner(id);
        List<JsonDataEnvelope> dataEnvelopeList = new ArrayList<>();
        for (final Message message : messageList) {
            JsonDataEnvelope data = new JsonDataEnvelope();
            data.setData(message);
            data.setLink(new ArrayList<Link>(){{
                add(new Link(BASE_URI+REST_URI+"/id/"+ message.getId(),"self"));
                add(new Link(BASE_URI+REST_URI+"/removemessage/"+message.getId(),"remove"));
                add(new Link(BASE_URI+"/user/id/"+ message.getOwner().getId(),"author"));
            }});
            dataEnvelopeList.add(data);
        }
        json.setData(dataEnvelopeList);
        List<Link> linkList = new ArrayList<>();
        linkList.add(new Link(BASE_URI+REST_URI+"/owner/"+id,"self"));
        json.setLink(linkList);
        json.setStatus("SUCCESS");
        return Response.ok().entity(json).build();
    }

    @GET
    @Produces("application/json")
    @Path("/id/{Id}")
    public Response getMessageById(@PathParam("Id") int id) {
        JsonBasicEnvelope json = new JsonBasicEnvelope();
        final Message message = dao.findById(id);
        json.setData(message);
        json.setLink(new ArrayList<Link>(){{
            add(new Link(BASE_URI+REST_URI+"/id/"+ message.getId(),"self"));
            add(new Link(BASE_URI+REST_URI+"/removemessage/"+message.getId(),"remove"));
            add(new Link(BASE_URI+"/user/id/"+ message.getOwner().getId(),"author"));
        }});
        json.setStatus("SUCCESS");
        return Response.ok().entity(json).build();
    }

    @GET
    @Produces("application/json")
    @Path("/all/{limit}")
    public List<Message> getAll(@PathParam("limit") int limit) {
        List<Message> message = dao.getAllMessages(limit);
        return message;
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public List<Message> getAll() {
        List<Message> message = dao.getAllMessages();
        return message;
    }

    @GET
    @Produces("application/json")
    @Path("/user/{id}")
    public List<Message> MessagesByOwner(@PathParam("id") int owner_id) {
        return dao.MessagesByOwner(owner_id);
    }

    @POST
    @Produces("application/json")
    @Path("/sendmessage")
    public Response pressistNewMessage (@FormParam("likes") int likes, @FormParam("message") String message, @FormParam("owner_id") int owner_id) {

        //create message from form data
        JsonBasicEnvelope json = new JsonBasicEnvelope();
        final Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setLikes(likes);

        //Get user via rest API
        WebTarget resource = ClientBuilder.newClient().target(BASE_URI+"/user/id/"+owner_id).register(new Authenticator("Luxiam","admin"));
        String jsonMessage = resource.request(MediaType.APPLICATION_JSON).get(String.class);
        try {
            Users owner = MyJacksonJsonProvider.MAPPER.readValue(jsonMessage, Users.class);
            newMessage.setOwner(owner);

            dao.persist(newMessage);

            List<Link> linkList = new ArrayList<>();
            linkList.add(new Link(BASE_URI+REST_URI+"/id/"+newMessage.getId(),"self"));
            linkList.add(new Link(BASE_URI+REST_URI+"/removemessage/"+newMessage.getId(),"remove"));
            linkList.add(new Link(BASE_URI+"/user/owner/"+owner.getId(),"owner"));
            json.setData(newMessage);
            json.setLink(linkList);
            json.setStatus("SUCCESS");
            return Response.ok().entity(json).build();

        } catch (IOException e) {
            json.setStatus("FAILED");
            json.setErrorMsg(e.getMessage());

            e.printStackTrace();

            return Response.ok().entity(json).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/removemessage/{id}")
    public Response removeMessage(@PathParam("id") int id){
        JsonBasicEnvelope json = new JsonBasicEnvelope();

        WebTarget resource = ClientBuilder.newClient().target(BASE_URI+"/message/id/"+id).register(new Authenticator("Luxiam","admin"));
        String jsonMessage = resource.request(MediaType.APPLICATION_JSON).get(String.class);

        try {

            JSONObject jsonObject = new JSONObject(jsonMessage);
            Message message = MyJacksonJsonProvider.MAPPER.readValue(jsonObject.getJSONObject("data").toString(),Message.class);

            System.out.println(message.getMessage());

            dao.remove(message);

            json.setStatus("SUCCESS");
            List<Link> linkLink = new ArrayList<>();
            linkLink.add(new Link(BASE_URI+REST_URI+"/all/10","home"));
            json.setLink(linkLink);

            return Response.ok().entity(json).build();
        } catch (IOException e) {
            e.printStackTrace();
            json.setStatus("FAILED");
            json.setErrorMsg(e.getMessage());
            return Response.ok().entity(json).build();
        }
    }
}
