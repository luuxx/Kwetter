package RESTServices

import io.restassured.RestAssured
import io.restassured.response.Response
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.is;

class MessageServiceTest extends Specification{

    def setup() {
        RestAssured.port = 8181
        RestAssured.baseURI = "https://localhost"
        RestAssured.basePath = "/api"
    }

    def "Check if REST Service is running"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("/message/owner/352")

        then:
        r.then().statusCode(200)
    }

    def "Get message by owner"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("/message/owner/352")

        then:
        r.then().
            body("Status", is("SUCCESS")).
            body("data.message", containsString("The Mainframe :P N0 I M3an 1t"))
    }
}
