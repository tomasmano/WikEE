package cz.cvut.wikee.model.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 19.1.13
 * Time: 20:49
 * <p/>
 * User of application
 * <p/>
 * contains - not used
 * partOf - not used
 * roles - admin/redactor/regular_user/..
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends WikeeEntity {
    public static final String TYPE = "USER";

    @Override
    public String type() {
        return TYPE;
    }

    private String firstname;
    private String lastname;

    private String username;
    private String password;

    @OneToMany(mappedBy = "creator")
    private List<WikeeEntity> createdItems;

    @ManyToOne
    private Role role;

    public User() {
    }

    public User(User creator, String firstname, String lastname, String username, String password, Role role) {
        super(creator);
        this.firstname = firstname;
        this.username = username;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
    }

    // GETTERS and SETTERS -------------------------------------------------------------------
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        propertyChanged(this.firstname, firstname);
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        propertyChanged(this.lastname, lastname);
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        propertyChanged(this.username, username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        propertyChanged(this.password, password);
        this.password = password;
    }

    public List<WikeeEntity> getCreatedItems() {
        return createdItems;
    }

    public void setCreatedItems(List<WikeeEntity> createdItems) {
        this.createdItems = createdItems;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Not used
     */
    @Override
    @Deprecated
    public List<WikeeEntity> getContains() {
        return new ArrayList<WikeeEntity>();
    }

    /**
     * Not used
     */
    @Override
    @Deprecated
    public List<WikeeEntity> getPartOf() {
        return new ArrayList<WikeeEntity>();
    }
}
