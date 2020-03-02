package p12.ocr.users.controller;

import p12.ocr.users.UsersApplication;
import p12.ocr.users.JsonUtil;
import p12.ocr.users.model.UserEntreprise;
import p12.ocr.users.repository.IUserEntrepriseRepository;
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
public class UserEntrepriseControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenUserEntreprise_whenGetUserEntrepriseById_thenStatus200() throws Exception {

        UserEntreprise userEntreprise = createUserEntrepriseTest("Nni");
        userEntrepriseRepository.saveAndFlush(userEntreprise);

        mvc.perform(get("/userEntreprise/id/"+ userEntreprise.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( userEntreprise.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenUserEntreprise_whenGetUserEntrepriseByNni_thenStatus200() throws Exception {

        UserEntreprise userEntreprise = createUserEntrepriseTest("Nni");
        userEntrepriseRepository.saveAndFlush(userEntreprise);

        mvc.perform(get("/userEntreprise/nni/"+ userEntreprise.getNni() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( userEntreprise.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenUserEntreprise_whenGetAllUserEntreprise_thenStatus200() throws Exception {
        UserEntreprise userEntreprise1 = createUserEntrepriseTest("Nni1");
        userEntrepriseRepository.saveAndFlush(userEntreprise1);

        UserEntreprise userEntreprise2 = createUserEntrepriseTest("Nni2");
        userEntrepriseRepository.saveAndFlush(userEntreprise2);

        mvc.perform(get("/userEntreprise/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( userEntreprise1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( userEntreprise2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateUserEntreprise() throws Exception {

        UserEntreprise userEntreprise = createUserEntrepriseTest("Nni");
        userEntrepriseRepository.saveAndFlush(userEntreprise);

        mvc.perform(post("/userEntreprise/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(userEntreprise)));

        List<UserEntreprise> found = userEntrepriseRepository.findAll();
        assertThat(found).extracting(UserEntreprise::getId).containsOnly(userEntreprise.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateUserEntreprise() throws Exception {
        UserEntreprise userEntreprise = createUserEntrepriseTest("Nni");
        userEntrepriseRepository.saveAndFlush(userEntreprise);

        userEntreprise.setNni( "Update" );

        mvc.perform(put("/userEntreprise/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(userEntreprise)));

        UserEntreprise fromDb = userEntrepriseRepository.getOne( userEntreprise.getId() );

        assertThat( fromDb.getNni() ).isEqualTo( "Update" );

    }

    private UserEntreprise createUserEntrepriseTest(String nni ){
        UserEntreprise userEntreprise = new UserEntreprise();
        userEntreprise.setNni( nni );
        return userEntreprise;
    }
}
