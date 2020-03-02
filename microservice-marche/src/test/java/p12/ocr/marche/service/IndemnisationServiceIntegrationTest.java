package p12.ocr.marche.service;


import p12.ocr.marche.model.Indemnisation;
import p12.ocr.marche.repository.IIndemnisationRepository;
import p12.ocr.marche.service.indemnisation.IIndemnisationService;
import p12.ocr.marche.service.indemnisation.IndemnisationServiceImpl;
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
public class IndemnisationServiceIntegrationTest {

    @TestConfiguration
    static class IndemnisationServiceIntegrationTestContextConfiguration {
        @Bean
        public IndemnisationServiceImpl indemnisationService() {return new IndemnisationServiceImpl();}
    }

    @Autowired
    private IIndemnisationService indemnisationService;

    @MockBean
    private IIndemnisationRepository indemnisationRepository;


    @Before
    public void setUp() {

        Indemnisation indemnisation1 = new Indemnisation();
        indemnisation1.setId( 1L );
        indemnisation1.setPdl("Pdl1");

        Indemnisation indemnisation2 = new Indemnisation();
        indemnisation2.setPdl("Pdl2");

        Indemnisation indemnisation3 = new Indemnisation();
        indemnisation3.setPdl("Pdl3");

        List<Indemnisation> indemnisationList = Arrays.asList( indemnisation1,indemnisation2,indemnisation3 );

        Mockito.when(indemnisationRepository.findById( indemnisation1.getId() ) ).thenReturn( Optional.of( indemnisation1 )  );

        Mockito.when(indemnisationRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(indemnisationRepository.findAll()).thenReturn( indemnisationList );

        Mockito.when(indemnisationRepository.save(any(Indemnisation.class))).thenReturn(indemnisation1);
    }

    @Test
    public void whenValidId_thenIndemnisationShouldBeFound() {
        Indemnisation fromDb = indemnisationService.findById( 1l );
        assertThat(fromDb.getPdl()).isEqualTo( "Pdl1" );
        verifyIndemnisationFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenIndemnisationShouldNotBeFound() {
        Indemnisation fromDb = indemnisationService.findById( -99L );
        verifyIndemnisationFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyIndemnisationFindByIdCalledOnce() {
        Mockito.verify(indemnisationRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( indemnisationRepository );
    }

    @Test
    public void given3Indemnisation_whenGetAll_thenReturn3Records() {

        Indemnisation indemnisation1 = new Indemnisation();
        indemnisation1.setPdl("Pdl1");

        Indemnisation indemnisation2 = new Indemnisation();
        indemnisation2.setPdl("Pdl2");

        Indemnisation indemnisation3 = new Indemnisation();
        indemnisation3.setPdl("Pdl3");

        List<Indemnisation> indemnisationList = indemnisationService.findAll();
        verifyFindAllIndemnisationIsCalledOnce();

        assertThat(indemnisationList).hasSize(3).extracting(Indemnisation::getPdl).containsOnly(
                indemnisation1.getPdl(), indemnisation2.getPdl(), indemnisation3.getPdl() );
    }

    private void verifyFindAllIndemnisationIsCalledOnce() {
        Mockito.verify(indemnisationRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( indemnisationRepository );
    }

    @Test
    public void whenSaveIndemnisation_thenReturnIndemnisation() {

        Indemnisation indemnisation = indemnisationService.save(  new Indemnisation() );
        Indemnisation fromDb = indemnisationService.findById( indemnisation.getId() );
        verifySaveIndemnisationCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( indemnisation.getId() );
    }

    private void verifySaveIndemnisationCalledOnce(){

        Mockito.verify(indemnisationRepository, VerificationModeFactory.times(1) ).save( any(Indemnisation.class) );
        Mockito.reset( indemnisationRepository );
    }

}
