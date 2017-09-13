package Provider.JsonEnvlope;

import Moduls.Link;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Luxiam on 8/29/2017.
 */
public class JsonDataEnvelope {

    @JsonProperty("context")
    private Object data;
    private List<Link> linkList;
    private Link link;

    public JsonDataEnvelope (){

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @JsonProperty("link")
    public List<Link> getLinks() {
        return linkList;
    }

    public void setLink(List<Link> link) {
        this.linkList = link;
    }

    public Link getLink() {
        return link;
    }

    public void setLink (Link link) {
        this.link = link;
    }
}
