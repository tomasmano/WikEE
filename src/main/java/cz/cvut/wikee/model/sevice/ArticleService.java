package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.AbstractDAO;
import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Article_;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Article> getAllUsers(){
        return getAll();
    }

    public Article getRole(String name){
       return getWhereEquals(Article_.name, name);
    }


}
