package cz.cvut.wikee.model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 20.1.13
 * Time: 16:43
 *
 * Parent class for all entities in app.
 * Dates are auto populated, use only getters
 */
@Entity
public abstract class WikeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Integer id;

    public Date created;
    public Date updated;

    @ManyToOne
    public User creator;

    @ManyToMany(
            mappedBy = "contains",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<WikeeEntity> partOf;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable (
            name="wikeeentity_wikeeentity",
            joinColumns = {@JoinColumn(name="contains_id")},
            inverseJoinColumns = {@JoinColumn(name="partOf_id")}
    )
    private List<WikeeEntity> contains;

    public WikeeEntity(){
        Date now = new Date();
        setCreated(now);
        setUpdated(now);
        this.contains = new ArrayList<WikeeEntity>();
        this.partOf = new ArrayList<WikeeEntity>();
    }

    public WikeeEntity(User creator){
        this();
        this.creator = creator;
    }

    public abstract String type();

    protected void propertyChanged(Serializable oldValue, Serializable newValue) {
        if(oldValue == null && newValue == null){
            return;
        }
        if(oldValue == null){
            Serializable tmp = oldValue;
            oldValue = newValue;
            newValue = oldValue;
        }
        if(!oldValue.equals(newValue)){
            return;
        }

        setUpdated(new Date());
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    protected void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    protected void setUpdated(Date updated) {
        this.updated = updated;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        propertyChanged(this.creator, creator);
        this.creator = creator;
    }

    public List<WikeeEntity> getPartOf() {
        return partOf;
        //return Collections.unmodifiableList(partOf);
    }

    protected void setPartOf(List<WikeeEntity> partOf) {
        this.partOf = partOf;
    }

    public List<WikeeEntity> getContains() {
        return contains;
        //return Collections.unmodifiableList(contains);
    }

    protected void setContains(List<WikeeEntity> contains) {
        this.contains = contains;
    }

    // Entities are equal if and only if id = id

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WikeeEntity that = (WikeeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
