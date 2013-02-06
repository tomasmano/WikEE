package cz.cvut.wikee.view.converter;

import cz.cvut.wikee.model.sevice.TicketService;
import org.apache.log4j.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * Converter for Tickets for JSF.
 *
 * Mel by se pouzit @Named, jinak se nic neinjectne. Viz http://stackoverflow.com/questions/7531449/cdi-injection-into-a-facesconverter
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@FacesConverter(value="ticketConverter")
public class TicketConverter implements Converter{

    public Logger logger = Logger.getLogger(this.getClass());

    @Inject
    private TicketService ticketService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        logger.debug("Converting the given string: "+string);
        return ticketService.get(new Integer(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        logger.debug("Converting the given ojbect: "+o);
        return ((Integer) o).toString();
    }

}
