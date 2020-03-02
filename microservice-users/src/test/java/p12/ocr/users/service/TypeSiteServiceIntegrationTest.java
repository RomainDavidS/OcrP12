package p12.ocr.users.service;

import p12.ocr.users.model.TypeSite;
import p12.ocr.users.repository.ITypeSiteRepository;
import p12.ocr.users.service.typesite.ITypeSiteService;
import p12.ocr.users.service.typesite.TypeSiteServiceImpl;
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
public class TypeSiteServiceIntegrationTest {

    @TestConfiguration
    static class TypeSiteServiceIntegrationTestContextConfiguration {
        @Bean
        public TypeSiteServiceImpl typeSiteService() {return new TypeSiteServiceImpl();}
    }

    @Autowired
    private ITypeSiteService typeSiteService;

    @MockBean
    private ITypeSiteRepository typeSiteRepository;


    @Before
    public void setUp() {

        TypeSite typeSite1 = new TypeSite("TypeSite1");
        typeSite1.setId( 1L );

        TypeSite typeSite2 = new TypeSite("TypeSite2");

        TypeSite typeSite3 = new TypeSite("TypeSite3");

        List<TypeSite> typeSiteList = Arrays.asList( typeSite1,typeSite2,typeSite3 );

        Mockito.when(typeSiteRepository.findById( typeSite1.getId() ) ).thenReturn( Optional.of( typeSite1 )  );

        Mockito.when(typeSiteRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(typeSiteRepository.findAll()).thenReturn( typeSiteList );

        Mockito.when(typeSiteRepository.save(any(TypeSite.class))).thenReturn(typeSite1);
    }

    @Test
    public void whenValidId_thenTypeSiteShouldBeFound() {
        TypeSite fromDb = typeSiteService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "TypeSite1" );
        verifyTypeSiteFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenTypeSiteShouldNotBeFound() {
        TypeSite fromDb = typeSiteService.findById( -99L );
        verifyTypeSiteFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyTypeSiteFindByIdCalledOnce() {
        Mockito.verify(typeSiteRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( typeSiteRepository );
    }

    @Test
    public void given3TypeSite_whenGetAll_thenReturn3Records() {

        TypeSite typeSite1 = new TypeSite("TypeSite1");
        TypeSite typeSite2 = new TypeSite("TypeSite2");
        TypeSite typeSite3 = new TypeSite("TypeSite3");

        List<TypeSite> typeSiteList = typeSiteService.findAll();
        verifyFindAllTypeSiteIsCalledOnce();

        assertThat(typeSiteList).hasSize(3).extracting(TypeSite::getName).containsOnly(
                typeSite1.getName(), typeSite2.getName(), typeSite3.getName());
    }

    private void verifyFindAllTypeSiteIsCalledOnce() {
        Mockito.verify(typeSiteRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( typeSiteRepository );
    }

    @Test
    public void whenSaveTypeSite_thenReturnTypeSite() {

        TypeSite typeSite = typeSiteService.save(  new TypeSite("TypeSite1") );
        TypeSite fromDb = typeSiteService.findById( typeSite.getId() );
        verifySaveTypeSiteCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( typeSite.getId() );
    }
    private void verifySaveTypeSiteCalledOnce(){

        Mockito.verify(typeSiteRepository, VerificationModeFactory.times(1) ).save( any(TypeSite.class) );
        Mockito.reset( typeSiteRepository );
    }

}
