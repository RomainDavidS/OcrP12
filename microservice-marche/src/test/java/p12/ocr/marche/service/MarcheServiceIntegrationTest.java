package p12.ocr.marche.service;


import p12.ocr.marche.model.Marche;
import p12.ocr.marche.repository.IMarcheRepository;
import p12.ocr.marche.service.marche.IMarcheService;
import p12.ocr.marche.service.marche.MarcheServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class MarcheServiceIntegrationTest {

    @TestConfiguration
    static class MarcheServiceIntegrationTestContextConfiguration {
        @Bean
        public MarcheServiceImpl marcheService() {return new MarcheServiceImpl();}
    }

    @Autowired
    private IMarcheService marcheService;

    @MockBean
    private IMarcheRepository marcheRepository;


    @Before
    public void setUp() {

        Marche marche1 = new Marche();
        marche1.setId( 1L );
        marche1.setName("Marche1");

        Marche marche2 = new Marche();
        marche2.setName("Marche2");

        Marche marche3 = new Marche();
        marche3.setName("Marche3");

        List<Marche> marcheList = Arrays.asList( marche1,marche2,marche3 );

        Mockito.when(marcheRepository.findById( marche1.getId() ) ).thenReturn( Optional.of( marche1 )  );

        Mockito.when(marcheRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(marcheRepository.findAll(
                Sort.by( Sort.Direction.DESC,"typeMarche" )
                        .and(Sort.by( Sort.Direction.ASC, "idOrganization") )
                        .and(Sort.by( Sort.Direction.ASC, "name") ))).thenReturn( marcheList );

        Mockito.when(marcheRepository.save(any(Marche.class))).thenReturn(marche1);
    }

    @Test
    public void whenValidId_thenMarcheShouldBeFound() {
        Marche fromDb = marcheService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "Marche1" );
        verifyMarcheFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenMarcheShouldNotBeFound() {
        Marche fromDb = marcheService.findById( -99L );
        verifyMarcheFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyMarcheFindByIdCalledOnce() {
        Mockito.verify(marcheRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( marcheRepository );
    }

    @Test
    public void given3Marche_whenGetAll_thenReturn3Records() {

        Marche marche1 = new Marche();
        marche1.setName("Marche1");

        Marche marche2 = new Marche();
        marche2.setName("Marche2");

        Marche marche3 = new Marche();
        marche3.setName("Marche3");

        List<Marche> marcheList = marcheService.findAll();
        verifyFindAllMarcheIsCalledOnce();

        assertThat(marcheList).hasSize(3).extracting(Marche::getName).containsOnly(
                marche1.getName(), marche2.getName(), marche3.getName());
    }

    private void verifyFindAllMarcheIsCalledOnce() {
        Mockito.verify(marcheRepository, VerificationModeFactory.times(1)).findAll(
                Sort.by( Sort.Direction.DESC,"typeMarche" )
                        .and(Sort.by( Sort.Direction.ASC, "idOrganization") )
                        .and(Sort.by( Sort.Direction.ASC, "name") ));
        Mockito.reset( marcheRepository );
    }

    @Test
    public void whenSaveMarche_thenReturnMarche() {

        Marche marche = marcheService.save(  new Marche() );
        Marche fromDb = marcheService.findById( marche.getId() );
        verifySaveMarcheCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( marche.getId() );
    }

    private void verifySaveMarcheCalledOnce(){

        Mockito.verify(marcheRepository, VerificationModeFactory.times(1) ).save( any(Marche.class) );
        Mockito.reset( marcheRepository );
    }

}
