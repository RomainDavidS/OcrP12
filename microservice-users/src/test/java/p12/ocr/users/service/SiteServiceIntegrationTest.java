package p12.ocr.users.service;

import p12.ocr.users.model.Site;
import p12.ocr.users.repository.ISiteRepository;
import p12.ocr.users.service.site.ISiteService;
import p12.ocr.users.service.site.SiteServiceImpl;
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
public class SiteServiceIntegrationTest {

    @TestConfiguration
    static class SiteServiceIntegrationTestContextConfiguration {
        @Bean
        public SiteServiceImpl siteService() {return new SiteServiceImpl();}
    }

    @Autowired
    private ISiteService siteService;

    @MockBean
    private ISiteRepository siteRepository;


    @Before
    public void setUp() {

        Site site1 = new Site("Site1");
        site1.setId( 1L );

        Site site2 = new Site("Site2");

        Site site3 = new Site("Site3");

        List<Site> siteList = Arrays.asList( site1,site2,site3 );

        Mockito.when(siteRepository.findById( site1.getId() ) ).thenReturn( Optional.of( site1 )  );

        Mockito.when(siteRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(siteRepository.findAll()).thenReturn( siteList );

        Mockito.when(siteRepository.save(any(Site.class))).thenReturn(site1);
    }

    @Test
    public void whenValidId_thenSiteShouldBeFound() {
        Site fromDb = siteService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "Site1" );
        verifySiteFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenSiteShouldNotBeFound() {
        Site fromDb = siteService.findById( -99L );
        verifySiteFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifySiteFindByIdCalledOnce() {
        Mockito.verify(siteRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( siteRepository );
    }

    @Test
    public void given3Site_whenGetAll_thenReturn3Records() {

        Site site1 = new Site("Site1");
        Site site2 = new Site("Site2");
        Site site3 = new Site("Site3");

        List<Site> siteList = siteService.findAll();
        verifyFindAllSiteIsCalledOnce();

        assertThat(siteList).hasSize(3).extracting(Site::getName).containsOnly(
                site1.getName(), site2.getName(), site3.getName());
    }

    private void verifyFindAllSiteIsCalledOnce() {
        Mockito.verify(siteRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( siteRepository );
    }

    @Test
    public void whenSaveSite_thenReturnSite() {

        Site site = siteService.save(  new Site("Site1") );
        Site fromDb = siteService.findById( site.getId() );
        verifySaveSiteCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( site.getId() );
    }
    private void verifySaveSiteCalledOnce(){

        Mockito.verify(siteRepository, VerificationModeFactory.times(1) ).save( any(Site.class) );
        Mockito.reset( siteRepository );
    }

}
