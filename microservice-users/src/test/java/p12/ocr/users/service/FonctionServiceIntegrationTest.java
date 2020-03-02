package p12.ocr.users.service;

import p12.ocr.users.model.Fonction;
import p12.ocr.users.repository.IFonctionRepository;
import p12.ocr.users.service.fonction.FonctionServiceImpl;
import p12.ocr.users.service.fonction.IFonctionService;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class FonctionServiceIntegrationTest {

    @TestConfiguration
    static class FonctionServiceIntegrationTestContextConfiguration {
        @Bean
        public FonctionServiceImpl fonctionService() {return new FonctionServiceImpl();}
    }

    @Autowired
    private IFonctionService fonctionService;

    @MockBean
    private IFonctionRepository fonctionRepository;


    @Before
    public void setUp() {

        Fonction fonction1 = new Fonction("Fonction1");
        fonction1.setId( 1L );

        Fonction fonction2 = new Fonction("Fonction2");

        Fonction fonction3 = new Fonction("Fonction3");

        List<Fonction> fonctionList = Arrays.asList( fonction1,fonction2,fonction3 );

        Mockito.when(fonctionRepository.findById( fonction1.getId() ) ).thenReturn( Optional.of( fonction1 )  );

        Mockito.when(fonctionRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(fonctionRepository.findAll()).thenReturn( fonctionList );

        Mockito.when(fonctionRepository.save(any(Fonction.class))).thenReturn(fonction1);
    }

    @Test
    public void whenValidId_thenFonctionShouldBeFound() {
        Fonction fromDb = fonctionService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "Fonction1" );
        verifyFonctionFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenFonctionShouldNotBeFound() {
        Fonction fromDb = fonctionService.findById( -99L );
        verifyFonctionFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyFonctionFindByIdCalledOnce() {
        Mockito.verify(fonctionRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( fonctionRepository );
    }

    @Test
    public void given3Fonction_whenGetAll_thenReturn3Records() {

        Fonction fonction1 = new Fonction("Fonction1");
        Fonction fonction2 = new Fonction("Fonction2");
        Fonction fonction3 = new Fonction("Fonction3");

        List<Fonction> fonctionList = fonctionService.findAll();
        verifyFindAllFonctionIsCalledOnce();

        assertThat(fonctionList).hasSize(3).extracting(Fonction::getName).containsOnly(
                fonction1.getName(), fonction2.getName(), fonction3.getName());
    }

    private void verifyFindAllFonctionIsCalledOnce() {
        Mockito.verify(fonctionRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( fonctionRepository );
    }

    @Test
    public void whenSaveFonction_thenReturnFonction() {

        Fonction fonction = fonctionService.save(  new Fonction("Fonction1") );
        Fonction fromDb = fonctionService.findById( fonction.getId() );
        verifySaveFonctionCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( fonction.getId() );
    }

    private void verifySaveFonctionCalledOnce(){

        Mockito.verify(fonctionRepository, VerificationModeFactory.times(1) ).save( any(Fonction.class) );
        Mockito.reset( fonctionRepository );
    }

}
