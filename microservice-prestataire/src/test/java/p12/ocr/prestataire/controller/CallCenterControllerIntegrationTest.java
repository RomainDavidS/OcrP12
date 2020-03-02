package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.JsonUtil;
import p12.ocr.prestataire.PrestataireApplication;
import p12.ocr.prestataire.model.CallCenter;
import p12.ocr.prestataire.repository.ICallCenterRepository;
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
public class CallCenterControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ICallCenterRepository callCenterRepository;

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenCallCenter_whenGetCallCenterById_thenStatus200() throws Exception {
        CallCenter callCenter = createCallCenterTest( "CallCenter");
        callCenterRepository.saveAndFlush( callCenter );

        mvc.perform(get("/callCenter/find/"+ callCenter.getId() ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( callCenter.getId().intValue() ) ) );
    }

    @Test
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void givenCallCenter_whenGetAllCallCenter_thenStatus200() throws Exception {
        CallCenter callCenter1 = createCallCenterTest( "CallCenter1");
        callCenterRepository.saveAndFlush( callCenter1 );

        CallCenter callCenter2 = createCallCenterTest( "CallCenter2");
        callCenterRepository.saveAndFlush( callCenter2 );

        mvc.perform(get("/callCenter/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].id", is( callCenter1.getId().intValue() ) ) )
                .andExpect(jsonPath("$[1].id", is( callCenter2.getId().intValue() ) ) );
    }


    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenCreateCallCenter() throws Exception {
        CallCenter callCenter = createCallCenterTest( "CallCenter");
        callCenterRepository.saveAndFlush( callCenter );

        mvc.perform(post("/callCenter/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(callCenter)));

        List<CallCenter> found = callCenterRepository.findAll();
        assertThat(found).extracting(CallCenter::getId).containsOnly(callCenter.getId());

    }

    @Test
    @Transactional
    @Sql( scripts = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
    @Sql( scripts = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD )
    public void whenValidInput_thenUpdateCallCenter() throws Exception {
        CallCenter callCenter = createCallCenterTest( "CallCenter");
        callCenterRepository.saveAndFlush( callCenter );

        callCenter.setName( "Update" );

        mvc.perform(put("/callCenter/update").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(callCenter)));

        CallCenter fromDb = callCenterRepository.getOne( callCenter.getId() );

        assertThat( fromDb.getName() ).isEqualTo( "Update" );

    }



    private CallCenter createCallCenterTest(String name){
        CallCenter callCenter = new CallCenter();
        callCenter.setName( name );
        return callCenter;
    }
}
