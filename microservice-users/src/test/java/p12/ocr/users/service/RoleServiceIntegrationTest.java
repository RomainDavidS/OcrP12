package p12.ocr.users.service;

import p12.ocr.users.model.Role;
import p12.ocr.users.repository.IRoleRepository;
import p12.ocr.users.service.role.IRoleService;
import p12.ocr.users.service.role.RoleServiceImpl;
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
public class RoleServiceIntegrationTest {

    @TestConfiguration
    static class RoleServiceIntegrationTestContextConfiguration {
        @Bean
        public RoleServiceImpl roleService() {return new RoleServiceImpl();}
    }

    @Autowired
    private IRoleService roleService;

    @MockBean
    private IRoleRepository roleRepository;


    @Before
    public void setUp() {

        Role role1 = new Role("Role1");
        role1.setId( 1L );

        Role role2 = new Role("Role2");

        Role role3 = new Role("Role3");

        List<Role> roleList = Arrays.asList( role1,role2,role3 );

        Mockito.when(roleRepository.findById( role1.getId() ) ).thenReturn( Optional.of( role1 )  );

        Mockito.when(roleRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(roleRepository.findAll()).thenReturn( roleList );

        Mockito.when(roleRepository.save(any(Role.class))).thenReturn(role1);
    }

    @Test
    public void whenValidId_thenRoleShouldBeFound() {
        Role fromDb = roleService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "Role1" );
        verifyRoleFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenRoleShouldNotBeFound() {
        Role fromDb = roleService.findById( -99L );
        verifyRoleFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyRoleFindByIdCalledOnce() {
        Mockito.verify(roleRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( roleRepository );
    }

    @Test
    public void given3Role_whenGetAll_thenReturn3Records() {

        Role role1 = new Role("Role1");
        Role role2 = new Role("Role2");
        Role role3 = new Role("Role3");

        List<Role> roleList = roleService.findAll();
        verifyFindAllRoleIsCalledOnce();

        assertThat(roleList).hasSize(3).extracting(Role::getName).containsOnly(
                role1.getName(), role2.getName(), role3.getName());
    }

    private void verifyFindAllRoleIsCalledOnce() {
        Mockito.verify(roleRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( roleRepository );
    }


}
