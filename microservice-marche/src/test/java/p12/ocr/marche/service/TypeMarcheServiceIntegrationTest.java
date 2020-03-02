package p12.ocr.marche.service;

import p12.ocr.marche.model.TypeMarche;
import p12.ocr.marche.repository.ITypeMarcheRepository;
import p12.ocr.marche.service.typemarche.ITypeMarcheService;
import p12.ocr.marche.service.typemarche.TypeMarcheServiceImpl;
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
public class TypeMarcheServiceIntegrationTest {

    @TestConfiguration
    static class TypeMarcheServiceIntegrationTestContextConfiguration {
        @Bean
        public TypeMarcheServiceImpl typeMarcheService() {return new TypeMarcheServiceImpl();}
    }

    @Autowired
    private ITypeMarcheService typeMarcheService;

    @MockBean
    private ITypeMarcheRepository typeMarcheRepository;


    @Before
    public void setUp() {

        TypeMarche typeMarche1 = new TypeMarche();
        typeMarche1.setId( 1L );
        typeMarche1.setName("TypeMarche1");

        TypeMarche typeMarche2 = new TypeMarche();
        typeMarche2.setName("TypeMarche2");

        TypeMarche typeMarche3 = new TypeMarche();
        typeMarche3.setName("TypeMarche3");

        List<TypeMarche> typeMarcheList = Arrays.asList( typeMarche1,typeMarche2,typeMarche3 );

        Mockito.when(typeMarcheRepository.findById( typeMarche1.getId() ) ).thenReturn( Optional.of( typeMarche1 )  );

        Mockito.when(typeMarcheRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(typeMarcheRepository.findAll()).thenReturn( typeMarcheList );

        Mockito.when(typeMarcheRepository.save(any(TypeMarche.class))).thenReturn(typeMarche1);
    }

    @Test
    public void whenValidId_thenTypeMarcheShouldBeFound() {
        TypeMarche fromDb = typeMarcheService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "TypeMarche1" );
        verifyTypeMarcheFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenTypeMarcheShouldNotBeFound() {
        TypeMarche fromDb = typeMarcheService.findById( -99L );
        verifyTypeMarcheFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyTypeMarcheFindByIdCalledOnce() {
        Mockito.verify(typeMarcheRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( typeMarcheRepository );
    }

    @Test
    public void given3TypeMarche_whenGetAll_thenReturn3Records() {

        TypeMarche typeMarche1 = new TypeMarche();
        typeMarche1.setName("TypeMarche1");

        TypeMarche typeMarche2 = new TypeMarche();
        typeMarche2.setName("TypeMarche2");

        TypeMarche typeMarche3 = new TypeMarche();
        typeMarche3.setName("TypeMarche3");

        List<TypeMarche> typeMarcheList = typeMarcheService.findAll();
        verifyFindAllTypeMarcheIsCalledOnce();

        assertThat(typeMarcheList).hasSize(3).extracting(TypeMarche::getName).containsOnly(
                typeMarche1.getName(), typeMarche2.getName(), typeMarche3.getName());
    }

    private void verifyFindAllTypeMarcheIsCalledOnce() {
        Mockito.verify(typeMarcheRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( typeMarcheRepository );
    }

    @Test
    public void whenSaveTypeMarche_thenReturnTypeMarche() {

        TypeMarche typeMarche = typeMarcheService.save(  new TypeMarche() );
        TypeMarche fromDb = typeMarcheService.findById( typeMarche.getId() );
        verifySaveTypeMarcheCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( typeMarche.getId() );
    }

    private void verifySaveTypeMarcheCalledOnce(){

        Mockito.verify(typeMarcheRepository, VerificationModeFactory.times(1) ).save( any(TypeMarche.class) );
        Mockito.reset( typeMarcheRepository );
    }

}
