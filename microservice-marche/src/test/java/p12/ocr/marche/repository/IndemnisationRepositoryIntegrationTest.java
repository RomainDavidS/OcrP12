package p12.ocr.marche.repository;

import p12.ocr.marche.model.Indemnisation;
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
public class IndemnisationRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IIndemnisationRepository indemnisationRepository;

    @Autowired
    private ITypeMarcheRepository typeMarcheRepository;

    @Autowired
    private IMarcheRepository marcheRepository;

    @Before
    public void setUp(){
        indemnisationRepository.deleteAll();
    }

    @After
    public void erase(){
        indemnisationRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnIndemnisation() {

        Indemnisation indemnisation = createTestIndemnisation("1111");

        entityManager.persistAndFlush(indemnisation);

        Indemnisation fromDb = indemnisationRepository.findById( indemnisation.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( indemnisation.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Indemnisation fromDb = indemnisationRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveIdemnisation_thenReturnIndemnisation() {
        Indemnisation indemnisation = createTestIndemnisation("1111");
        indemnisationRepository.save( indemnisation );
        Indemnisation fromDb = indemnisationRepository.findById( indemnisation.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( indemnisation.getId() );
    }


    @Test
    public void givenSetOfIndemnisation_whenFindAll_thenReturnAllIndemnisation() {

        Indemnisation indemnisation1 = createTestIndemnisation("1111");
        Indemnisation indemnisation2 = createTestIndemnisation("2222");
        Indemnisation indemnisation3 = createTestIndemnisation("3333");

        entityManager.persist(indemnisation1);
        entityManager.persist(indemnisation2);
        entityManager.persist(indemnisation3);
        entityManager.flush();

        List<Indemnisation> allIndemnisation = indemnisationRepository.findAll();

        assertThat(allIndemnisation).hasSize(3).extracting(Indemnisation::getId).containsOnly(
                indemnisation1.getId(), indemnisation2.getId(), indemnisation3.getId());
    }

    private Indemnisation createTestIndemnisation(String pdl){
        Indemnisation indemnisation = new Indemnisation();
        indemnisation.setPdl( pdl );
        return indemnisation;
    }
}
