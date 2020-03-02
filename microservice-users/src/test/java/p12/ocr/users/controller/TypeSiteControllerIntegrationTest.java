package p12.ocr.users.controller;

import p12.ocr.users.UsersApplication;
import p12.ocr.users.JsonUtil;
import p12.ocr.users.model.TypeSite;
import p12.ocr.users.repository.ITypeSiteRepository;
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
public class TypeSiteControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ITypeSiteRepository typeSiteRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenTypeSite_whenGetTypeSiteById_thenStatus200() throws Exception {

        TypeSite typeSite = createTypeSiteTest("TypeSite");
        typeSiteRepository.saveAndFlush( typeSite );

        mvc.perform(get("/typeSite/find/"+ typeSite.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( typeSite.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenTypeSite_whenGetAllTypeSite_thenStatus200() throws Exception {
        TypeSite typeSite1 = createTypeSiteTest("TypeSite1");
        typeSiteRepository.saveAndFlush( typeSite1 );

        TypeSite typeSite2 = createTypeSiteTest("TypeSite2");
        typeSiteRepository.saveAndFlush( typeSite2 );

        mvc.perform(get("/typeSite/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( typeSite1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( typeSite2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateTypeSite() throws Exception {

        TypeSite typeSite = createTypeSiteTest("TypeSite");
        typeSiteRepository.saveAndFlush( typeSite );

        mvc.perform(post("/typeSite/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(typeSite)));

        List<TypeSite> found = typeSiteRepository.findAll();
        assertThat(found).extracting(TypeSite::getId).containsOnly(typeSite.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateTypeSite() throws Exception {
        TypeSite typeSite = createTypeSiteTest("TypeSite");
        typeSiteRepository.saveAndFlush( typeSite );

        typeSite.setName( "Update" );

        mvc.perform(put("/typeSite/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(typeSite)));

        TypeSite fromDb = typeSiteRepository.getOne( typeSite.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }


    private TypeSite createTypeSiteTest(String name){
        return new TypeSite( name );
    }
}
