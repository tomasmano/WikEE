package cz.cvut.wikee.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 20.1.13
 * Time: 21:01
 *
 * Role of user
 * In future can be extended with "rights" = e. g. admin has rights create_article, delete_article, ...
 *
 * partOf - not used
 * contains - not used
 */

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class Role extends WikeeEntity {
    public static final String TYPE = "ROLE";

    @Override
    public String type() {
        return TYPE;
    }

    @NotNull
    @Size(min = 3, message = "Role name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> members;

    protected Role(){
        super(null);
        this.members = new ArrayList<User>();
    }

    public Role(String name){
        this();
        this.name = name;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Members of this role
     * @return
     */
    public List<User> getMembers() {
        return members;
    }

    protected void setMembers(List<User> members) {
        this.members = members;
    }

    /**
     * Not used
     */
    @Deprecated
    @Override
    public List<WikeeEntity> getPartOf() {
        throw new IllegalStateException("Method not supported for this entity");
    }

    /**
     * Not used
     */
    @Deprecated
    @Override
    public List<WikeeEntity> getContains() {
        throw new IllegalStateException("Method not supported for this entity");
    }
}
