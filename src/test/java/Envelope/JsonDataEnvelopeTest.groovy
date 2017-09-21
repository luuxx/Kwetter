package Envelope

import Moduls.Hashtag
import Moduls.Link
import Moduls.Message
import Moduls.Users
import Provider.JsonEnvlope.JsonDataEnvelope
import spock.lang.Specification

class JsonDataEnvelopeTest extends Specification{

    JsonDataEnvelope envelope;

    def setup(){
        envelope = new JsonDataEnvelope()
    }

    def "check if class exits"(){
        when:
        JsonDataEnvelope envelope = new JsonDataEnvelope()

        then:
        notThrown(NullPointerException)
    }

    def "Check to see if #link is a set"(){
        setup:
        Link link = new Link()

        when:
        envelope.setLink(link)

        then:
        envelope.getLink() == link
    }

    def "Check to see if set #data equals the given object"(){
        setup:
        Object o = new Object()

        when:
        envelope.setData(o)

        then:
        envelope.getData() == o

    }

    def "Check to see if data can contain different types of objects"(){
        setup:
        Users users = new Users()
        Hashtag hashtag = new Hashtag()
        Message message = new Message()

        when:
        envelope.setData(users)
        envelope.setData(hashtag)
        envelope.setData(message)
        envelope.setData(1)
        envelope.setData("Hello")

        then:
        notThrown(IllegalArgumentException)
    }

    def "Check to see if #links value equals the set data"(){
        setup:
        List<Link> linkList = new ArrayList<>()
        Link link = new Link()
        linkList.add(link)

        when:
        envelope.setLink(linkList)

        then:
        envelope.getLinks() == linkList
    }
}
