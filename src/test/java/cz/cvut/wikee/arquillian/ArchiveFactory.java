package cz.cvut.wikee.arquillian;

import org.apache.log4j.Logger;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;

/**
 * Created by
 * User: Vaclav Cokrt
 * Email: beziks@gmail.com
 * Date: 4.2.13
 * Time: 19:41
 */
public class ArchiveFactory {
    private static final Logger LOG = Logger.getLogger(ArchiveFactory.class);

    public static WebArchive createArchive() {
        WebArchive arch = ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                .addAsWebInfResource(new File("src/test/resources/test-ds.xml"))
                .addAsResource(new File("src/test/resources"), "")
                .addPackages(true, "cz.cvut.wikee.model")
                .addPackages(true, "cz.cvut.wikee.arquillian")
                ;

        arch.delete("/WEB-INF/classes/cz/cvut/wikee/model/sevice/Initialization.class");

        /*arch.addAsLibraries(Maven.resolver()
                .loadPomFromFile("pom.xml").resolve("org.apache.jena:jena-arq")
                .withTransitivity().asFile()
        );

        arch.addAsLibraries(Maven.resolver()
                .loadPomFromFile("pom.xml").resolve("net.sourceforge.htmlcleaner:htmlcleaner")
                .withTransitivity().asFile()
        );   */

        LOG.info("TEST ARCHIVE created, content:\n " + arch.toString(true) + "\n");
        return arch;
    }
}
