package cz.cvut.wikee.model.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 19.1.13
 * Time: 20:37
 */
public class EntityManagerProvider {

    @Produces @Storage @PersistenceUnit(unitName = "primary")
    private EntityManagerFactory entityManagerFactory;

    @Produces @Storage @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
