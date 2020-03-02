package p12.ocr.prestataire.repository;
import p12.ocr.prestataire.model.Entreprise;
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
public class EntrepriseRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IEntrepriseRepository entrepriseRepository;

    @Autowired
    private ISitePrestataireRepository sitePrestataireRepository;

    @Before
    public void setUp(){
        sitePrestataireRepository.deleteAll();
        entrepriseRepository.deleteAll(); }

    @After
    public void erase(){
        entrepriseRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnEntreprise() {

        Entreprise entreprise = createTestEntreprise("Entreprise");

        entityManager.persistAndFlush(entreprise);

        Entreprise fromDb = entrepriseRepository.findById( entreprise.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( entreprise.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Entreprise fromDb = entrepriseRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindBySiret_thenReturnEntreprise() {

        Entreprise entreprise = createTestEntreprise("Entreprise");
        entreprise.setSiret("111");

        entityManager.persistAndFlush(entreprise);

        Entreprise fromDb = entrepriseRepository.findBySiret( entreprise.getSiret() );
        assertThat(fromDb.getSiret()).isEqualTo( entreprise.getSiret() );
    }

    @Test
    public void whenInvalidSiret_thenReturnNull() {
        Entreprise fromDb = entrepriseRepository.findBySiret( "InvalideSiret");
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveEntreprise_thenReturnEntreprise() {
        Entreprise entreprise = createTestEntreprise("Entreprise");
        entrepriseRepository.save( entreprise );
        Entreprise fromDb = entrepriseRepository.findById( entreprise.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( entreprise.getId() );
    }


    @Test
    public void givenSetOfEntreprise_whenFindAll_thenReturnAllEntreprise() {

        Entreprise entreprise1 = createTestEntreprise("Entreprise1");
        Entreprise entreprise2 = createTestEntreprise("Entreprise2");
        Entreprise entreprise3 = createTestEntreprise("Entreprise3");

        entityManager.persist(entreprise1);
        entityManager.persist(entreprise2);
        entityManager.persist(entreprise3);
        entityManager.flush();

        List<Entreprise> allEntreprise = entrepriseRepository.findAll();

        assertThat(allEntreprise).hasSize(3).extracting(Entreprise::getId).containsOnly(
                entreprise1.getId(), entreprise2.getId(), entreprise3.getId());
    }

    private Entreprise createTestEntreprise(String name){
        Entreprise entreprise = new Entreprise();
        entreprise.setName( name );
        return entreprise;
    }
}
