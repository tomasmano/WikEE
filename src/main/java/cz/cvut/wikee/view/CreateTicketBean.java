package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.sevice.TicketService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Named("createTicketBean")
@RequestScoped
public class CreateTicketBean {
    
    private Logger logger = Logger.getLogger(this.getClass());

    @Inject
    private TicketService ticketService;
    
    @Inject
    private AuthenticatedUserBean userBean;
    
    @Size(max=32)
    private String ticketName;
    
    @Size(max=255)
    private String info;
    
    public String saveTicket(){
        logger.debug("Saving ticket ..");
        Ticket saved = ticketService.saveOrUpdate(new Ticket(userBean.getUser(), ticketName, info));
        logger.debug("Ticket saved: "+saved);
        return "ticketCreated";
    }
    
    //////////  Getters / Setters  //////////

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
