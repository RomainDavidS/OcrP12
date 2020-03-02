package p12.ocr.marche.repository;

import p12.ocr.marche.model.Marche;
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
public class MarcheRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IMarcheRepository marcheRepository;

    @Autowired
    private IIndemnisationRepository indemnisationRepository;

    @Autowired
    private ITypeMarcheRepository typeMarcheRepository;

    @Before
    public void setUp(){

        indemnisationRepository.deleteAll();
        marcheRepository.deleteAll();

    }

    @After
    public void erase(){
        marcheRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnMarche() {

        Marche marche = createTestMarche("Marche");

        entityManager.persistAndFlush(marche);

        Marche fromDb = marcheRepository.findById( marche.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( marche.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Marche fromDb = marcheRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveMarche_thenReturnMarche() {
        Marche marche = createTestMarche("Marche");
        marcheRepository.save( marche );
        Marche fromDb = marcheRepository.findById( marche.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( marche.getId() );
    }


    @Test
    public void givenSetOfMarche_whenFindAll_thenReturnAllMarche() {

        Marche marche1 = createTestMarche("Marche1");
        Marche marche2 = createTestMarche("Marche2");
        Marche marche3 = createTestMarche("Marche3");

        entityManager.persist(marche1);
        entityManager.persist(marche2);
        entityManager.persist(marche3);
        entityManager.flush();

        List<Marche> allMarche = marcheRepository.findAll();

        assertThat(allMarche).hasSize(3).extracting(Marche::getId).containsOnly(
                marche1.getId(), marche2.getId(), marche3.getId());
    }

    private Marche createTestMarche(String name){
        Marche marche = new Marche();
        marche.setName( name );
        return marche;
    }
}
