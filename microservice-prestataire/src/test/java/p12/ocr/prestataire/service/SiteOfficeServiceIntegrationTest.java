package p12.ocr.prestataire.service;

import p12.ocr.prestataire.model.SiteOffice;
import p12.ocr.prestataire.repository.ISiteOfficeRepository;
import p12.ocr.prestataire.service.siteoffice.ISiteOfficeService;
import p12.ocr.prestataire.service.siteoffice.SiteOfficeServiceImpl;
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
public class SiteOfficeServiceIntegrationTest {

    @TestConfiguration
    static class SiteOfficeServiceIntegrationTestContextConfiguration {
        @Bean
        public SiteOfficeServiceImpl siteOfficeService() {return new SiteOfficeServiceImpl();}
    }

    @Autowired
    private ISiteOfficeService siteOfficeService;

    @MockBean
    private ISiteOfficeRepository siteOfficeRepository;


    @Before
    public void setUp() {

        SiteOffice siteOffice1 = new SiteOffice();
        siteOffice1.setId( 1L );
        siteOffice1.setRhName("SiteOffice1");

        SiteOffice siteOffice2 = new SiteOffice();
        siteOffice2.setRhName("SiteOffice2");

        SiteOffice siteOffice3 = new SiteOffice();
        siteOffice3.setRhName("SiteOffice3");

        List<SiteOffice> siteOfficeList = Arrays.asList( siteOffice1,siteOffice2,siteOffice3 );

        Mockito.when(siteOfficeRepository.findById( siteOffice1.getId() ) ).thenReturn( Optional.of( siteOffice1 )  );

        Mockito.when(siteOfficeRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(siteOfficeRepository.findAll()).thenReturn( siteOfficeList );

        Mockito.when(siteOfficeRepository.save(any(SiteOffice.class))).thenReturn(siteOffice1);

    }

    @Test
    public void whenValidId_thenSiteOfficeShouldBeFound() {
        SiteOffice fromDb = siteOfficeService.findById( 1l );
        assertThat(fromDb.getRhName()).isEqualTo( "SiteOffice1" );
        verifySiteOfficeFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenSiteOfficeShouldNotBeFound() {
        SiteOffice fromDb = siteOfficeService.findById( -99L );
        verifySiteOfficeFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifySiteOfficeFindByIdCalledOnce() {
        Mockito.verify(siteOfficeRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( siteOfficeRepository );
    }

    @Test
    public void given3SiteOffice_whenGetAll_thenReturn3Records() {

        SiteOffice siteOffice1 = new SiteOffice();
        siteOffice1.setRhName("SiteOffice1");

        SiteOffice siteOffice2 = new SiteOffice();
        siteOffice2.setRhName("SiteOffice2");

        SiteOffice siteOffice3 = new SiteOffice();
        siteOffice3.setRhName("SiteOffice3");

        List<SiteOffice> siteOfficeList = siteOfficeService.findAll();
        verifyFindAllSiteOfficeIsCalledOnce();

        assertThat(siteOfficeList).hasSize(3).extracting(SiteOffice::getRhName).containsOnly(
                siteOffice1.getRhName(), siteOffice2.getRhName(), siteOffice3.getRhName() );
    }

    private void verifyFindAllSiteOfficeIsCalledOnce() {
        Mockito.verify(siteOfficeRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( siteOfficeRepository );
    }

    @Test
    public void whenSaveCallCenter_thenReturnCallCenter() {

        SiteOffice siteOffice = siteOfficeService.save(  new SiteOffice() );
        SiteOffice fromDb = siteOfficeService.findById( siteOffice.getId() );
        verifySaveSiteOfficeCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( siteOffice.getId() );
    }

    private void verifySaveSiteOfficeCalledOnce(){

        Mockito.verify(siteOfficeRepository, VerificationModeFactory.times(1) ).save( any(SiteOffice.class) );
        Mockito.reset( siteOfficeRepository );
    }
    @Test
    public void whenDelete_thenSiteOfficeShouldBeDeleted() {
        SiteOffice siteOffice = siteOfficeService.save(  new SiteOffice() );
        siteOfficeService.delete( siteOffice.getId() );
        verifyDeleteSiteOfficeCalledOnce();
    }
    private void verifyDeleteSiteOfficeCalledOnce(){
        Mockito.verify(siteOfficeRepository, VerificationModeFactory.times(1)).delete(any( SiteOffice.class ) );
        Mockito.reset( siteOfficeRepository );
    }

}
