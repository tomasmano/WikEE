package cz.cvut.wikee.model.persistence.entity;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 20.1.13
 * Time: 20:45
 *
 * Ticket - used to determine "category" of items
 *
 * contains - subtickets, articles, ...
 * partOf - parent tickets, if any
 */
@Entity
public class Ticket extends WikeeEntity {
    public static final String TYPE = "TICKET";

    @Override
    public String type() {
        return TYPE;
    }

    private String name;
    private String info;

    public Ticket(){
    }

    public Ticket(User creator, String name, String info){
        this.creator = creator;
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /** parent tickets, if any */
    @Override
    public List<WikeeEntity> getPartOf() {
        return super.getPartOf();
    }

    /** subtickets, articles, ... */
    @Override
    public List<WikeeEntity> getContains() {
        return super.getContains();
    }
}
