package p12.ocr.users.repository;

import p12.ocr.users.model.Organization;
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
public class OrganizationRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Before
    public void setUp(){
        userEntrepriseRepository.deleteAll();
        organizationRepository.deleteAll();
    }

    @After
    public void erase(){
        organizationRepository.deleteAll();
    }
    @Test
    public void whenFindById_thenReturnOrganization() {

        Organization organization =  new Organization( "Organization" );

        entityManager.persistAndFlush(organization);

        Organization fromDb = organizationRepository.findById( organization.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( organization.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Organization fromDb = organizationRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenSaveOrganization_thenReturnOrganization() {
        Organization organization =  new Organization( "Organization" );
        organizationRepository.save( organization );
        Organization fromDb = organizationRepository.findById( organization.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( organization.getId() );
    }


    @Test
    public void givenSetOfOrganization_whenFindAll_thenReturnAllOrganization() {

        Organization organization1 =  new Organization( "Organization1" );
        Organization organization2 =  new Organization( "Organization2" );
        Organization organization3 =  new Organization( "Organization3" );

        entityManager.persist(organization1);
        entityManager.persist(organization2);
        entityManager.persist(organization3);
        entityManager.flush();

        List<Organization> allOrganization = organizationRepository.findAll();

        assertThat(allOrganization).hasSize(3).extracting(Organization::getId).containsOnly(
                organization1.getId(), organization2.getId(), organization3.getId());
    }
}
