package p12.ocr.prestataire.repository;
import p12.ocr.prestataire.model.SiteOffice;
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
public class SiteOfficeRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ISiteOfficeRepository siteOfficeRepository;

    @Before
    public void setUp(){ siteOfficeRepository.deleteAll(); }

    @After
    public void erase(){
        siteOfficeRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnSiteOffice() {

        SiteOffice siteOffice = createTestSiteOffice("SiteOffice");

        entityManager.persistAndFlush(siteOffice);

        SiteOffice fromDb = siteOfficeRepository.findById( siteOffice.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( siteOffice.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        SiteOffice fromDb = siteOfficeRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveSiteOffice_thenReturnSiteOffice() {
        SiteOffice siteOffice = createTestSiteOffice("SiteOffice");
        siteOfficeRepository.save( siteOffice );
        SiteOffice fromDb = siteOfficeRepository.findById( siteOffice.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( siteOffice.getId() );
    }

    @Test
    public void whenDeleteSiteOffice_thenReturnNull() {
        SiteOffice siteOffice = createTestSiteOffice("SiteOffice");
        siteOfficeRepository.save( siteOffice );
        siteOfficeRepository.delete( siteOffice );
        SiteOffice fromDb = siteOfficeRepository.findById( siteOffice.getId() ).orElse(null );
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfSiteOffice_whenFindAll_thenReturnAllSiteOffice() {

        SiteOffice siteOffice1 = createTestSiteOffice("SiteOffice1");
        SiteOffice siteOffice2 = createTestSiteOffice("SiteOffice2");
        SiteOffice siteOffice3 = createTestSiteOffice("SiteOffice3");

        entityManager.persist(siteOffice1);
        entityManager.persist(siteOffice2);
        entityManager.persist(siteOffice3);
        entityManager.flush();

        List<SiteOffice> allSiteOffice = siteOfficeRepository.findAll();

        assertThat(allSiteOffice).hasSize(3).extracting(SiteOffice::getId).containsOnly(
                siteOffice1.getId(), siteOffice2.getId(), siteOffice3.getId());
    }

    private SiteOffice createTestSiteOffice(String rhName){
        SiteOffice siteOffice = new SiteOffice();
        siteOffice.setRhName( rhName );
        return siteOffice;
    }
}
