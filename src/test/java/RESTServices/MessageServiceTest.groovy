package RESTServices

import io.restassured.RestAssured
import io.restassured.response.Response
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.hamcrest.Matchers.*

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
            body("status", is("SUCCESS")).
            body("data[1].context.message", containsString("The Mainframe :P N0 I M3an 1t")).
            body("data[1].link[0].rel", is("self")).
            body("data[1].link[1].rel", is("remove")).
            body("data[1].link[2].rel", is("author"))
    }

    def "Get message by id"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("/message/id/401")

        then:
        r.then().
                body("status", is("SUCCESS")).
                body("data.message", containsString("The Mainframe :P N0 I M3an 1t")).
                body("link[0].rel", is("self")).
                body("link[1].rel", is("remove")).
                body("link[2].rel", is("author"))
    }

    def "Get all messages no limit"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("/message/all")

        then:
        r.then().body("[1].message", not(isEmptyOrNullString())).
                body("[1].id", not(isEmptyOrNullString())).
                body("[1].owner", not(isEmptyOrNullString()))
    }

    def "Get all messages limit 2"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("/message/all/2")

        then:
        r.then().body("[0].message", not(isEmptyOrNullString())).
                body("[0].id", not(isEmptyOrNullString())).
                body("[0].owner", not(isEmptyOrNullString()))
        r.then().body("[1].message", not(isEmptyOrNullString())).
                body("[1].id", not(isEmptyOrNullString())).
                body("[1].owner", not(isEmptyOrNullString()))
        r.then().body("[2].message", is(isEmptyOrNullString())).
                body("[2].id", is(isEmptyOrNullString())).
                body("[2].owner", is(isEmptyOrNullString()))
    }
}
