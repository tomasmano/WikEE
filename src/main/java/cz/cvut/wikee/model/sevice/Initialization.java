package cz.cvut.wikee.model.sevice;

import cz.cvut.wikee.model.persistence.Storage;
import cz.cvut.wikee.model.persistence.entity.Article;
import cz.cvut.wikee.model.persistence.entity.Role;
import cz.cvut.wikee.model.persistence.entity.Ticket;
import cz.cvut.wikee.model.persistence.entity.User;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 19.1.13
 * Time: 20:22
 *
 * Singleton to insert init data into DB
 */
@Singleton
@Startup
public class Initialization {
    public Logger logger = Logger.getLogger(this.getClass());

    @Inject @Storage
    private EntityManager em;

    @Inject
    private RoleService roleService;

    @Inject
    private TicketService ticketService;

    @Inject
    private ArticleService articleService;

    @Inject
    private SettingsService settingsService;

    @PostConstruct
    public void init(){

        logger.info("Initializing database data...");

        // Init db only if it is not initialized zet
        if(settingsService.get("DB_INIT").equals("TRUE")){
            logger.info("Already initialized, nothing to do.");
            return;
        }

        logger.debug("Inserting roles...");
        Role admin = new Role("ADMIN");
        roleService.saveOrUpdate(admin);

        Role redactor = new Role("REDACTOR");
        roleService.saveOrUpdate(redactor);

        Role regular = new Role("REGULAR");
        roleService.saveOrUpdate(regular);

        logger.debug("Inserting users...");
        User u = new User(null, "Václav", "Čokrt", "admin", "admin", admin);
        em.persist(u);

        User uu = new User(null, "Evžen", "Dlouhý", "evzen", "evzen", redactor);
        em.persist(uu);

        User uuu = new User(null, "Karel", "Pohoda", "karel", "karel", regular);
        em.persist(uuu);

        logger.debug("Inserting tickets...");
        Ticket tCars = new Ticket(u, "Auta", "Rubrika o autech");
        Ticket tComputers = new Ticket(u, "Počítače", "Rubrika o počítačích");
        Ticket tNotebooks = new Ticket(u, "Notebooky", "Rubrika o noteboocích");
        tComputers.addContains(tNotebooks);

        ticketService.persist(tCars);
        ticketService.persist(tComputers);
        ticketService.persist(tNotebooks);

        logger.debug("Inserting articles...");
        Article article = new Article(u, "Nová Octávie", "Lorem ipsum ...");
        article.addPartOf(tCars);

        Article article2 = new Article(u, "Strašná autonehoda", "Lorem ipsum ...");
        article2.addPartOf(tCars);

        articleService.persist(article);
        articleService.persist(article2);

        logger.debug("Marked as initialized...");
        settingsService.set("DB_INIT", "TRUE");

        logger.info("DB initialization is done.");
    }
}
