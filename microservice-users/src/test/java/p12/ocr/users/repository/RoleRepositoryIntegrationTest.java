package p12.ocr.users.repository;

import p12.ocr.users.model.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Before
    public void setUp(){
        userEntrepriseRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @After
    public void erase(){
        roleRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnRole() {

        Role role =  new Role( "Rôle" );

        entityManager.persistAndFlush(role);

        Role fromDb = roleRepository.findById( role.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( role.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Role fromDb = roleRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void givenSetOfRole_whenFindAll_thenReturnAllRole() {

        Role role1 =  new Role( "Rôle1" );
        Role role2 =  new Role( "Rôle2" );
        Role role3 =  new Role( "Rôle3" );

        entityManager.persist(role1);
        entityManager.persist(role2);
        entityManager.persist(role3);
        entityManager.flush();

        List<Role> allRole = roleRepository.findAll();

        assertThat(allRole).hasSize(3).extracting(Role::getId).containsOnly(
                role1.getId(), role2.getId(), role3.getId());
    }
}
