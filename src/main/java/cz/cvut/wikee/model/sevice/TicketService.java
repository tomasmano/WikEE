package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.AbstractDAO;
import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.persistence.entity.Ticket_;

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
public class TicketService extends AbstractDAO<Ticket> {

    @Inject @Storage
    protected EntityManager em;

    public TicketService() {
        super(Ticket.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    //---------------------------------------------------------

    public Ticket getTicket(String name){
        return getWhereEquals(Ticket_.name, name);
    }
}
