package p12.ocr.users.repository;

import p12.ocr.users.model.TypeSite;
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
public class TypeSiteRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ITypeSiteRepository typeSiteRepository;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Autowired
    private ISiteRepository siteRepository;

    @Before
    public void setUp(){
        userEntrepriseRepository.deleteAll();
        siteRepository.deleteAll();
        typeSiteRepository.deleteAll();
    }

    @After
    public void erase(){ typeSiteRepository.deleteAll();}

    @Test
    public void whenFindById_thenReturnTypeSite() {

        TypeSite typeSite = new TypeSite("TypeSite");

        entityManager.persistAndFlush(typeSite);

        TypeSite fromDb = typeSiteRepository.findById( typeSite.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( typeSite.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        TypeSite fromDb = typeSiteRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenSaveTypeSite_thenReturnTypeSite() {
        TypeSite typeSite = new TypeSite("TypeSite");
        typeSiteRepository.save( typeSite );
        TypeSite fromDb = typeSiteRepository.findById( typeSite.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( typeSite.getId() );
    }


    @Test
    public void givenSetOfTypeSite_whenFindAll_thenReturnAllTypeSite() {

        TypeSite typeSite1 = new TypeSite("TypeSite1");
        TypeSite typeSite2 = new TypeSite("TypeSite2");
        TypeSite typeSite3 = new TypeSite("TypeSite3");

        entityManager.persist(typeSite1);
        entityManager.persist(typeSite2);
        entityManager.persist(typeSite3);
        entityManager.flush();

        List<TypeSite> allTypeSite = typeSiteRepository.findAll();

        assertThat(allTypeSite).hasSize(3).extracting(TypeSite::getId).containsOnly(
                typeSite1.getId(), typeSite2.getId(), typeSite3.getId());
    }


}
