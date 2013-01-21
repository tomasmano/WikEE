package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.AbstractDAO;
import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.Role;
import cz.cvut.wikee.model.persistence.entity.Role_;

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
public class RoleService extends AbstractDAO<Role> {

    @Inject @Storage
    protected EntityManager em;

    public RoleService() {
        super(Role.class);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    //---------------------------------------------------------

    public List<Role> getAllUsers(){
        return getAll();
    }

    public Role getRole(String name){
        return getWhereEquals(Role_.name, name);
    }


}
