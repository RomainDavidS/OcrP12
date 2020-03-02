package p12.ocr.marche.controller;


import p12.ocr.marche.JsonUtil;
import p12.ocr.marche.MarcheApplication;
import p12.ocr.marche.model.Marche;
import p12.ocr.marche.repository.IMarcheRepository;
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
        classes = MarcheApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MarcheControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IMarcheRepository marcheRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenMarche_whenGetMarcheById_thenStatus200() throws Exception {

        Marche marche = createMarcheTest("Marche");
        marcheRepository.saveAndFlush( marche );

        mvc.perform(get("/marche/find/"+ marche.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( marche.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenMarche_whenGetAllMarche_thenStatus200() throws Exception {
        Marche marche1 = createMarcheTest("Marche1");
        marcheRepository.saveAndFlush( marche1 );

        Marche marche2 = createMarcheTest("Marche2");
        marcheRepository.saveAndFlush( marche2 );

        mvc.perform(get("/marche/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( marche1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( marche2.getId().intValue() ) ) );
    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateMarche() throws Exception {

        Marche marche = createMarcheTest("Marche");
        marcheRepository.saveAndFlush( marche );

        mvc.perform(post("/marche/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(marche)));

        List<Marche> found = marcheRepository.findAll();
        assertThat(found).extracting(Marche::getId).containsOnly(marche.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateMarche() throws Exception {
        Marche marche = createMarcheTest("Marche");
        marcheRepository.saveAndFlush( marche );

        marche.setName( "Update" );

        mvc.perform(put("/marche/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(marche)));

        Marche fromDb = marcheRepository.getOne( marche.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }

    private Marche createMarcheTest(String name){
        Marche marche = new Marche();
        marche.setName( name );
        return marche;
    }
}
