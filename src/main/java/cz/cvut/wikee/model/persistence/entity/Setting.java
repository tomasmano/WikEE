package cz.cvut.wikee.model.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 21.1.13
 * Time: 16:53
 *
 * Entity to save app setting - whatever you want
 */

@Entity
public class Setting {
    @Id
    private String name;
    private String value;

    public Setting() {
    }

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
