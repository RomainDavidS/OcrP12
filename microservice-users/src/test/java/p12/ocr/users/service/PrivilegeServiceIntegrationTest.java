package p12.ocr.users.service;


import p12.ocr.users.model.Privilege;
import p12.ocr.users.repository.IPrivilegeRepository;
import p12.ocr.users.service.privilege.IPrivilegeService;
import p12.ocr.users.service.privilege.PrivilegeServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class PrivilegeServiceIntegrationTest {

    @TestConfiguration
    static class PrivilegeServiceIntegrationTestContextConfiguration {
        @Bean
        public PrivilegeServiceImpl privilegeService() {return new PrivilegeServiceImpl();}
    }

    @Autowired
    private IPrivilegeService privilegeService;

    @MockBean
    private IPrivilegeRepository privilegeRepository;


    @Before
    public void setUp() {

        Privilege privilege1 = new Privilege("Privilege1");
        privilege1.setId( 1L );

        Privilege privilege2 = new Privilege("Privilege2");

        Privilege privilege3 = new Privilege("Privilege3");

        List<Privilege> privilegeList = Arrays.asList( privilege1,privilege2,privilege3 );

        Mockito.when(privilegeRepository.findByName( privilege1.getName() ) ).thenReturn(  privilege1   );

        Mockito.when(privilegeRepository.findByName("Invalid") ).thenReturn( null );

        Mockito.when(privilegeRepository.findAll()).thenReturn( privilegeList );
    }

    @Test
    public void whenValidId_thenPrivilegeShouldBeFound() {
        Privilege fromDb = privilegeService.findByName( "Privilege1" );
        assertThat(fromDb.getName()).isEqualTo( "Privilege1" );
        verifyPrivilegeFindByNameCalledOnce();
    }

    @Test
    public void whenInvalidId_thenPrivilegeShouldNotBeFound() {
        Privilege fromDb = privilegeService.findByName( "Invalid" );
        verifyPrivilegeFindByNameCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyPrivilegeFindByNameCalledOnce() {
        Mockito.verify(privilegeRepository, VerificationModeFactory.times(1)).findByName( Mockito.anyString() );
        Mockito.reset( privilegeRepository );
    }

    @Test
    public void given3Privilege_whenGetAll_thenReturn3Records() {

        Privilege privilege1 = new Privilege("Privilege1");
        Privilege privilege2 = new Privilege("Privilege2");
        Privilege privilege3 = new Privilege("Privilege3");

        List<Privilege> privilegeList = privilegeService.findAll();
        verifyFindAllPrivilegeIsCalledOnce();

        assertThat(privilegeList).hasSize(3).extracting(Privilege::getName).containsOnly(
                privilege1.getName(), privilege2.getName(), privilege3.getName());
    }

    private void verifyFindAllPrivilegeIsCalledOnce() {
        Mockito.verify(privilegeRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( privilegeRepository );
    }


}
