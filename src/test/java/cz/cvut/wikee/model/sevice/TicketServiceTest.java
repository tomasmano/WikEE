package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.arquillian.ArchiveFactory;
import cz.cvut.wikee.arquillian.TestUtils;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 4.2.13
 * Time: 18:27
 */

@RunWith(Arquillian.class)
public class TicketServiceTest {
    private static final Logger LOG = Logger.getLogger(TicketServiceTest.class);

    @Inject
    private TicketService ticketService;

    @Inject
    private TestUtils testUtils;

    @Deployment
    public static Archive<?> getDeployment() {
       return ArchiveFactory.createArchive();
    }

    @Before
    public void init(){
        testUtils.insertSampleData();
    }

    @After
    public void clean(){
        testUtils.clearDatabase();
    }


    /**
     * Demo test
     */
    @Test
    public void test() {
        Assert.assertEquals(3, ticketService.getAll().size());
    }
}
