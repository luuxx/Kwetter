package Moduls.DAO;

import Moduls.DAO.Interfaces.IHastagDAO;
import Moduls.Hashtag;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Luxiam on 8/26/2017.
 */

@Stateless
public class HashtagDAO implements IHastagDAO {

    @PersistenceContext
    EntityManager entitymanager;

    public void persist(Hashtag entity) {

        try {

            Query query = entitymanager.createNamedQuery("Hashtag.findTag");
            query.setParameter("tag", entity.getTag());

            entity = (Hashtag) query.getSingleResult();
        } catch (NoResultException e) {
            entitymanager.persist(entity);
        }

    }

    public void remove(Hashtag entity) {
        entitymanager.remove(entity);
    }

    public Hashtag findById(int id) {
        return entitymanager.find(Hashtag.class,id);
    }

    public Hashtag findByTag(String tag) {
        return entitymanager.createNamedQuery("Hashtag.findTag", Hashtag.class).setParameter("tag",tag).getSingleResult();
    }
}
