package p12.ocr.prestataire.service;

import p12.ocr.prestataire.model.Entreprise;
import p12.ocr.prestataire.repository.IEntrepriseRepository;
import p12.ocr.prestataire.service.entreprise.EntrepriseServiceImpl;
import p12.ocr.prestataire.service.entreprise.IEntrepriseService;
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
public class EntrepriseServiceIntegrationTest {

    @TestConfiguration
    static class  EntrepriseServiceIntegrationTestContextConfiguration {
        @Bean
        public EntrepriseServiceImpl entrepriseService() {return new EntrepriseServiceImpl();}
    }

    @Autowired
    private IEntrepriseService entrepriseService;

    @MockBean
    private IEntrepriseRepository entrepriseRepository;


    @Before
    public void setUp() {

        Entreprise entreprise1 = new Entreprise();
        entreprise1.setId( 1L );
        entreprise1.setName("Entreprise1");

        Entreprise entreprise2 = new Entreprise();
        entreprise2.setName("Entreprise2");

        Entreprise entreprise3 = new Entreprise();
        entreprise3.setName("Entreprise3");

        List<Entreprise> entrepriseList = Arrays.asList( entreprise1,entreprise2,entreprise3 );

        Mockito.when(entrepriseRepository.findById( entreprise1.getId() ) ).thenReturn( Optional.of( entreprise1 )  );

        Mockito.when(entrepriseRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(entrepriseRepository.findAll()).thenReturn( entrepriseList );

        Mockito.when(entrepriseRepository.save(any(Entreprise.class))).thenReturn(entreprise1);
    }

    @Test
    public void whenValidId_thenEntrepriseShouldBeFound() {
        Entreprise fromDb = entrepriseService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "Entreprise1" );
        verifyEntrepriseFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenEntrepriseShouldNotBeFound() {
        Entreprise fromDb = entrepriseService.findById( -99L );
        verifyEntrepriseFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyEntrepriseFindByIdCalledOnce() {
        Mockito.verify(entrepriseRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( entrepriseRepository );
    }

    @Test
    public void given3Entreprise_whenGetAll_thenReturn3Records() {

        Entreprise entreprise1 = new Entreprise();
        entreprise1.setName("Entreprise1");

        Entreprise entreprise2 = new Entreprise();
        entreprise2.setName("Entreprise2");

        Entreprise entreprise3 = new Entreprise();
        entreprise3.setName("Entreprise3");

        List<Entreprise> entrepriseList = entrepriseService.findAll();
        verifyFindAllEntrepriseIsCalledOnce();

        assertThat(entrepriseList).hasSize(3).extracting(Entreprise::getName).containsOnly(
                entreprise1.getName(), entreprise2.getName(), entreprise3.getName() );
    }

    private void verifyFindAllEntrepriseIsCalledOnce() {
        Mockito.verify(entrepriseRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( entrepriseRepository );
    }

    @Test
    public void whenSaveEntreprise_thenReturnEntreprise() {

        Entreprise entreprise = entrepriseService.save(  new Entreprise() );
        Entreprise fromDb = entrepriseService.findById( entreprise.getId() );
        verifySaveEntrepriseCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( entreprise.getId() );
    }

    private void verifySaveEntrepriseCalledOnce(){

        Mockito.verify(entrepriseRepository, VerificationModeFactory.times(1) ).save( any(Entreprise.class) );
        Mockito.reset( entrepriseRepository );
    }

}
