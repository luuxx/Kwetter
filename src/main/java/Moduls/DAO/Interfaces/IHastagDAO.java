package Moduls.DAO.Interfaces;

import Moduls.Hashtag;

import javax.ejb.Local;

/**
 * Created by Luxiam on 8/26/2017.
 */

@Local
public interface IHastagDAO {
    void persist(Hashtag entity);

    void remove(Hashtag entity);

    Hashtag findById(int id);

    Hashtag findByTag(String tag);
}
