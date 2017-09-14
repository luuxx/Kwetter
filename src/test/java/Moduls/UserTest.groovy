package Moduls

import Moduls.Users
import spock.lang.Specification
import spock.lang.Unroll

class UserTest extends Specification{
    def "Check if User class exists"(){
        when:
        Users user = new Users();

        then:
        notThrown(NullPointerException);
    }

    def "users id needs to be equal to wat is set"(){
        setup:
        Users users = new Users()

        when:
        users.setId(1)

        then:
        users.getId() == 1
    }

    def "users Bio needs to be equal to wat is set"(){
        setup:
        Users users = new Users()

        when:
        users.setBio("This is a bio")

        then:
        users.getBio() == "This is a bio"
    }

    def "users Location needs to be equal to wat is set"(){
        setup:
        Users users = new Users()

        when:
        users.setLocation("In this test")

        then:
        users.getLocation() == "In this test"
    }

    def "users Name needs to be equal to wat is set"(){
        setup:
        Users users = new Users()

        when:
        users.setName("TestUsers")

        then:
        users.getName() == "TestUsers"
    }

    def "users Web address needs to be equal to wat is set"(){
        setup:
        Users users = new Users()

        when:
        users.setWebAddress("www.foobar.com")

        then:
        users.getWebAddress() == "www.foobar.com"
    }

    def "users password address needs to be equal to wat is set"(){
        setup:
        Users users = new Users()

        when:
        users.setPassword("12sdASF12vaF#")

        then:
        users.getPassword() == "12sdASF12vaF#"
    }

    @Unroll
    def "new user should have empty fields"(){
        when:
        Users users = new Users()

        then:
        assertUserFieldsAreEmpty(users)

    }

    @Unroll
    void assertUserFieldsAreEmpty(Users users){
        assert users.getId() == 0
        assert users.getPassword() == null
        assert users.getWebAddress() == null
        assert users.getUsername() == null
        assert users.getName() == null
        assert users.getLocation() == null
        assert users.getBio() == null
        assert users.getFollowers() == null
        assert users.getFollowing() == null
    }

    def "user list of followers should be the same set"(){
        setup:
            Users users1 = new Users()
            Users users2 = new Users()
            List<Users> listOfUsers = new ArrayList<>()
            listOfUsers.add(users2)
        when:
            users1.setFollowers(listOfUsers)
        then:
            users1.getFollowers() == listOfUsers
            users1.getFollowers().size() == listOfUsers.size()
    }

}
