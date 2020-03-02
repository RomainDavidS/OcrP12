package p12.ocr.prestataire.repository;
import p12.ocr.prestataire.model.SitePrestataire;
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
public class SitePrestataireRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ISitePrestataireRepository sitePrestataireRepository;

    @Before
    public void setUp(){ sitePrestataireRepository.deleteAll(); }

    @After
    public void erase(){
        sitePrestataireRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnSitePrestataire() {

        SitePrestataire sitePrestataire = createTestSitePrestataire("SitePrestataire");

        entityManager.persistAndFlush(sitePrestataire);

        SitePrestataire fromDb = sitePrestataireRepository.findById( sitePrestataire.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( sitePrestataire.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        SitePrestataire fromDb = sitePrestataireRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveSitePrestataire_thenReturnSitePrestataire() {
        SitePrestataire sitePrestataire = createTestSitePrestataire("SitePrestataire");
        sitePrestataireRepository.save( sitePrestataire );
        SitePrestataire fromDb = sitePrestataireRepository.findById( sitePrestataire.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( sitePrestataire.getId() );
    }


    @Test
    public void givenSetOfSitePrestataire_whenFindAll_thenReturnAllSitePrestataire() {

        SitePrestataire sitePrestataire1 = createTestSitePrestataire("SitePrestataire1");
        SitePrestataire sitePrestataire2 = createTestSitePrestataire("SitePrestataire2");
        SitePrestataire sitePrestataire3 = createTestSitePrestataire("SitePrestataire3");

        entityManager.persist(sitePrestataire1);
        entityManager.persist(sitePrestataire2);
        entityManager.persist(sitePrestataire3);
        entityManager.flush();

        List<SitePrestataire> allSitePrestataire = sitePrestataireRepository.findAll();

        assertThat(allSitePrestataire).hasSize(3).extracting(SitePrestataire::getId).containsOnly(
                sitePrestataire1.getId(), sitePrestataire2.getId(), sitePrestataire3.getId());
    }

    private SitePrestataire createTestSitePrestataire(String name){
        SitePrestataire sitePrestataire = new SitePrestataire();
        sitePrestataire.setName( name );
        return sitePrestataire;
    }
}
