package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.Setting;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 21.1.13
 * Time: 16:56
 */

@Named
@Stateless
public class SettingsService {

    @Inject @Storage
    private EntityManager em;

    /**
     * @param name of setting
     * @return value from db or empty string "" (will be saved into DB to)
     */
    public String get(String name){
        return get(name, "");
    }

    /**
     *
     * @param name of param
     * @param defaultValue value used if param is not set
     * @return value from db or @param defaultValue (will be saved into DB to)
     */
    public String get(String name, String defaultValue){
        Setting e = em.find(Setting.class, name);
        if(e == null || e.getValue() == null){
            set(name, defaultValue);
            return defaultValue;
        }
        return e.getValue();
    }

    /**
     * Insert or update setting in DB
     *
     * @param name - name of setting
     * @param value
     */
    public void set(String name, String value){
        if(name == null || value == null){
            return;
        }

        Setting e = em.find(Setting.class, name);
        if(e == null){
            e = new Setting(name, value);
            em.persist(e);
        } else {
            e.setValue(value);
            em.merge(e);
        }
    }
}
