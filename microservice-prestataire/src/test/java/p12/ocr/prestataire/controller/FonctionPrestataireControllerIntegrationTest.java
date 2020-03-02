package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.JsonUtil;
import p12.ocr.prestataire.PrestataireApplication;
import p12.ocr.prestataire.model.FonctionPrestataire;
import p12.ocr.prestataire.repository.IFonctionPrestataireRepository;
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
        classes = PrestataireApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class FonctionPrestataireControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IFonctionPrestataireRepository fonctionPrestataireRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenFonctionPrestataire_whenGetFonctionPrestataireById_thenStatus200() throws Exception {
        FonctionPrestataire fonctionPrestataire = createFonctionPrestataireTest( "FonctionPrestataire");
        fonctionPrestataireRepository.saveAndFlush( fonctionPrestataire );

        mvc.perform(get("/fonctionPrestataire/find/"+ fonctionPrestataire.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( fonctionPrestataire.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenFonctionPrestataire_whenGetAllFonctionPrestataire_thenStatus200() throws Exception {
        FonctionPrestataire fonctionPrestataire1 = createFonctionPrestataireTest( "FonctionPrestataire1");
        fonctionPrestataireRepository.saveAndFlush( fonctionPrestataire1 );

        FonctionPrestataire fonctionPrestataire2 = createFonctionPrestataireTest( "FonctionPrestataire2");
        fonctionPrestataireRepository.saveAndFlush( fonctionPrestataire2 );

        mvc.perform(get("/fonctionPrestataire/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( fonctionPrestataire1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( fonctionPrestataire2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateFonctionPrestataire() throws Exception {
        FonctionPrestataire fonctionPrestataire = createFonctionPrestataireTest( "FonctionPrestataire");
        fonctionPrestataireRepository.saveAndFlush( fonctionPrestataire );

        mvc.perform(post("/fonctionPrestataire/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(fonctionPrestataire)));

        List<FonctionPrestataire> found = fonctionPrestataireRepository.findAll();
        assertThat(found).extracting(FonctionPrestataire::getId).containsOnly(fonctionPrestataire.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateFonctionPrestataire() throws Exception {
        FonctionPrestataire fonctionPrestataire = createFonctionPrestataireTest( "FonctionPrestataire");
        fonctionPrestataireRepository.saveAndFlush( fonctionPrestataire );

        fonctionPrestataire.setName( "Update" );

        mvc.perform(put("/fonctionPrestataire/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(fonctionPrestataire)));

       FonctionPrestataire fromDb = fonctionPrestataireRepository.getOne( fonctionPrestataire.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }


    private FonctionPrestataire createFonctionPrestataireTest(String name){
        FonctionPrestataire fonctionPrestataire = new FonctionPrestataire();
        fonctionPrestataire.setName( name );
        return fonctionPrestataire;
    }
}
