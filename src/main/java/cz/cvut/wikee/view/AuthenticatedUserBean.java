package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.User;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Named("userBean")
@SessionScoped
public class AuthenticatedUserBean  implements Serializable {

    private User user;
    
    /**
     * Invalidates user's session.
     * 
     * @return null
     * @throws IOException 

     */
    public String doLogout() throws IOException {
        user = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        context.redirect(context.getRequestContextPath());
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }
    
    //////////  Getters / Setters  //////////

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
