package cz.cvut.wikee.view;

import cz.cvut.wikee.model.persistence.entity.User;
import cz.cvut.wikee.model.sevice.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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

@Named("usersTest")
@SessionScoped
public class UsersTestBean implements Serializable {

    private String username;

    private User user;

    @PostConstruct
    public void init(){
    }

    @Inject
    private UserService userService;

    public List<User> getAllUsers(){
        return userService.getAll();
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String perform(){
        user = userService.getUser(username);
        return null;
    }
}
