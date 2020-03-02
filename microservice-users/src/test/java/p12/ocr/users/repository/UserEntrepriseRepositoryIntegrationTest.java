package p12.ocr.users.repository;


import p12.ocr.users.model.UserEntreprise;
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
public class UserEntrepriseRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Before
    public void setUp(){ userEntrepriseRepository.deleteAll(); }

    @After
    public void erase(){
        userEntrepriseRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnUserEntreprise() {

        UserEntreprise userEntreprise = createTestUserEntreprise("Nni");

        entityManager.persistAndFlush(userEntreprise);

        UserEntreprise fromDb = userEntrepriseRepository.findById( userEntreprise.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( userEntreprise.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        UserEntreprise fromDb = userEntrepriseRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindByNni_thenReturnUserEntreprise() {

        UserEntreprise userEntreprise = createTestUserEntreprise("Nni");

        entityManager.persistAndFlush(userEntreprise);

        UserEntreprise fromDb = userEntrepriseRepository.findByNni( userEntreprise.getNni() );
        assertThat(fromDb.getNni()).isEqualTo( userEntreprise.getNni() );
    }

    @Test
    public void whenInvalidNni_thenReturnNull() {
        UserEntreprise fromDb = userEntrepriseRepository.findByNni( "InvalidNni");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenSaveUserEntreprise_thenReturnUserEntreprise() {
        UserEntreprise userEntreprise = createTestUserEntreprise("Nni");
        userEntrepriseRepository.save(userEntreprise);
        UserEntreprise fromDb = userEntrepriseRepository.findById( userEntreprise.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( userEntreprise.getId() );
    }


    @Test
    public void givenSetOfUserEntreprise_whenFindAll_thenReturnAllUserEntreprise() {

        UserEntreprise userEntreprise1 = createTestUserEntreprise("Nni1");
        UserEntreprise userEntreprise2 = createTestUserEntreprise("Nni2");
        UserEntreprise userEntreprise3 = createTestUserEntreprise("Nni3");

        entityManager.persist(userEntreprise1);
        entityManager.persist(userEntreprise2);
        entityManager.persist(userEntreprise3);
        entityManager.flush();

        List<UserEntreprise> allUserEntreprise = userEntrepriseRepository.findAll();

        assertThat(allUserEntreprise).hasSize(3).extracting(UserEntreprise::getId).containsOnly(
                userEntreprise1.getId(), userEntreprise2.getId(), userEntreprise3.getId());
    }

    private UserEntreprise createTestUserEntreprise(String nni){
        UserEntreprise userEntreprise = new UserEntreprise();
        userEntreprise.setNni( nni );
        return userEntreprise;
    }

}
