package Provider.JsonEnvlope;

import Moduls.Link;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

public class JsonBasicEnvelope {

    private static final float version = 1.0f;

    private String status;
    private String errorMsg;
    private Map<String, Object> fieldErrors;
    private Object data;
    private List<Link> link;

    public JsonBasicEnvelope() {
    }

    public JsonBasicEnvelope(String status) {
        this.status = status;
    }

    @JsonIgnore
    public float getVersion() {
        return JsonBasicEnvelope.version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Map<String, Object> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, Object> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Link> getlink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }
}
