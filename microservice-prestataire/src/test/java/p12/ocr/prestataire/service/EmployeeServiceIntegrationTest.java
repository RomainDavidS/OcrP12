package p12.ocr.prestataire.service;

import p12.ocr.prestataire.model.Employee;
import p12.ocr.prestataire.model.SitePrestataire;
import p12.ocr.prestataire.repository.IEmployeeRepository;
import p12.ocr.prestataire.repository.ISitePrestataireRepository;
import p12.ocr.prestataire.service.employee.EmployeeServiceImpl;
import p12.ocr.prestataire.service.employee.IEmployeeService;
import p12.ocr.prestataire.service.siteprestataire.ISitePrestataireService;
import p12.ocr.prestataire.service.siteprestataire.SitePrestataireServiceImpl;
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
public class EmployeeServiceIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceIntegrationTestContextConfiguration {
        @Bean
        public EmployeeServiceImpl employeeService() {return new EmployeeServiceImpl();}

        @Bean
        public SitePrestataireServiceImpl sitePrestataireService() {return new SitePrestataireServiceImpl();}

    }

    @Autowired
    private IEmployeeService employeeService;

    @MockBean
    private IEmployeeRepository employeeRepository;

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @MockBean
    private ISitePrestataireRepository sitePrestataireRepository;


    @Before
    public void setUp() {

        Employee employee1 = new Employee();
        employee1.setId( 1L );
        employee1.setFirstName("Employee1");

        Employee employee2 = new Employee();
        employee2.setFirstName("Employee2");

        Employee employee3 = new Employee();
        employee3.setFirstName("Employee3");

        SitePrestataire sitePrestataire = new SitePrestataire();
        sitePrestataire.setId( 1L );

        List<Employee> employeeList = Arrays.asList( employee1,employee2,employee3 );

        Mockito.when(employeeRepository.findById( employee1.getId() ) ).thenReturn( Optional.of( employee1 )  );

        Mockito.when(employeeRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(employeeRepository.findAll()).thenReturn( employeeList );

        Mockito.when(sitePrestataireRepository.findById( 1L) ).thenReturn(Optional.of( sitePrestataire )  );

        Mockito.when(employeeRepository.findAllBySitePrestataireAndEndDateContractIsNull( sitePrestataire )).thenReturn( employeeList );

        Mockito.when(employeeRepository.findAllBySitePrestataireAndEndDateContractIsNotNull( sitePrestataire ) ).thenReturn( employeeList );

        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        Employee fromDb = employeeService.findById( 1l );
        assertThat(fromDb.getFirstName()).isEqualTo( "Employee1" );
        verifyEmployeeFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenEmployeeShouldNotBeFound() {
        Employee fromDb = employeeService.findById( -99L );
        verifyEmployeeFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyEmployeeFindByIdCalledOnce() {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( employeeRepository );
    }

    @Test
    public void given3Employee_whenGetAll_thenReturn3Records() {

        Employee employee1 = new Employee();
        employee1.setFirstName("Employee1");

        Employee employee2 = new Employee();
        employee2.setFirstName("Employee2");

        Employee employee3 = new Employee();
        employee3.setFirstName("Employee3");

        List<Employee> employeeList = employeeService.findAll();
        verifyFindAllEmployeeIsCalledOnce();

        assertThat(employeeList).hasSize(3).extracting(Employee::getFirstName).containsOnly(
                employee1.getFirstName(), employee2.getFirstName(), employee3.getFirstName() );
    }

    private void verifyFindAllEmployeeIsCalledOnce() {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( employeeRepository );
    }
    @Test
    public void given3EmployeeActif_whenGetAll_thenReturn3Records() {

        Employee employee1 = new Employee();
        employee1.setFirstName("Employee1");

        Employee employee2 = new Employee();
        employee2.setFirstName("Employee2");

        Employee employee3 = new Employee();
        employee3.setFirstName("Employee3");

        SitePrestataire sitePrestataire = new SitePrestataire();
        sitePrestataire.setId( 1L );

        List<Employee> employeeList = employeeService.findAllActifBySite( sitePrestataire.getId() );
        verifyFindAllEmployeeActifIsCalledOnce();

        assertThat(employeeList).hasSize(3).extracting(Employee::getFirstName).containsOnly(
                employee1.getFirstName(), employee2.getFirstName(), employee3.getFirstName() );
    }

    private void verifyFindAllEmployeeActifIsCalledOnce() {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findAllBySitePrestataireAndEndDateContractIsNull(any(SitePrestataire.class));
        Mockito.reset( employeeRepository );
    }
    @Test
    public void given3EmployeeInactif_whenGetAll_thenReturn3Records() {

        Employee employee1 = new Employee();
        employee1.setFirstName("Employee1");

        Employee employee2 = new Employee();
        employee2.setFirstName("Employee2");

        Employee employee3 = new Employee();
        employee3.setFirstName("Employee3");

        SitePrestataire sitePrestataire = new SitePrestataire();
        sitePrestataire.setId( 1L );

        List<Employee> employeeList = employeeService.findAllInactifBySite( sitePrestataire.getId() );
        verifyFindAllEmployeeInactifIsCalledOnce();

        assertThat(employeeList).hasSize(3).extracting(Employee::getFirstName).containsOnly(
                employee1.getFirstName(), employee2.getFirstName(), employee3.getFirstName() );
    }

    private void verifyFindAllEmployeeInactifIsCalledOnce() {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findAllBySitePrestataireAndEndDateContractIsNotNull(any(SitePrestataire.class));
        Mockito.reset( employeeRepository );
    }

    @Test
    public void whenSaveEmployee_thenReturnEmployee() {

        Employee employee = employeeService.save(  new Employee() );
        Employee fromDb = employeeService.findById( employee.getId() );
        verifySaveEmployeeCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( employee.getId() );
    }

    private void verifySaveEmployeeCalledOnce(){

        Mockito.verify(employeeRepository, VerificationModeFactory.times(1) ).save( any(Employee.class) );
        Mockito.reset( employeeRepository );
    }

}
