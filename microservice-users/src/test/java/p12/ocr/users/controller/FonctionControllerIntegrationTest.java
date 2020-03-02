package p12.ocr.users.controller;

import p12.ocr.users.UsersApplication;
import p12.ocr.users.JsonUtil;
import p12.ocr.users.model.Fonction;
import p12.ocr.users.repository.IFonctionRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UsersApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class FonctionControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IFonctionRepository fonctionRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenFonction_whenGetFonctionById_thenStatus200() throws Exception {

        Fonction fonction = createFonctionTest("Fonction");
        fonctionRepository.saveAndFlush( fonction );

        mvc.perform(get("/fonction/find/"+ fonction.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( fonction.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenFonction_whenGetAllFonction_thenStatus200() throws Exception {
        Fonction fonction1 = createFonctionTest("Fonction1");
        fonctionRepository.saveAndFlush( fonction1 );

        Fonction fonction2 = createFonctionTest("Fonction2");
        fonctionRepository.saveAndFlush( fonction2 );

        mvc.perform(get("/fonction/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( fonction1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( fonction2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateFonction() throws Exception {

        Fonction fonction = createFonctionTest("Fonction");
        fonctionRepository.saveAndFlush( fonction );

        mvc.perform(post("/fonction/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(fonction)));

        List<Fonction> found = fonctionRepository.findAll();
        assertThat(found).extracting(Fonction::getId).containsOnly(fonction.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateFonction() throws Exception {
        Fonction fonction = createFonctionTest("Fonction");
        fonctionRepository.saveAndFlush( fonction );

        fonction.setName( "Update" );

        mvc.perform(put("/fonction/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(fonction)));

        Fonction fromDb = fonctionRepository.getOne( fonction.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }


    private Fonction createFonctionTest(String name){
        return new Fonction( name );
    }
}
