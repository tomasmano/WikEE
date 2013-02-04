package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.AbstractDAO;
import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.User;
import cz.cvut.wikee.model.persistence.entity.User_;
import cz.cvut.wikee.model.persistence.entity.WikeeEntity;

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
public class UserService extends AbstractDAO<User> {

    @Inject @Storage
    protected EntityManager em;

    public UserService() {
        super(User.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    //--------------------------
    public User getUser(String username){
        return getWhereEquals(User_.username, username);
    }

    @Override
    public void remove(User user) {
        for(WikeeEntity e : user.getCreatedItems()){
            e.setCreator(null);
        }

        user.getRole().getMembers().remove(user);
        user.setRole(null);

        super.remove(user);
    }
}
