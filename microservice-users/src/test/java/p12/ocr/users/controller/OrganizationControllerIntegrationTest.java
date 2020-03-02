package p12.ocr.users.controller;

import p12.ocr.users.UsersApplication;
import p12.ocr.users.JsonUtil;
import p12.ocr.users.model.Organization;
import p12.ocr.users.repository.IOrganizationRepository;
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
        classes = UsersApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class OrganizationControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenOrganization_whenGetOrganizationById_thenStatus200() throws Exception {

        Organization organization = createOrganizationTest("Organization");
        organizationRepository.saveAndFlush( organization );

        mvc.perform(get("/organization/find/"+ organization.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( organization.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenOrganization_whenGetAllOrganization_thenStatus200() throws Exception {
        Organization organization1 = createOrganizationTest("Organization1");
        organizationRepository.saveAndFlush( organization1 );

        Organization organization2 = createOrganizationTest("Organization2");
        organizationRepository.saveAndFlush( organization2 );

        mvc.perform(get("/organization/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( organization1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( organization2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateOrganization() throws Exception {

        Organization organization = createOrganizationTest("Organization");
        organizationRepository.saveAndFlush( organization );

        mvc.perform(post("/organization/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(organization)));

        List<Organization> found = organizationRepository.findAll();
        assertThat(found).extracting(Organization::getId).containsOnly(organization.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateOrganization() throws Exception {
        Organization organization = createOrganizationTest("Organization");
        organizationRepository.saveAndFlush( organization );

        organization.setName( "Update" );

        mvc.perform(put("/organization/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(organization)));

        Organization fromDb = organizationRepository.getOne( organization.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }


    private Organization createOrganizationTest(String name){return new Organization( name );}
}
