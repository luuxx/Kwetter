package DAO

import Moduls.DAO.UsersDAO
import Moduls.Users
import spock.lang.Specification

import javax.persistence.EntityManager

class UserDAOTest extends Specification{
    UsersDAO dao
    Users sampleUser1
    List<Users> usersList
    EntityManager entityManager


    def setup(){
        entityManager = Stub(EntityManager)

        dao = new UsersDAO(entitymanager: entityManager)
        sampleUser1 = new Users()
        usersList = new ArrayList<>()

    }

    def "Class exits"(){
        expect:
        dao != null
    }
}
