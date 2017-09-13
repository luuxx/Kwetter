package Moduls;

import javax.persistence.*;

/**
 * Created by Luxiam on 8/26/2017.
 */

@Entity
@Table
@NamedQuery(name = "Hashtag.findTag", query = "Select h From Hashtag h WHERE h.Tag = :tag")
public class Hashtag {

    @Id
    @GeneratedValue
    private int Id;
    private String Tag;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }
}
