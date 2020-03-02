package p12.ocr.users.service;

import p12.ocr.users.model.UserEntreprise;
import p12.ocr.users.repository.IUserEntrepriseRepository;
import p12.ocr.users.service.userentreprise.IUserEntrepriseService;
import p12.ocr.users.service.userentreprise.UserEntrepriseServiceImpl;
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
public class UserEntrepriseServiceIntegrationTest {

    @TestConfiguration
    static class UserEntrepriseServiceIntegrationTestContextConfiguration {
        @Bean
        public UserEntrepriseServiceImpl userEntrepriseService() {return new UserEntrepriseServiceImpl();}
    }

    @Autowired
    private IUserEntrepriseService userEntrepriseService;

    @MockBean
    private IUserEntrepriseRepository userEntrepriseRepository;


    @Before
    public void setUp() {

        UserEntreprise userEntreprise1 = new UserEntreprise();
        userEntreprise1.setId( 1L );
        userEntreprise1.setNni("111");

        UserEntreprise userEntreprise2 = new UserEntreprise();
        userEntreprise2.setNni("222");

        UserEntreprise userEntreprise3 = new UserEntreprise();
        userEntreprise3.setNni("333");

        List<UserEntreprise> userEntrepriseList = Arrays.asList(userEntreprise1, userEntreprise2, userEntreprise3);

        Mockito.when(userEntrepriseRepository.findById( userEntreprise1.getId() ) ).thenReturn( Optional.of(userEntreprise1)  );

        Mockito.when(userEntrepriseRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(userEntrepriseRepository.findByNni( userEntreprise1.getNni() ) ).thenReturn(userEntreprise1);

        Mockito.when(userEntrepriseRepository.findByNni( "Invalide" ) ).thenReturn(  null   );

        Mockito.when(userEntrepriseRepository.findAll()).thenReturn(userEntrepriseList);

        Mockito.when(userEntrepriseRepository.save(any(UserEntreprise.class))).thenReturn(userEntreprise1);
    }

    @Test
    public void whenValidId_thenUserEntrepriseShouldBeFound() {
        UserEntreprise fromDb = userEntrepriseService.findById( 1l );
        assertThat(fromDb.getNni()).isEqualTo( "111" );
        verifyUserEntrepriseFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenUserEntrepriseShouldNotBeFound() {
        UserEntreprise fromDb = userEntrepriseService.findById( -99L );
        verifyUserEntrepriseFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyUserEntrepriseFindByIdCalledOnce() {
        Mockito.verify(userEntrepriseRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( userEntrepriseRepository );
    }

    @Test
    public void whenValidNni_thenUserEntrepriseShouldBeFound() {
        UserEntreprise fromDb = userEntrepriseService.findByNni( "111" );
        assertThat(fromDb.getNni()).isEqualTo( "111" );
        verifyUserEntrepriseFindByNniCalledOnce();
    }


    @Test
    public void whenInvalidNni_thenUserEntrepriseShouldNotBeFound() {
        UserEntreprise fromDb = userEntrepriseService.findByNni( "Invalide" );
        verifyUserEntrepriseFindByNniCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyUserEntrepriseFindByNniCalledOnce() {
        Mockito.verify(userEntrepriseRepository, VerificationModeFactory.times(1)).findByNni( Mockito.anyString() );
        Mockito.reset( userEntrepriseRepository );
    }

    @Test
    public void given3UserEntreprise_whenGetAll_thenReturn3Records() {

        UserEntreprise userEntreprise1 = new UserEntreprise();
        userEntreprise1.setNni("111");

        UserEntreprise userEntreprise2 = new UserEntreprise();
        userEntreprise2.setNni("222");

        UserEntreprise userEntreprise3 = new UserEntreprise();
        userEntreprise3.setNni("333");

        List<UserEntreprise> userEntrepriseList = userEntrepriseService.findAll();
        verifyFindAllUserEntrepriseIsCalledOnce();

        assertThat(userEntrepriseList).hasSize(3).extracting(UserEntreprise::getNni).containsOnly(
                userEntreprise1.getNni(), userEntreprise2.getNni(), userEntreprise3.getNni());
    }

    private void verifyFindAllUserEntrepriseIsCalledOnce() {
        Mockito.verify(userEntrepriseRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( userEntrepriseRepository );
    }

    @Test
    public void whenSaveUserEntreprise_thenReturnUserEntreprise() {

        UserEntreprise userEntreprise = userEntrepriseService.save(  new UserEntreprise() );

        UserEntreprise fromDb = userEntrepriseService.findById( userEntreprise.getId() );
        verifySaveUserEntrepriseCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( userEntreprise.getId() );
    }
    private void verifySaveUserEntrepriseCalledOnce(){

        Mockito.verify(userEntrepriseRepository, VerificationModeFactory.times(1) ).save( any(UserEntreprise.class) );
        Mockito.reset( userEntrepriseRepository );
    }

}
