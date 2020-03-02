package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.JsonUtil;
import p12.ocr.prestataire.PrestataireApplication;
import p12.ocr.prestataire.model.SiteOffice;
import p12.ocr.prestataire.repository.ISiteOfficeRepository;
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
public class SiteOfficeControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ISiteOfficeRepository siteOfficeRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenSiteOffice_whenGetSiteOfficeById_thenStatus200() throws Exception {
        SiteOffice siteOffice = createSiteOfficeTest( "SiteOffice");
        siteOfficeRepository.saveAndFlush( siteOffice );

        mvc.perform(get("/siteOffice/find/"+ siteOffice.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( siteOffice.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenSiteOffice_whenGetAllSiteOffice_thenStatus200() throws Exception {
        SiteOffice siteOffice1 = createSiteOfficeTest( "SiteOffice1");
        siteOfficeRepository.saveAndFlush( siteOffice1 );

        SiteOffice siteOffice2 = createSiteOfficeTest( "SiteOffice2");
        siteOfficeRepository.saveAndFlush( siteOffice2 );

        mvc.perform(get("/siteOffice/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( siteOffice1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( siteOffice2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateSiteOffice() throws Exception {
        SiteOffice siteOffice = createSiteOfficeTest( "SiteOffice");
        siteOfficeRepository.saveAndFlush( siteOffice );

        mvc.perform(post("/siteOffice/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(siteOffice)));

        List<SiteOffice> found = siteOfficeRepository.findAll();
        assertThat(found).extracting(SiteOffice::getId).containsOnly(siteOffice.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateSiteOffice() throws Exception {
        SiteOffice siteOffice = createSiteOfficeTest( "SiteOffice");
        siteOfficeRepository.saveAndFlush( siteOffice );

        siteOffice.setRhName( "Update" );

        mvc.perform(put("/siteOffice/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(siteOffice)));

        SiteOffice fromDb = siteOfficeRepository.getOne( siteOffice.getId() );

        assertThat( fromDb.getRhName() ).isEqualTo( "Update" );

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenDeleteSiteOffice() throws Exception {
        SiteOffice siteOffice = createSiteOfficeTest( "SiteOffice");
        siteOfficeRepository.saveAndFlush( siteOffice );

        mvc.perform(delete("/siteOffice/delete/"+ siteOffice.getId() ).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(siteOffice)));

        SiteOffice fromDb = siteOfficeRepository.findById( siteOffice.getId() ).orElse(null);

        assertThat(fromDb).isNull();

    }

    private SiteOffice createSiteOfficeTest(String name){
        SiteOffice siteOffice = new SiteOffice();
        siteOffice.setRhName( name );
        return siteOffice;
    }
}
