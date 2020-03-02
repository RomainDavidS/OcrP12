package p12.ocr.users.repository;

import p12.ocr.users.model.Site;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class SiteRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ISiteRepository siteRepository;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Before
    public void setUp(){
        userEntrepriseRepository.deleteAll();
        siteRepository.deleteAll(); }

    @After
    public void erase(){
        siteRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnSite() {

        Site site = new Site("Site");

        entityManager.persistAndFlush(site);

        Site fromDb = siteRepository.findById( site.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( site.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Site fromDb = siteRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenSaveSite_thenReturnSite() {
        Site site = new Site("Site");
        siteRepository.save( site );
        Site fromDb = siteRepository.findById( site.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( site.getId() );
    }


    @Test
    public void givenSetOfSite_whenFindAll_thenReturnAllSite() {

        Site site1 = new Site("Site1");
        Site site2 = new Site("Site2");
        Site site3 = new Site("Site3");

        entityManager.persist(site1);
        entityManager.persist(site2);
        entityManager.persist(site3);
        entityManager.flush();

        List<Site> allSite = siteRepository.findAll();

        assertThat(allSite).hasSize(3).extracting(Site::getId).containsOnly(
                site1.getId(), site2.getId(), site3.getId());
    }

}
