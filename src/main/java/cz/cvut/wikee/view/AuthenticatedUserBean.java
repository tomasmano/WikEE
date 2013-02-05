package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.User;
import cz.cvut.wikee.model.sevice.UserService;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Named("userBean")
@SessionScoped
public class AuthenticatedUserBean  implements Serializable {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    private User user;
    
    private String role;
    
    private Article selectedArticle;
    
    @Inject
    private UserService userService;
    
    /**
     * Invalidates user's session.
     * 
     * @return null
     * @throws IOException 

     */
    public String doLogout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        context.redirect(context.getRequestContextPath());
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }
    
    //////////  Getters / Setters  //////////

    public User getUser() {
        if (user == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            String remoteUserName = externalContext.getRemoteUser();
            user = userService.getUser(remoteUserName);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return getUser().getRole().getName();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        logger.debug("Setting article: "+selectedArticle);
        this.selectedArticle = selectedArticle;
    }
    
}
