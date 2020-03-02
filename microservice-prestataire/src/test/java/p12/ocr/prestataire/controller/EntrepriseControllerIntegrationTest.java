package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.JsonUtil;
import p12.ocr.prestataire.PrestataireApplication;
import p12.ocr.prestataire.model.Entreprise;
import p12.ocr.prestataire.repository.IEntrepriseRepository;
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
public class EntrepriseControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IEntrepriseRepository entrepriseRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenEntreprise_whenGetEntrepriseById_thenStatus200() throws Exception {
        Entreprise entreprise = createEntrepriseTest( "Entreprise");
        entrepriseRepository.saveAndFlush( entreprise );

        mvc.perform(get("/entreprise/find/"+ entreprise.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( entreprise.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenEntreprise_whenGetEntrepriseBySiret_thenStatus200() throws Exception {
        Entreprise entreprise = createEntrepriseTest( "Entreprise");
        entreprise.setSiret("111");
        entrepriseRepository.saveAndFlush( entreprise );

        mvc.perform(get("/entreprise/find/siret/"+ entreprise.getSiret() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( entreprise.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenEntreprise_whenGetAllEntreprise_thenStatus200() throws Exception {
        Entreprise entreprise1 = createEntrepriseTest( "Entreprise1");
        entrepriseRepository.saveAndFlush( entreprise1 );

        Entreprise entreprise2 = createEntrepriseTest( "Entreprise2");
        entrepriseRepository.saveAndFlush( entreprise2 );

        mvc.perform(get("/entreprise/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( entreprise1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( entreprise2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateEntreprise() throws Exception {
        Entreprise entreprise = createEntrepriseTest( "Entreprise");
        entrepriseRepository.saveAndFlush( entreprise );

        mvc.perform(post("/entreprise/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(entreprise)));

        List<Entreprise> found = entrepriseRepository.findAll();
        assertThat(found).extracting(Entreprise::getId).containsOnly(entreprise.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateEntreprise() throws Exception {
        Entreprise entreprise = createEntrepriseTest( "Entreprise");
        entrepriseRepository.saveAndFlush( entreprise );

        entreprise.setName( "Update" );

        mvc.perform(put("/entreprise/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(entreprise)));

        Entreprise fromDb = entrepriseRepository.getOne( entreprise.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }


    private Entreprise createEntrepriseTest(String name){
        Entreprise entreprise = new Entreprise();
        entreprise.setName( name );
        return entreprise;
    }
}
