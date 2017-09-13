package Moduls;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.primefaces.json.JSONObject;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Luxiam on 8/23/2017.
 */

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Users.findByName", query = "select u from Users u where u.Name = :name"),
        @NamedQuery(name = "Users.findByUserName", query = "select u from Users u where u.Username = :name"),
        @NamedQuery(name = "Users.CountAllFollowers", query = "SELECT count(u2) FROM Users u1 INNER JOIN u1.Followers u2 WHERE u1.Id = :id"),
        @NamedQuery(name = "Users.CountAllFollowing", query = "SELECT count(u2) FROM Users u1 INNER JOIN u1.Following u2 WHERE u1.Id = :id"),
        @NamedQuery(name = "Users.findAllFollowing", query = "SELECT u2 FROM Users u1 INNER JOIN u1.Following u2 WHERE u1.Id = :id"),
})
public class Users {

    @Id
    @GeneratedValue
    private int Id;


    private String Username;
    private String Password;

    private String Name;
    private String Bio;
    private String Location;
    private String WebAddres;

    @OneToMany(fetch=FetchType.LAZY)
    @CascadeOnDelete
    @JoinTable(
            name = "users_users",
            joinColumns =
            @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "following_id", referencedColumnName = "id")

    )
    @JsonIgnore
    private List<Users> Following;

    @OneToMany(fetch=FetchType.LAZY)
    @CascadeOnDelete
    @JoinTable(
            name = "users_users",
            joinColumns =
            @JoinColumn(name = "following_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "users_id", referencedColumnName = "id")

    )
    @JsonIgnore
    private List<Users> Followers;

    public Users(JSONObject owner) {
        setName(owner.getString("name"));
        setUsername(owner.getString("username"));
        setId(owner.getInt("id"));
    }

    public Users() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getWebAddres() {
        return WebAddres;
    }

    public void setWebAddres(String webAddres) {
        WebAddres = webAddres;
    }

    @JsonIgnore
    public List<Users> getFollowing() {
        return Following;
    }

    public void setFollowing(List<Users> following) {
        Following = following;
    }

    @JsonIgnore
    public List<Users> getFollowers() {
        return Followers;
    }

    public void setFollowers(List<Users> followers) {
        Followers = followers;
    }
}
