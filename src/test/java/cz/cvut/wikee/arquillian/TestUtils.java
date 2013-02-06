package cz.cvut.wikee.arquillian;

import cz.cvut.wikee.model.persistence.AbstractDAO;
import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.*;
import cz.cvut.wikee.model.sevice.*;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 4.2.13
 * Time: 19:46
 */

@Stateless
public class TestUtils {
    private static final Logger LOG = Logger.getLogger(TestUtils.class);

    @Inject
    @Storage
    private EntityManager em;

    @Inject
    private RoleService roleService;

    @Inject
    private TicketService ticketService;

    @Inject
    private ArticleService articleService;

    @Inject
    private UserService userService;

    @Inject
    private SettingsService settingsService;

    public void insertSampleDataFull(){
        LOG.info("START - Initializing database data FULL...");

        LOG.debug("Inserting roles...");
        Role admin = new Role("ADMIN");
        roleService.saveOrUpdate(admin);

        Role redactor = new Role("REDACTOR");
        roleService.saveOrUpdate(redactor);

        Role regular = new Role("REGULAR");
        roleService.saveOrUpdate(regular);

        LOG.debug("Inserting users...");
        User u = new User(null, "Václav", "Čokrt", "admin", "admin", admin);
        em.persist(u);

        User uu = new User(null, "Evžen", "Dlouhý", "evzen", "evzen", redactor);
        em.persist(uu);

        User uuu = new User(null, "Karel", "Pohoda", "karel", "karel", regular);
        em.persist(uuu);

        LOG.debug("Inserting tickets...");
        Ticket tCars = new Ticket(u, "Auta", "Rubrika o autech");
        Ticket tComputers = new Ticket(u, "Počítače", "Rubrika o počítačích");
        Ticket tNotebooks = new Ticket(u, "Notebooky", "Rubrika o noteboocích");
        tComputers.addContains(tNotebooks);

        ticketService.persist(tCars);
        ticketService.persist(tComputers);
        ticketService.persist(tNotebooks);

        LOG.debug("Inserting articles...");
        Article article = new Article(u, "Nová Octávie", "Lorem ipsum ...");
        articleService.persist(article);
        article.addPartOf(tCars);

        Article article2 = new Article(u, "Strašná autonehoda", "Lorem ipsum ...");
        articleService.persist(article2);
        article2.addPartOf(tCars);

        LOG.info("END - Initializing database data FULL...");
    }

    public void insertSampleDataBasics(){
        LOG.info("START - Initializing database data BASICS...");

        LOG.debug("Inserting roles...");
        Role admin = new Role("ADMIN");
        roleService.saveOrUpdate(admin);

        Role redactor = new Role("REDACTOR");
        roleService.saveOrUpdate(redactor);

        Role regular = new Role("REGULAR");
        roleService.saveOrUpdate(regular);

        LOG.debug("Inserting users...");
        User u = new User(null, "Václav", "Čokrt", "admin", "admin", admin);
        em.persist(u);

        User uu = new User(null, "Evžen", "Dlouhý", "redactor", "redactor", redactor);
        em.persist(uu);

        User uuu = new User(null, "Karel", "Pohoda", "regular", "regular", regular);
        em.persist(uuu);

        LOG.info("END - Initializing database data BASICS...");
    }

    public void clearDatabase(){
        for(Article a : articleService.getAll()){
            articleService.remove(a);
        }

        for(User u : userService.getAll()){
            userService.remove(u);
        }

        for(Role r : roleService.getAll()){
            roleService.remove(r);
        }

        for(Ticket t : ticketService.getAll()){
            ticketService.remove(t);
        }

        for(Setting s : AbstractDAO.getAll(em, Setting.class)){
            em.remove(s);
        }
    }
}
