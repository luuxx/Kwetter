import Provider.MyJacksonJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/api")
//The java class declares root resource and provider classes
public class MyApplication extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        h.add( MyJacksonJsonProvider.class);
        return h;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();

        properties.put("jersey.config.server.wadl.disableWadl", true);
        properties.put("jersey.config.server.provider.packages", "Service");


        return properties;
    }
}