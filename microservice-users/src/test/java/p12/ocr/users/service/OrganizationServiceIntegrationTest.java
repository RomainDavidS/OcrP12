package p12.ocr.users.service;

import p12.ocr.users.model.Organization;
import p12.ocr.users.repository.IOrganizationRepository;
import p12.ocr.users.service.organization.IOrganizationService;
import p12.ocr.users.service.organization.OrganizationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class OrganizationServiceIntegrationTest {

    @TestConfiguration
    static class OrganizationServiceIntegrationTestContextConfiguration {
        @Bean
        public OrganizationServiceImpl organizationService() {return new OrganizationServiceImpl();}
    }

    @Autowired
    private IOrganizationService organizationService;

    @MockBean
    private IOrganizationRepository organizationRepository;


    @Before
    public void setUp() {

        Organization organization1 = new Organization("Organization1");
        organization1.setId( 1L );

        Organization organization2 = new Organization("Organization2");

        Organization organization3 = new Organization("Organization3");

        List<Organization> organizationList = Arrays.asList( organization1,organization2,organization3 );

        Mockito.when(organizationRepository.findById( organization1.getId() ) ).thenReturn( Optional.of( organization1 )  );

        Mockito.when(organizationRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(organizationRepository.findAll()).thenReturn( organizationList );

        Mockito.when(organizationRepository.save(any(Organization.class))).thenReturn(organization1);
    }

    @Test
    public void whenValidId_thenOrganizationShouldBeFound() {
        Organization fromDb = organizationService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "Organization1" );
        verifyOrganizationFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenOrganizationShouldNotBeFound() {
        Organization fromDb = organizationService.findById( -99L );
        verifyOrganizationFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyOrganizationFindByIdCalledOnce() {
        Mockito.verify(organizationRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( organizationRepository );
    }

    @Test
    public void given3Organization_whenGetAll_thenReturn3Records() {

        Organization organization1 = new Organization("Organization1");
        Organization organization2 = new Organization("Organization2");
        Organization organization3 = new Organization("Organization3");

        List<Organization> organizationList = organizationService.findAll();
        verifyFindAllOrganizationIsCalledOnce();

        assertThat(organizationList).hasSize(3).extracting(Organization::getName).containsOnly(
                organization1.getName(), organization2.getName(), organization3.getName());
    }

    private void verifyFindAllOrganizationIsCalledOnce() {
        Mockito.verify(organizationRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( organizationRepository );
    }

    @Test
    public void whenSaveOrganization_thenReturnOrganization() {

        Organization organization = organizationService.save(  new Organization("Organization1") );
        Organization fromDb = organizationService.findById( organization.getId() );
        verifySaveOrganizationCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( organization.getId() );
    }
    private void verifySaveOrganizationCalledOnce(){

        Mockito.verify(organizationRepository, VerificationModeFactory.times(1) ).save( any(Organization.class) );
        Mockito.reset( organizationRepository );
    }

}
