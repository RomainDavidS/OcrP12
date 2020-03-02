package p12.ocr.marche.controller;


import p12.ocr.marche.JsonUtil;
import p12.ocr.marche.MarcheApplication;
import p12.ocr.marche.model.Indemnisation;
import p12.ocr.marche.repository.IIndemnisationRepository;
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

import static org.hamcrest.CoreMatchers.is;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
public class IdemnisationControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IIndemnisationRepository indemnisationRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenIndemnisation_whenGetIndemnisationById_thenStatus200() throws Exception {

        Indemnisation indemnisation = createIndemnisationTest("Indemnisation");
        indemnisationRepository.saveAndFlush( indemnisation );

        mvc.perform(get("/indemnisation/find/"+ indemnisation.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( indemnisation.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenIndemnisation_whenGetAllIndemnisation_thenStatus200() throws Exception {
        Indemnisation indemnisation1 = createIndemnisationTest("Indemnisation1");
        indemnisationRepository.saveAndFlush( indemnisation1 );

        Indemnisation indemnisation2 = createIndemnisationTest("Indemnisation2");
        indemnisationRepository.saveAndFlush( indemnisation2 );

        mvc.perform(get("/indemnisation/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( indemnisation1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( indemnisation2.getId().intValue() ) ) );
    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateIndemnisation() throws Exception {

        Indemnisation indemnisation = createIndemnisationTest("Indemnisation");
        indemnisationRepository.saveAndFlush( indemnisation );

        mvc.perform(post("/indemnisation/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(indemnisation)));

        List<Indemnisation> found = indemnisationRepository.findAll();
        assertThat(found).extracting(Indemnisation::getId).containsOnly(indemnisation.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateIndemnisation() throws Exception {
        Indemnisation indemnisation = createIndemnisationTest("Indemnisation");
        indemnisationRepository.saveAndFlush( indemnisation );

        indemnisation.setPdl( "Update" );

        mvc.perform(put("/indemnisation/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(indemnisation)));

        Indemnisation fromDb = indemnisationRepository.getOne( indemnisation.getId() );

        assertThat( fromDb.getPdl() ).isEqualTo( "Update" );

    }

    private Indemnisation createIndemnisationTest(String pdl){
        Indemnisation indemnisation = new Indemnisation();
        indemnisation.setPdl(pdl);
        return indemnisation;
    }
}
