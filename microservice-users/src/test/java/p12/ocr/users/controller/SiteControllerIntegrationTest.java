package p12.ocr.users.controller;

import p12.ocr.users.UsersApplication;
import p12.ocr.users.JsonUtil;
import p12.ocr.users.model.Site;
import p12.ocr.users.repository.ISiteRepository;
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
public class SiteControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ISiteRepository siteRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenSite_whenGetSiteById_thenStatus200() throws Exception {

        Site site = createSiteTest("Site");
        siteRepository.saveAndFlush( site );

        mvc.perform(get("/site/find/"+ site.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( site.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenSite_whenGetAllSite_thenStatus200() throws Exception {
        Site site1 = createSiteTest("Site1");
        siteRepository.saveAndFlush( site1 );

        Site site2 = createSiteTest("Site2");
        siteRepository.saveAndFlush( site2 );

        mvc.perform(get("/site/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( site1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( site2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateSite() throws Exception {

        Site site = createSiteTest("Site");
        siteRepository.saveAndFlush( site );

        mvc.perform(post("/site/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(site)));

        List<Site> found = siteRepository.findAll();
        assertThat(found).extracting(Site::getId).containsOnly(site.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateSite() throws Exception {
        Site site = createSiteTest("Site");
        siteRepository.saveAndFlush( site );

        site.setName( "Update" );

        mvc.perform(put("/site/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(site)));

        Site fromDb = siteRepository.getOne( site.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }


    private Site createSiteTest(String name){
        return new Site( name );
    }
}
