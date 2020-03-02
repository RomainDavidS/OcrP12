package p12.ocr.users.repository;

import p12.ocr.users.model.Fonction;
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
public class FonctionRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IFonctionRepository fonctionRepository;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Before
    public void setUp(){
        userEntrepriseRepository.deleteAll();
        fonctionRepository.deleteAll();
    }

    @After
    public void erase(){

        fonctionRepository.deleteAll();
    }
    @Test
    public void whenFindById_thenReturnFonction() {

        Fonction fonction =  new Fonction( "Fonction" );

        entityManager.persistAndFlush(fonction);

        Fonction fromDb = fonctionRepository.findById( fonction.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( fonction.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Fonction fromDb = fonctionRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenSaveFonction_thenReturnFonction() {
        Fonction fonction =  new Fonction( "Fonction" );
        fonctionRepository.save( fonction );
        Fonction fromDb = fonctionRepository.findById( fonction.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( fonction.getId() );
    }


    @Test
    public void givenSetOfFontion_whenFindAll_thenReturnAllFonction() {

        Fonction fonction1 =  new Fonction( "Fonction1" );
        Fonction fonction2 =  new Fonction( "Fonction2" );
        Fonction fonction3 =  new Fonction( "Fonction3" );

        entityManager.persist(fonction1);
        entityManager.persist(fonction2);
        entityManager.persist(fonction3);
        entityManager.flush();

        List<Fonction> allFonction = fonctionRepository.findAll();

        assertThat(allFonction).hasSize(3).extracting(Fonction::getId).containsOnly(
                fonction1.getId(), fonction2.getId(), fonction3.getId());
    }


}
