package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.AbstractDAO;
import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Article_;
import cz.cvut.wikee.model.persistence.entity.Ticket;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 19.1.13
 * Time: 20:12
 */

@Named
@Stateless
public class ArticleService extends AbstractDAO<Article> {

    @Inject @Storage
    protected EntityManager em;

    public ArticleService() {
        super(Article.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    //---------------------------------------------------------


    public Article getArticle(String name){
       return getWhereEquals(Article_.name, name);
    }

    public void addTicket(Article article, Ticket ticket){
        article = em.merge(article);
        ticket = em.merge(ticket);

        article.addPartOf(ticket);
    }


}
