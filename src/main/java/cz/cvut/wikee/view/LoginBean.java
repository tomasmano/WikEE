package cz.cvut.wikee.view;

import cz.cvut.wikee.model.sevice.UserService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@Named("loginBean")
@RequestScoped
public class LoginBean {

    @Inject
    private AuthenticatedUserBean userBean;
    
    @Inject
    private UserService userService;
    
    private String username;
    private String password;

    public String doLogin() {
        userBean.setUser(userService.getUser(username));
        return "success";
    }

    //////////  Getters / Setters  //////////
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticatedUserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(AuthenticatedUserBean userBean) {
        this.userBean = userBean;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
}
