package cz.cvut.wikee.view;


import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.sevice.ArticleService;
import cz.cvut.wikee.model.sevice.TicketService;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.util.List;

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

    private Integer ticketId;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public void saveArticle(){
        logger.debug("Saving article ..");
        Article saved = articleService.saveOrUpdate(new Article(userBean.getUser(), name, content));
        // TODO - nikde se nenastavuje ticket. Ani se nikde nevola nevola getTickets()
        // Prozatim nastavim vzdy ticket Počítače

        ticket = ticketService.get(ticketId);
        //ticket = ticketService.getTicket("Počítače");

        // Entita neni v persistence kontextu - musi se mergenout.
        //saved.addPartOf(ticket);
        // articleService.merge(saved);

        // Udelal jsem v service zvlastni metodu pro pridani ticketu
        articleService.addTicket(saved, ticket);

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
        List<Ticket> all = ticketService.getAll();
        return all;
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
