package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.sevice.ArticleService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Named("articleBean")
@ViewScoped
public class ArticlesBean {
    
    @Inject
    private ArticleService articleService;
    
    private Article selectedArticle;
    
    public void deleteSelected(Article article){
        articleService.remove(article);
    }
    
    public List<Article> getAll(){
        return articleService.getAll();
    }
    
    public List<Ticket> getArticleTickets(Article article) {
        if (article != null) {
            return articleService.getContainsTickets(article);
        }
        return new ArrayList<Ticket>();
    }
    
    //////////  Getters / Setters  //////////

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }
    
}
