package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.sevice.ArticleService;
import cz.cvut.wikee.model.sevice.TicketService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Named("createArticleBean")
@RequestScoped
public class CreateArticleBean {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Inject
    private ArticleService articleService;
    
    @Inject
    private TicketService ticketService;
    
    @Inject
    private AuthenticatedUserBean userBean;
    
    @Size(max=64)
    private String name;
    
    @Size(max=255)
    private String content;
    
    private Ticket ticket;
    
    private List<Ticket> tickets;
    
    public void saveArticle(){
        logger.debug("Saving article ..");
        Article saved = articleService.saveOrUpdate(new Article(userBean.getUser(), name, content));
//        saved.addPartOf(ticket);
        logger.debug("Article saved: "+saved);
    }
    
    //////////  Getters / Setters  //////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Ticket> getTickets() {
        return ticketService.getAll();
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
