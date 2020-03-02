package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.JsonUtil;
import p12.ocr.prestataire.PrestataireApplication;
import p12.ocr.prestataire.model.Employee;
import p12.ocr.prestataire.model.SitePrestataire;
import p12.ocr.prestataire.repository.IEmployeeRepository;
import p12.ocr.prestataire.repository.ISitePrestataireRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PrestataireApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class EmployeeControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private ISitePrestataireRepository sitePrestataireRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenEmployee_whenGetEmployeeById_thenStatus200() throws Exception {

        Employee employee = createEmployeeTest( "Employee",null,null);
        employeeRepository.saveAndFlush( employee );

        mvc.perform(get("/employee/find/"+ employee.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( employee.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenEmployee_whenGetAllEmployee_thenStatus200() throws Exception {
        Employee employee1 = createEmployeeTest( "Employee1",null,null);
        employeeRepository.saveAndFlush( employee1 );

        Employee employee2 = createEmployeeTest( "Employee2",null,null);
        employeeRepository.saveAndFlush( employee2 );


        mvc.perform(get("/employee/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( employee1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( employee2.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenEmployeeActif_whenGetAllEmployeeActif_thenStatus200() throws Exception {
        SitePrestataire sitePrestataire = new SitePrestataire();
        sitePrestataire.setName( "SitePrestataire");
        sitePrestataireRepository.saveAndFlush( sitePrestataire );

        Employee employee1 = createEmployeeTest( "Employee1",sitePrestataire,null);
        employeeRepository.saveAndFlush( employee1 );

        Employee employee2 = createEmployeeTest( "Employee2",sitePrestataire,null);
        employeeRepository.saveAndFlush( employee2 );

        mvc.perform(get("/employee/all/actif/" + sitePrestataire.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( employee1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( employee2.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenEmployeeInactif_whenGetAllEmployeeInactif_thenStatus200() throws Exception {
        SitePrestataire sitePrestataire = new SitePrestataire();
        sitePrestataire.setName( "SitePrestataire");
        sitePrestataireRepository.saveAndFlush( sitePrestataire );

        Employee employee1 = createEmployeeTest( "Employee1",sitePrestataire,new Date());
        employeeRepository.saveAndFlush( employee1 );

        Employee employee2 = createEmployeeTest( "Employee2",sitePrestataire,new Date());
        employeeRepository.saveAndFlush( employee2 );

        mvc.perform(get("/employee/all/inactif/" + sitePrestataire.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( employee1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( employee2.getId().intValue() ) ) );
    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateEmployee() throws Exception {
        Employee employee = createEmployeeTest( "Employee",null,null);
        employeeRepository.saveAndFlush( employee );

        mvc.perform(post("/employee/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(employee)));

        List<Employee> found = employeeRepository.findAll();
        assertThat(found).extracting(Employee::getId).containsOnly(employee.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateEmployee() throws Exception {
        Employee employee = createEmployeeTest( "Employee",null,null);
        employeeRepository.saveAndFlush( employee );

        employee.setFirstName( "Update" );

        mvc.perform(put("/employee/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(employee)));

        Employee fromDb = employeeRepository.getOne( employee.getId() );

        assertThat( fromDb.getFirstName() ).isEqualTo( "Update" );

    }

    private Employee createEmployeeTest(String name, SitePrestataire sitePrestataire, Date date){
        Employee employee = new Employee();
        employee.setFirstName( name );
        employee.setEndDateContract( date );
        employee.setSitePrestataire( sitePrestataire );
        return employee;
    }
}
