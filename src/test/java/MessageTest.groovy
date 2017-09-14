import Moduls.Hashtag
import Moduls.Message
import Moduls.Users
import spock.lang.Specification

class MessageTest extends Specification {
    def "Check if Message class exists"(){
        when:
        Message message = new Message();

        then:
        notThrown(NullPointerException);
    }

    def "If #message id value is set then it should mach the set value"(){
        setup:
        Message message = new Message()

        when:
        message.setId(1);

        then:
        message.getId() == 1
    }

    def "#message message value should match set value"(){
        setup:
        Message message = new Message()

        when:
        message.setMessage("Hi i'm a test message!")

        then:
        message.getMessage() == "Hi i'm a test message!"
    }

    def "#message like value should match set value"(){
        setup:
        Message message = new Message()

        when:
        message.setLikes(204)

        then:
        message.getLikes() == 204
    }

    def "#message owner value should mach set value"(){
        setup:
        Message message = new Message()
        Users owner = new Users();

        when:
        message.setOwner(owner)

        then:
        message.getOwner() == owner
    }

    def "#message hashtag value should mach set value"(){
        setup:
        Message message = new Message();
        List<Hashtag> hashtagList = new ArrayList<>()
        Hashtag hashtag = new Hashtag()
        hashtagList.add(hashtag)

        when:
        message.setHashtags(hashtagList)

        then:
        message.getHashtags() == hashtagList
    }
}