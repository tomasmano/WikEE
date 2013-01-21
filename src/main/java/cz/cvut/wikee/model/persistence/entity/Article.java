package cz.cvut.wikee.model.persistence.entity;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 20.1.13
 * Time: 17:11
 *
 * Text article - base item in wikEE
 *
 * contains - photos, other articles, attachments...
 * partOf - ticket, other article, ...
 *
 */
@Entity
public class Article extends WikeeEntity {
    public static final String TYPE = "ARTICLE";

    @Override
    public String type() {
        return TYPE;
    }

    public Article(){
    }

    public Article(User creator, String name, String content){
        super(creator);
        this.name = name;
        this.content = content;

    }

    private String name;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        propertyChanged(this.name, name);
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        propertyChanged(this.content, content);
        this.content = content;
    }

    /** ticket, other article, ... */
    @Override
    public List<WikeeEntity> getPartOf() {
        return super.getPartOf();
    }

    /** ticket, other article, ... */
    @Override
    public void setPartOf(List<WikeeEntity> partOf) {
        super.setPartOf(partOf);
    }

    /** photos, other articles, attachments */
    @Override
    public List<WikeeEntity> getContains() {
        return super.getContains();
    }

    /** photos, other articles, attachments */
    @Override
    public void setContains(List<WikeeEntity> contains) {
        super.setContains(contains);
    }
}
