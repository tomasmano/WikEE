package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.AbstractDAO;
import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.User;
import cz.cvut.wikee.model.persistence.entity.User_;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 19.1.13
 * Time: 20:12
 */

@Named
@Stateless
public class UserService extends AbstractDAO<User> {

    @Inject @Storage
    protected EntityManager em;

    public UserService() {
        super(User.class);
    }

    public List<User> getAllUsers(){
        return getAll();
    }

    public User getUser(String username){
        return getWhereEquals(User_.username, username);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
}
