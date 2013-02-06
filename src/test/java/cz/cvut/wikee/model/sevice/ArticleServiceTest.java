package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.arquillian.ArchiveFactory;
import cz.cvut.wikee.arquillian.TestUtils;
import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.persistence.entity.User;
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
 * Date: 6.2.13
 * Time: 19:59
 */
@RunWith(Arquillian.class)
public class ArticleServiceTest {
    private static final Logger LOG = Logger.getLogger(ArticleServiceTest.class);

    @Inject
    private TestUtils testUtils;

    @Inject
    private ArticleService articleService;

    @Inject
    private TicketService ticketService;

    @Inject
    private UserService userService;

    private User admin;
    private User regular;
    private User redactor;

    @Deployment
    public static Archive<?> getDeployment() {
        return ArchiveFactory.createArchive();
    }

    @Before
    public void init(){
        testUtils.insertSampleDataBasics();
        admin = userService.getUser("admin");
        regular = userService.getUser("regular");
        redactor = userService.getUser("redactor");
    }

    @After
    public void clean(){
        testUtils.clearDatabase();
    }

    @Test
    public void testLastUpdateChanged(){
        Article article = new Article(admin, "art1", "cont1");
        articleService.persist(article);

        long created = article.getCreated().getTime();
        long updated = article.getUpdated().getTime();

        LOG.info("Created: " + created);
        LOG.info("Updated: " + updated);

        Assert.assertEquals(created, updated);

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
        }

        article.setContent("Content upr");
        articleService.merge(article);

        article = articleService.get(article.getId());
        Assert.assertEquals("Content upr", article.getContent());

        LOG.info("Updated after update: " + article.getUpdated().getTime());
        Assert.assertTrue(updated < article.getUpdated().getTime());

        LOG.info("Created after update: " + article.getCreated().getTime());
        Assert.assertEquals(created, article.getCreated().getTime());
    }

    @Test
    public void testIsPartOf(){
        Article art1 = new Article(admin, "art1", "cont1");
        articleService.persist(art1);

        Article art2 = new Article(admin, "art2", "cont2");
        articleService.persist(art2);

        Ticket t1 = new Ticket(admin, "t1", "t1info");
        ticketService.persist(t1);

        Ticket t2 = new Ticket(admin, "t2", "t2info");
        ticketService.persist(t2);

        t1 = ticketService.addContains(t1, t2);
        t1 = ticketService.addContains(t1, art1);
        t2 = ticketService.addContains(t2, art2);

        t1 = ticketService.loadLazyCollections(ticketService.get(t1.getId()));
        t2 = ticketService.loadLazyCollections(ticketService.get(t2.getId()));
        art1 = articleService.loadLazyCollections(articleService.get(art1.getId()));
        art2 = articleService.loadLazyCollections(articleService.get(art2.getId()));

        Assert.assertTrue(t2.getPartOf().contains(t1));
        Assert.assertTrue(art1.getPartOf().contains(t1));
        Assert.assertTrue(art2.getPartOf().contains(t2));

        Assert.assertFalse(art2.getPartOf().contains(t1));
    }

    @Test
    public void testCreator(){
        Article art1 = new Article(admin, "art1", "cont1");
        articleService.persist(art1);

        Article art2 = new Article(regular, "art2", "cont2");
        articleService.persist(art2);

        User a = userService.getUser("admin");
        a = userService.loadLazyCollections(a);
        Assert.assertTrue(a.getCreatedItems().contains(art1));

        User r = userService.getUser("regular");
        Assert.assertTrue(userService.loadLazyCollections(r).getCreatedItems().contains(art2));
    }
}
