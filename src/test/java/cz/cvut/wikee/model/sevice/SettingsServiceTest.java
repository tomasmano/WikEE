package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.arquillian.ArchiveFactory;
import cz.cvut.wikee.arquillian.TestUtils;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 6.2.13
 * Time: 23:06
 */
@RunWith(Arquillian.class)
public class SettingsServiceTest {

    @Inject
    private TestUtils testUtils;

    @Inject
    private SettingsService settingsService;

    @Deployment
    public static Archive<?> getDeployment() {
        return ArchiveFactory.createArchive();
    }

    @After
    public void clean(){
        testUtils.clearDatabase();
    }

    @Test
    public void test(){
        Assert.assertEquals("", settingsService.get("aa"));
        settingsService.set("aa", "valAA");
        Assert.assertEquals("valAA", settingsService.get("aa"));
        settingsService.set("aa", "valBB");
        Assert.assertEquals("valBB", settingsService.get("aa"));

        Assert.assertEquals("BB", settingsService.get("bb", "BB"));
    }
}
