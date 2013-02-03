package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.User;
import cz.cvut.wikee.model.sevice.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by
 * User: Zdenek Pecka
 * Email: peckazde@gmail.com
 * Date: 3.2.13
 *
 * Just testing bean, you can delete...
 */

@Named("authTest")
@SessionScoped
public class UsersAuth implements Serializable {

    private User user;

    @PostConstruct
    public void init(){
    }

    @Inject
    private UserService userService;

    public String getUserName(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        String remoteUser = externalContext.getRemoteUser();
        return remoteUser;
    }

    public String getUserRole(){
        String username = getUserName();
        User user = userService.getUser(username);
        return user.getRole().getName();
    }

}
