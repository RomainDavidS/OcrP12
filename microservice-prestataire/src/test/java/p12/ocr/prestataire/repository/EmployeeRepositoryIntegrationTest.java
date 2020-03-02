package p12.ocr.prestataire.repository;
import p12.ocr.prestataire.model.Employee;
import p12.ocr.prestataire.model.SitePrestataire;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Before
    public void setUp(){ employeeRepository.deleteAll(); }

    @After
    public void erase(){
        employeeRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnEmployee() {
        SitePrestataire sitePrestataire = new SitePrestataire();
        entityManager.persistAndFlush(sitePrestataire);

        Employee employee = createTestEmployee("Employee",null,sitePrestataire);

        entityManager.persistAndFlush(employee);

        Employee fromDb = employeeRepository.findById( employee.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( employee.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Employee fromDb = employeeRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveEmployee_thenReturnEmployee() {
        SitePrestataire sitePrestataire = new SitePrestataire();
        entityManager.persistAndFlush(sitePrestataire);

        Employee employee = createTestEmployee("Employee",null,sitePrestataire);
        employeeRepository.save( employee );
        Employee fromDb = employeeRepository.findById( employee.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( employee.getId() );
    }


    @Test
    public void givenSetOfEmployee_whenFindAll_thenReturnAllEmployee() {

        SitePrestataire sitePrestataire = new SitePrestataire();
        entityManager.persistAndFlush(sitePrestataire);

        Employee employee1 = createTestEmployee("Employee1",null,sitePrestataire);
        Employee employee2 = createTestEmployee("Employee2",null,sitePrestataire);
        Employee employee3 = createTestEmployee("Employee3",null,sitePrestataire);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.flush();

        List<Employee> allEmployee = employeeRepository.findAll();

        assertThat(allEmployee).hasSize(3).extracting(Employee::getId).containsOnly(
                employee1.getId(), employee2.getId(), employee3.getId());
    }

    @Test
    public void givenSetOfEmployeeActif_whenFindAll_thenReturnAllEmployeeActif() {

        SitePrestataire sitePrestataire = new SitePrestataire();
        entityManager.persistAndFlush(sitePrestataire);

        Employee employee1 = createTestEmployee("Employee1",null,sitePrestataire);
        Employee employee2 = createTestEmployee("Employee2",null,sitePrestataire);
        Employee employee3 = createTestEmployee("Employee3",null,sitePrestataire);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.flush();

        List<Employee> allEmployee = employeeRepository.findAllBySitePrestataireAndEndDateContractIsNull( sitePrestataire );

        assertThat(allEmployee).hasSize(3).extracting(Employee::getId).containsOnly(
                employee1.getId(), employee2.getId(), employee3.getId());
    }
    @Test
    public void givenSetOfEmployeeInactif_whenFindAll_thenReturnAllEmployeeInactif() {

        SitePrestataire sitePrestataire = new SitePrestataire();
        entityManager.persistAndFlush(sitePrestataire);

        Employee employee1 = createTestEmployee("Employee1",new Date(),sitePrestataire);
        Employee employee2 = createTestEmployee("Employee2",new Date(),sitePrestataire);
        Employee employee3 = createTestEmployee("Employee3",new Date(),sitePrestataire);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.flush();

        List<Employee> allEmployee = employeeRepository.findAllBySitePrestataireAndEndDateContractIsNotNull( sitePrestataire);

        assertThat(allEmployee).hasSize(3).extracting(Employee::getId).containsOnly(
                employee1.getId(), employee2.getId(), employee3.getId());
    }


    private Employee createTestEmployee(String name, Date date, SitePrestataire sitePrestataire){
        Employee employee = new Employee();
        employee.setFirstName( name );
        employee.setEndDateContract( date );
        employee.setSitePrestataire( sitePrestataire );
        return employee;
    }
}
