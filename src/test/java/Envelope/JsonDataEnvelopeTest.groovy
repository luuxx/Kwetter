package Envelope

import Moduls.Hashtag
import Moduls.Link
import Moduls.Message
import Moduls.Users
import Provider.JsonEnvlope.JsonDataEnvelope
import spock.lang.Specification

class JsonDataEnvelopeTest extends Specification{
    def "check if class exits"(){
        when:
        JsonDataEnvelope envelope = new JsonDataEnvelope()

        then:
        notThrown(NullPointerException)
    }

    def "Check to see if #link is a set"(){
        setup:
        Link link = new Link()
        JsonDataEnvelope envelope = new JsonDataEnvelope()

        when:
        envelope.setLink(link)

        then:
        envelope.getLink() == link
    }

    def "Check to see if set #data equals the given object"(){
        setup:
        Object o = new Object()
        JsonDataEnvelope envelope = new JsonDataEnvelope()

        when:
        envelope.setData(o)

        then:
        envelope.getData() == o

    }

    def "Check to see if data can contain different types of objects"(){
        setup:
        JsonDataEnvelope envelope = new JsonDataEnvelope()
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
}
