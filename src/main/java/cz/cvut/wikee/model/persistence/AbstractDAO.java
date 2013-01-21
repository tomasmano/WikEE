package cz.cvut.wikee.model.persistence;

import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.persistence.entity.WikeeEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 20.1.13
 * Time: 15:48
 *
 * Parent class for all services operating over some database table - DAO like
 */

public abstract class AbstractDAO<T extends WikeeEntity> implements Serializable {

    private Class<T> clazz;

    protected abstract EntityManager getEm();

    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @return all entities of defined class
     */
    public List<T> getAll() {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);

        TypedQuery<T> result = getEm().createQuery(criteria);
        return result.getResultList();
    }

    /**
     * Finds entity, which attribute @param attribute equals @param value
     * Expects ony one entity as result.
     *
     * @param attribute
     * @param value
     * @return entity or null
     */
    protected T getWhereEquals(SingularAttribute<T, ? extends Serializable> attribute, Serializable value) {
        CriteriaBuilder builder = getEm().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);

        criteria.select(root);
        criteria.where(builder.equal(root.get(attribute), value));

        TypedQuery<T> result = getEm().createQuery(criteria);
        try {
            return result.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Finds entity with id @param id
     *
     * @param id
     * @return entity or null
     */
    protected T get(Integer id){
        return getEm().find(clazz, id);
    }

    public void persist(T entity){
        getEm().persist(entity);
    }

    public T merge(T entity){
        return getEm().merge(entity);
    }

    /**
     * Updates entity in DB if exists, otherwise insert new record
     * @param entity
     * @return
     */
    public T saveOrUpdate(T entity){
        if(getEm().contains(entity)){
            return merge(entity);
        }
        persist(entity);
        return entity;
    }

    /**
     * @param entity - current entity
     * @param type - type of wikEE entities you want to.
     * @param <R>
     * @return List of entities
     *
     * Example: getContains(ticket, Article.TYPE) - returns list of articles with ticket (category) ticket
     */
    public <R extends WikeeEntity> List<R> getContains(T entity, String type) {
        entity = getEm().merge(entity);
        return filterList(entity.getContains(), type);
    }

    public List<Article> getContainsArticles(T entity){
        return getContains(entity, Article.TYPE);
    }

    public List<Ticket> getContainsTickets(T entity){
        return getContains(entity, Ticket.TYPE);
    }

    /**
     * @param entity - current entity
     * @param type - type of wikEE entities you want to.
     * @param <R>
     * @return List of entities
     *
     * Example: getPartOf(article, Ticket.TYPE) - returns list of tickets article belongs to.
     */
    public <R extends WikeeEntity> List<R> getPartOf(T entity, String type) {
        entity = getEm().merge(entity);
        return filterList(entity.getPartOf(), type);
    }

    public List<Article> getPartOfArticles(T entity){
        return getPartOf(entity, Article.TYPE);
    }

    public List<Ticket> getPartOfTickets(T entity){
        return getPartOf(entity, Ticket.TYPE);
    }

    private <R extends WikeeEntity> List<R> filterList(List<? extends WikeeEntity> list, String type) {
        List<R> out = new ArrayList<R>();
        for(WikeeEntity e : list){
            if(e.type().equals(type)){
                out.add((R) e);
            }
        }
        return out;
    }
}
