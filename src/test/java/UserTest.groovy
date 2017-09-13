import Moduls.Users
import spock.lang.Specification

class UserTest extends Specification{
    def "Check if User class exists"(){
        when:
        Users user = new Users();

        then:
        notThrown(NullPointerException);
    }

    def "Check to see if the class setters work"(){
    }
}
