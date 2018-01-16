package RESTServices

import io.restassured.RestAssured
import io.restassured.response.Response
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.is;

class UserServicesRESTTest extends Specification{
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
                get("/user/id/352")


        then:
        r.then().statusCode(200)
    }

    def "Find user by username"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("user/username/Luxiam")
        then:
        r.then().statusCode(200)
                .body("name", is("Luuk Minten"))
                .body("location", containsString("Eindhoven"))
                .body("id", is(1))
                .body("username", is("Luxiam"));
    }

    def "Find user by non-exciting username"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("user/username/Looper")
        then:
        r.then().statusCode(204)
    }

    def "Find user by id"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("user/id/1")
        then:
        r.then().statusCode(200)
                .body("name", is("Luuk Minten"))
                .body("location", containsString("Eindhoven"))
                .body("id", is(1))
                .body("username", is("Luxiam"));
    }

    def "Find user by name"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("user/name/Luuk Minten")
        then:
        r.then().statusCode(200)
                .body("name", is("Luuk Minten"))
                .body("location", containsString("Eindhoven"))
                .body("id", is(1))
                .body("username", is("Luxiam"));
    }

    def "Find all following"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("user/following/352")
        then:
        r.then().statusCode(200)
                .body("name", hasItem("Test McTester"))
                .body("location", hasItem("The Mainframe"))
                .body("id", hasItem(351))
                .body("username", hasItem("Test123"));
    }

    def "count all following"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("user/countFollowing/352")
        then:
        r.then().statusCode(200).body(is("1"))
    }

    def "count all followers"(){
        when:
        Response r =
                given().
                        auth().
                        preemptive().
                        basic("Luxiam", "admin").

                        when().
                        get("user/countFollowers/352")
        then:
        r.then().statusCode(200).body(is("2"))
    }
}
