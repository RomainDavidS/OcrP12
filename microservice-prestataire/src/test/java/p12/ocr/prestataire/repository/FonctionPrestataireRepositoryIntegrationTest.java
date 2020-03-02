package p12.ocr.prestataire.repository;
import p12.ocr.prestataire.model.FonctionPrestataire;
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
public class FonctionPrestataireRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IFonctionPrestataireRepository fonctionPrestataireRepository;

    @Before
    public void setUp(){ fonctionPrestataireRepository.deleteAll(); }

    @After
    public void erase(){
        fonctionPrestataireRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnFonctionPrestataire() {

        FonctionPrestataire fonctionPrestataire = createTestFonctionPrestataire("FonctionPrestataire");

        entityManager.persistAndFlush(fonctionPrestataire);

        FonctionPrestataire fromDb = fonctionPrestataireRepository.findById( fonctionPrestataire.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( fonctionPrestataire.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        FonctionPrestataire fromDb = fonctionPrestataireRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveFonctionPrestataire_thenReturnFonctionPrestataire() {
        FonctionPrestataire fonctionPrestataire = createTestFonctionPrestataire("FonctionPrestataire");
        fonctionPrestataireRepository.save( fonctionPrestataire );
        FonctionPrestataire fromDb = fonctionPrestataireRepository.findById( fonctionPrestataire.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( fonctionPrestataire.getId() );
    }


    @Test
    public void givenSetOfTypeMarche_whenFindAll_thenReturnAllTypeMarche() {

        FonctionPrestataire fonctionPrestataire1 = createTestFonctionPrestataire("FonctionPrestataire1");
        FonctionPrestataire fonctionPrestataire2 = createTestFonctionPrestataire("FonctionPrestataire2");
        FonctionPrestataire fonctionPrestataire3 = createTestFonctionPrestataire("FonctionPrestataire3");

        entityManager.persist(fonctionPrestataire1);
        entityManager.persist(fonctionPrestataire2);
        entityManager.persist(fonctionPrestataire3);
        entityManager.flush();

        List<FonctionPrestataire> allFonctionPrestataire = fonctionPrestataireRepository.findAll();

        assertThat(allFonctionPrestataire).hasSize(3).extracting(FonctionPrestataire::getId).containsOnly(
                fonctionPrestataire1.getId(), fonctionPrestataire2.getId(), fonctionPrestataire3.getId());
    }

    private FonctionPrestataire createTestFonctionPrestataire(String name){
        FonctionPrestataire fonctionPrestataire = new FonctionPrestataire();
        fonctionPrestataire.setName( name );
        return fonctionPrestataire;
    }
}
