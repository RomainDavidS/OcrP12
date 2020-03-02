package p12.ocr.prestataire.service;

import p12.ocr.prestataire.model.FonctionPrestataire;
import p12.ocr.prestataire.repository.IFonctionPrestataireRepository;
import p12.ocr.prestataire.service.fonctionprestataire.FonctionPrestataireImpl;
import p12.ocr.prestataire.service.fonctionprestataire.IFonctionPrestataireService;
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
public class FonctionPrestataireServiceIntegrationTest {

    @TestConfiguration
    static class FonctionPrestataireServiceIntegrationTestContextConfiguration {
        @Bean
        public FonctionPrestataireImpl fonctionPrestataire() {return new FonctionPrestataireImpl();}
    }

    @Autowired
    private IFonctionPrestataireService fonctionPrestataireService;

    @MockBean
    private IFonctionPrestataireRepository fonctionPrestataireRepository;


    @Before
    public void setUp() {

        FonctionPrestataire fonctionPrestataire1 = new FonctionPrestataire();
        fonctionPrestataire1.setId( 1L );
        fonctionPrestataire1.setName("FonctionPrestataire1");

        FonctionPrestataire fonctionPrestataire2 = new FonctionPrestataire();
        fonctionPrestataire2.setName("FonctionPrestataire2");

        FonctionPrestataire fonctionPrestataire3 = new FonctionPrestataire();
        fonctionPrestataire3.setName("FonctionPrestataire3");

        List<FonctionPrestataire> fonctionPrestataireList = Arrays.asList( fonctionPrestataire1,fonctionPrestataire2,fonctionPrestataire3 );

        Mockito.when(fonctionPrestataireRepository.findById( fonctionPrestataire1.getId() ) ).thenReturn( Optional.of( fonctionPrestataire1 )  );

        Mockito.when(fonctionPrestataireRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(fonctionPrestataireRepository.findAll()).thenReturn( fonctionPrestataireList );

        Mockito.when(fonctionPrestataireRepository.save(any(FonctionPrestataire.class))).thenReturn(fonctionPrestataire1);
    }

    @Test
    public void whenValidId_thenFonctionPrestataireShouldBeFound() {
        FonctionPrestataire fromDb = fonctionPrestataireService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "FonctionPrestataire1" );
        verifyFonctionPrestataireFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenFonctionPrestataireShouldNotBeFound() {
        FonctionPrestataire fromDb = fonctionPrestataireService.findById( -99L );
        verifyFonctionPrestataireFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyFonctionPrestataireFindByIdCalledOnce() {
        Mockito.verify(fonctionPrestataireRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( fonctionPrestataireRepository );
    }

    @Test
    public void given3FonctionPrestataire_whenGetAll_thenReturn3Records() {

        FonctionPrestataire fonctionPrestataire1 = new FonctionPrestataire();
        fonctionPrestataire1.setName("FonctionPrestataire1");

        FonctionPrestataire fonctionPrestataire2 = new FonctionPrestataire();
        fonctionPrestataire2.setName("FonctionPrestataire2");

        FonctionPrestataire fonctionPrestataire3 = new FonctionPrestataire();
        fonctionPrestataire3.setName("FonctionPrestataire3");

        List<FonctionPrestataire> fonctionPrestataireList = fonctionPrestataireService.findAll();
        verifyFindAllFonctionPrestataireIsCalledOnce();

        assertThat(fonctionPrestataireList).hasSize(3).extracting(FonctionPrestataire::getName).containsOnly(
                fonctionPrestataire1.getName(), fonctionPrestataire2.getName(), fonctionPrestataire3.getName() );
    }

    private void verifyFindAllFonctionPrestataireIsCalledOnce() {
        Mockito.verify(fonctionPrestataireRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( fonctionPrestataireRepository );
    }

    @Test
    public void whenSaveFonctionPrestataire_thenReturnFonctionPrestataire() {

        FonctionPrestataire fonctionPrestataire = fonctionPrestataireService.save(  new FonctionPrestataire() );
        FonctionPrestataire fromDb = fonctionPrestataireService.findById( fonctionPrestataire.getId() );
        verifySaveFonctionPrestataireCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( fonctionPrestataire.getId() );
    }

    private void verifySaveFonctionPrestataireCalledOnce(){

        Mockito.verify(fonctionPrestataireRepository, VerificationModeFactory.times(1) ).save( any(FonctionPrestataire.class) );
        Mockito.reset( fonctionPrestataireRepository );
    }

}
