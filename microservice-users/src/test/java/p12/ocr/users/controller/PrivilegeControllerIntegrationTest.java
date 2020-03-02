package p12.ocr.users.controller;

import p12.ocr.users.UsersApplication;
import p12.ocr.users.model.Privilege;
import p12.ocr.users.repository.IPrivilegeRepository;
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
public class PrivilegeControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IPrivilegeRepository privilegeRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenPrivilege_whenGetPrivilegeById_thenStatus200() throws Exception {

        Privilege privilege = createPrivilegeTest("Privilege");
        privilegeRepository.saveAndFlush( privilege );

        mvc.perform(get("/privilege/find/"+ privilege.getName() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( privilege.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenPrivilege_whenGetAllPrivilege_thenStatus200() throws Exception {
        Privilege privilege1 = createPrivilegeTest("Privilege1");
        privilegeRepository.saveAndFlush( privilege1 );

        Privilege privilege2 = createPrivilegeTest("Privilege2");
        privilegeRepository.saveAndFlush( privilege2 );

        mvc.perform(get("/privilege/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( privilege1.getId().intValue() )  ) )
                .andExpect(jsonPath("$[1].id", is( privilege2.getId().intValue() ) ) );
    }

    private Privilege createPrivilegeTest(String name){
        return new Privilege( name );
    }
}
