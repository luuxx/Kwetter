package DAO

import Moduls.DAO.MessageDAO
import Moduls.Message
import Moduls.Users
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.TypedQuery

class MessageDAOTest extends Specification{

    MessageDAO dao
    EntityManager entityManager
    Message sampleMessage1
    List<Message> sampleMessageList
    Users sampleOwner

    def setup(){
        sampleMessageList = new ArrayList<>()

        sampleMessage1 = new Message()
        sampleOwner = new Users()
        sampleOwner.id = 1

        sampleMessage1.setId(1)
        sampleMessage1.setOwner(sampleOwner)
        sampleMessage1.setLikes(10)
        sampleMessage1.setMessage("This is a stub message! you are not connected to the real database.")

        entityManager = Stub(EntityManager.class)
        entityManager.find(Message.class,1) >> sampleMessage1

        sampleMessageList.add(sampleMessage1)
        dao = new MessageDAO(entityManager: entityManager)
    }

    def "Find by id returns message if id is 1"(){
        when: "Message id is 1"
        Message message = dao.findById(1)

        then: "Return message with id 1"
        message.getId() == 1
    }

    def "Find by id should not find a message with id 2"(){
        given:
        entityManager.find(Message.class, 2) >> null

        when: "Message id is 2"
        Message message = dao.findById(2)

        then:
        message == null
    }

    def "Find by owner returns message of owner"(){
        given:
        TypedQuery query = Stub(TypedQuery)
        query.getResultList() >> sampleMessageList
        query.setParameter(_,_) >> query
        entityManager.createNamedQuery("Message.listByOwner", Message.class) >> query

        when:
        List<Message> listOfMessages = dao.MessagesByOwner(sampleOwner.id)

        then:
        listOfMessages.size() == 1
        listOfMessages.get(0).id == 1
    }

    def "return a list of all the messages in the database"(){
        given:
        TypedQuery query = Stub(TypedQuery)
        query.getResultList() >> sampleMessageList
        entityManager.createNamedQuery("Message.getAllMessages", Message.class) >> query

        when:
        List<Message> listOfMessages = dao.getAllMessages()

        then:
        listOfMessages.size() == 1
        listOfMessages.get(0).id == 1
    }

    def "return a list of messages equal or less the number given"(){
        given:
        TypedQuery query = Stub(TypedQuery)
        query.getResultList() >> sampleMessageList
        query.setMaxResults(_) >> query
        entityManager.createNamedQuery("Message.getAllMessages", Message.class) >> query

        when:
        List<Message> listOfMessages = dao.getAllMessages(3)

        then:
        listOfMessages.size() <= 3
        listOfMessages.get(0).id == 1
    }

}
