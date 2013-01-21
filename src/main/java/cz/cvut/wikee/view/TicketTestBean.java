package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.sevice.TicketService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 19.1.13
 * Time: 22:30
 *
 * Just testing bean, you can delete...
 */

@Named("ticketTest")
@RequestScoped
public class TicketTestBean implements Serializable {
    @Inject
    private TicketService ticketService;


    public List<Article> getTicketArticles(){
        return ticketService.getContainsArticles(ticketService.getTicket("Auta"));
    }

    public List<Article> getParentTicket(Ticket t){
        return ticketService.getContainsArticles(ticketService.getTicket("Auta"));
    }


}
