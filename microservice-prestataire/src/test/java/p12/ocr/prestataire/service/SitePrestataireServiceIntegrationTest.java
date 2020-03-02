package p12.ocr.prestataire.service;

import p12.ocr.prestataire.model.SitePrestataire;
import p12.ocr.prestataire.repository.ISitePrestataireRepository;
import p12.ocr.prestataire.service.siteprestataire.ISitePrestataireService;
import p12.ocr.prestataire.service.siteprestataire.SitePrestataireServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class SitePrestataireServiceIntegrationTest {

    @TestConfiguration
    static class SitePrestataireServiceIntegrationTestContextConfiguration {
        @Bean
        public SitePrestataireServiceImpl sitePrestataireService() {return new SitePrestataireServiceImpl();}
    }

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @MockBean
    private ISitePrestataireRepository sitePrestataireRepository;


    @Before
    public void setUp() {

        SitePrestataire sitePrestataire1 = new SitePrestataire();
        sitePrestataire1.setId( 1L );
        sitePrestataire1.setName("SitePrestataire1");

        SitePrestataire sitePrestataire2 = new SitePrestataire();
        sitePrestataire2.setName("SitePrestataire2");

        SitePrestataire sitePrestataire3 = new SitePrestataire();
        sitePrestataire3.setName("SitePrestataire3");

        List<SitePrestataire> sitePrestataireList = Arrays.asList( sitePrestataire1,sitePrestataire2,sitePrestataire3 );

        Mockito.when(sitePrestataireRepository.findById( sitePrestataire1.getId() ) ).thenReturn( Optional.of( sitePrestataire1 )  );

        Mockito.when(sitePrestataireRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(sitePrestataireRepository.findAll()).thenReturn( sitePrestataireList );

        Mockito.when(sitePrestataireRepository.save(any(SitePrestataire.class))).thenReturn(sitePrestataire1);
    }

    @Test
    public void whenValidId_thenSitePrestataireShouldBeFound() {
        SitePrestataire fromDb = sitePrestataireService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "SitePrestataire1" );
        verifySitePrestataireFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenSitePrestataireShouldNotBeFound() {
        SitePrestataire fromDb = sitePrestataireService.findById( -99L );
        verifySitePrestataireFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifySitePrestataireFindByIdCalledOnce() {
        Mockito.verify(sitePrestataireRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( sitePrestataireRepository );
    }

    @Test
    public void given3SitePrestataire_whenGetAll_thenReturn3Records() {

        SitePrestataire sitePrestataire1 = new SitePrestataire();
        sitePrestataire1.setName("SitePrestataire1");

        SitePrestataire sitePrestataire2 = new SitePrestataire();
        sitePrestataire2.setName("SitePrestataire2");

        SitePrestataire sitePrestataire3 = new SitePrestataire();
        sitePrestataire3.setName("SitePrestataire3");

        List<SitePrestataire> sitePrestataireList = sitePrestataireService.findAll();
        verifyFindAllSitePrestataireIsCalledOnce();

        assertThat(sitePrestataireList).hasSize(3).extracting(SitePrestataire::getName).containsOnly(
                sitePrestataire1.getName(), sitePrestataire2.getName(), sitePrestataire3.getName() );
    }

    private void verifyFindAllSitePrestataireIsCalledOnce() {
        Mockito.verify(sitePrestataireRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( sitePrestataireRepository );
    }

    @Test
    public void whenSaveSitePrestataire_thenReturnCallCenter() {

        SitePrestataire sitePrestataire = sitePrestataireService.save(  new SitePrestataire() );
        SitePrestataire fromDb = sitePrestataireService.findById( sitePrestataire.getId() );
        verifySaveSitePrestataireCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( sitePrestataire.getId() );
    }

    private void verifySaveSitePrestataireCalledOnce(){

        Mockito.verify(sitePrestataireRepository, VerificationModeFactory.times(1) ).save( any(SitePrestataire.class) );
        Mockito.reset( sitePrestataireRepository );
    }

}
