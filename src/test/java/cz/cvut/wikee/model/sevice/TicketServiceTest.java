package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.arquillian.ArchiveFactory;
import cz.cvut.wikee.arquillian.TestUtils;
import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Ticket;
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
import java.util.List;

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

    /**
     * Test
     */
    @Test
    public void testSubTickets() {
        Ticket n = ticketService.getTicket("Notebooky");
        Ticket p = ticketService.getTicket("Počítače");
        Assert.assertEquals(n.getId(), ticketService.loadLazyCollections(p).getContains().get(0).getId());
        Assert.assertEquals(p.getId(), ticketService.loadLazyCollections(n).getPartOf().get(0).getId());

        ticketService.removePartOf(n, p);

        Assert.assertTrue(ticketService.loadLazyCollections(n).getPartOf().isEmpty());
        Assert.assertTrue(ticketService.loadLazyCollections(p).getContains().isEmpty());
    }

    @Test
    public void testContainingArticles() {
        Ticket t = ticketService.getTicket("Auta");
        List<Article> articles = ticketService.getContainsArticles(t);
        Assert.assertEquals(2, articles.size());

        ticketService.removeContains(t, articles.get(0));
        articles = ticketService.getContainsArticles(t);
        Assert.assertEquals(1, articles.size());
    }
}
