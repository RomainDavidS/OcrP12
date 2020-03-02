package p12.ocr.marche.controller;


import p12.ocr.marche.JsonUtil;
import p12.ocr.marche.MarcheApplication;
import p12.ocr.marche.model.TypeMarche;
import p12.ocr.marche.repository.ITypeMarcheRepository;
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
public class TypeMarcheControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ITypeMarcheRepository typeMarcheRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenTypeMarche_whenGetTypeMarcheById_thenStatus200() throws Exception {

        TypeMarche typeMarche = createTypeMarcheTest("TypeMarche");
        typeMarcheRepository.saveAndFlush( typeMarche );

        mvc.perform(get("/typeMarche/find/"+ typeMarche.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( typeMarche.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenTypeMarche_whenGetAllTypeMarche_thenStatus200() throws Exception {
        TypeMarche typeMarche1 = createTypeMarcheTest("TypeMarche1");
        typeMarcheRepository.saveAndFlush( typeMarche1 );

        TypeMarche typeMarche2 = createTypeMarcheTest("TypeMarche2");
        typeMarcheRepository.saveAndFlush( typeMarche2 );

        mvc.perform(get("/typeMarche/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( typeMarche1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( typeMarche2.getId().intValue() ) ) );
    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateTypeMarche() throws Exception {

        TypeMarche typeMarche = createTypeMarcheTest("TypeMarche");
        typeMarcheRepository.saveAndFlush( typeMarche );

        mvc.perform(post("/typeMarche/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(typeMarche)));

        List<TypeMarche> found = typeMarcheRepository.findAll();
        assertThat(found).extracting(TypeMarche::getId).containsOnly(typeMarche.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateTypeMarche() throws Exception {
        TypeMarche typeMarche = createTypeMarcheTest("TypeMarche");
        typeMarcheRepository.saveAndFlush( typeMarche );

        typeMarche.setName( "Update" );

        mvc.perform(put("/typeMarche/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(typeMarche)));

        TypeMarche fromDb = typeMarcheRepository.getOne( typeMarche.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }

    private TypeMarche createTypeMarcheTest(String name){
        TypeMarche typeMarche = new TypeMarche();
        typeMarche.setName(name);
        return typeMarche;
    }
}
