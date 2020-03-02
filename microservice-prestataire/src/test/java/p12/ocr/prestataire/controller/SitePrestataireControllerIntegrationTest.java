package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.JsonUtil;
import p12.ocr.prestataire.PrestataireApplication;
import p12.ocr.prestataire.model.SitePrestataire;
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
public class SitePrestataireControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ISitePrestataireRepository sitePrestataireRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenSitePrestataire_whenGetSitePrestataireById_thenStatus200() throws Exception {
        SitePrestataire sitePrestataire = createSitePrestataireTest( "SitePrestataire");
        sitePrestataireRepository.saveAndFlush( sitePrestataire );

        mvc.perform(get("/sitePrestataire/find/"+ sitePrestataire.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( sitePrestataire.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenSitePrestataire_whenGetAllSitePrestataire_thenStatus200() throws Exception {
        SitePrestataire sitePrestataire1 = createSitePrestataireTest( "SitePrestataire1");
        sitePrestataireRepository.saveAndFlush( sitePrestataire1 );

        SitePrestataire sitePrestataire2 = createSitePrestataireTest( "SitePrestataire2");
        sitePrestataireRepository.saveAndFlush( sitePrestataire2 );

        mvc.perform(get("/sitePrestataire/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( sitePrestataire1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( sitePrestataire2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateSitePrestataire() throws Exception {
        SitePrestataire sitePrestataire = createSitePrestataireTest( "SitePrestataire");
        sitePrestataireRepository.saveAndFlush( sitePrestataire );

        mvc.perform(post("/sitePrestataire/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(sitePrestataire)));

        List<SitePrestataire> found = sitePrestataireRepository.findAll();
        assertThat(found).extracting(SitePrestataire::getId).containsOnly(sitePrestataire.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateSitePrestataire() throws Exception {
        SitePrestataire sitePrestataire = createSitePrestataireTest( "SitePrestataire");
        sitePrestataireRepository.saveAndFlush( sitePrestataire );

        sitePrestataire.setName( "Update" );

        mvc.perform(put("/sitePrestataire/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(sitePrestataire)));

        SitePrestataire fromDb = sitePrestataireRepository.getOne( sitePrestataire.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }


    private SitePrestataire createSitePrestataireTest(String name){
        SitePrestataire sitePrestataire = new SitePrestataire();
        sitePrestataire.setName( name );
        return sitePrestataire;
    }
}
