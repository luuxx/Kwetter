package Moduls;

import Provider.MyJacksonJsonProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Luxiam on 8/23/2017.
 */

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Message.listByOwner", query = "select m from Message m INNER JOIN m.owner m2 WHERE m2.Id = :id"),
        @NamedQuery(name = "Message.getAllMessages", query = "select m from Message m ORDER BY m.Id desc ")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message{


    @javax.persistence.Id
    @GeneratedValue
    private int Id;

    @ManyToOne
    private Users owner;

    private String message;
    private int Likes;


    public Message(){}

    public Message (String JSONObj) throws JSONException{
        JSONObject single = new JSONObject(JSONObj);
        setId(single.getInt("id"));
        setMessage(single.getString("message"));
        setLikes(single.getInt("likes"));
        System.err.println(single.toString());
        try {
            setOwner(MyJacksonJsonProvider.MAPPER.readValue(single.toString(),Users.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CascadeOnDelete
    @OneToMany
    private List<Hashtag> hashtags;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
