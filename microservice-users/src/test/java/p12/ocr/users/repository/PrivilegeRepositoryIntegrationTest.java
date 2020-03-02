package p12.ocr.users.repository;

import p12.ocr.users.model.Privilege;
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
public class PrivilegeRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IPrivilegeRepository privilegeRepository;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Autowired
    private IRoleRepository roleRepository;


    @Before
    public void setUp(){
        userEntrepriseRepository.deleteAll();
        roleRepository.deleteAll();
        privilegeRepository.deleteAll();
    }

    @After
    public void erase(){
        privilegeRepository.deleteAll();
    }
    @Test
    public void whenFindById_thenReturnPrivilege() {

        Privilege privilege =  new Privilege( "Privilège" );

        entityManager.persistAndFlush(privilege);

        Privilege fromDb = privilegeRepository.findById( privilege.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( privilege.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Privilege fromDb = privilegeRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void givenSetOfPrivilege_whenFindAll_thenReturnAllPrivilege() {

        Privilege privilege1 =  new Privilege( "Privilège1" );
        Privilege privilege2 =  new Privilege( "Privilège2" );
        Privilege privilege3 =  new Privilege( "Privilège3" );

        entityManager.persist(privilege1);
        entityManager.persist(privilege2);
        entityManager.persist(privilege3);
        entityManager.flush();

        List<Privilege> allPrivilege = privilegeRepository.findAll();

        assertThat(allPrivilege).hasSize(3).extracting(Privilege::getId).containsOnly(
                privilege1.getId(), privilege2.getId(), privilege3.getId());
    }
}
