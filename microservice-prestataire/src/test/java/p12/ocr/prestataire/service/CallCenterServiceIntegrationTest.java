package p12.ocr.prestataire.service;

import p12.ocr.prestataire.model.CallCenter;
import p12.ocr.prestataire.repository.ICallCenterRepository;
import p12.ocr.prestataire.service.callcenter.CallCenterServiceImpl;
import p12.ocr.prestataire.service.callcenter.ICallCenterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class CallCenterServiceIntegrationTest {

    @TestConfiguration
    static class CallCenterServiceIntegrationTestContextConfiguration {
        @Bean
        public CallCenterServiceImpl callCenterService() {return new CallCenterServiceImpl();}
    }

    @Autowired
    private ICallCenterService callCenterService;

    @MockBean
    private ICallCenterRepository callCenterRepository;


    @Before
    public void setUp() {

        CallCenter callCenter1 = new CallCenter();
        callCenter1.setId( 1L );
        callCenter1.setName("CallCenter1");

        CallCenter callCenter2 = new CallCenter();
        callCenter2.setName("CallCenter2");

        CallCenter callCenter3 = new CallCenter();
        callCenter3.setName("CallCenter3");

        List<CallCenter> callCenterList = Arrays.asList( callCenter1,callCenter2,callCenter3 );

        Mockito.when(callCenterRepository.findById( callCenter1.getId() ) ).thenReturn( Optional.of( callCenter1 )  );

        Mockito.when(callCenterRepository.findById(-99L) ).thenReturn(Optional.empty());

        Mockito.when(callCenterRepository.findAll()).thenReturn( callCenterList );

        Mockito.when(callCenterRepository.save(any(CallCenter.class))).thenReturn(callCenter1);
    }

    @Test
    public void whenValidId_thenCallCenterShouldBeFound() {
        CallCenter fromDb = callCenterService.findById( 1l );
        assertThat(fromDb.getName()).isEqualTo( "CallCenter1" );
        verifyCallCenterFindByIdCalledOnce();
    }


    @Test
    public void whenInvalidId_thenCallCenterShouldNotBeFound() {
        CallCenter fromDb = callCenterService.findById( -99L );
        verifyCallCenterFindByIdCalledOnce();
        assertThat(fromDb).isNull();
    }

    private void verifyCallCenterFindByIdCalledOnce() {
        Mockito.verify(callCenterRepository, VerificationModeFactory.times(1)).findById( Mockito.anyLong() );
        Mockito.reset( callCenterRepository );
    }

    @Test
    public void given3CallCenter_whenGetAll_thenReturn3Records() {

        CallCenter callCenter1 = new CallCenter();
        callCenter1.setName("CallCenter1");

        CallCenter callCenter2 = new CallCenter();
        callCenter2.setName("CallCenter2");

        CallCenter callCenter3 = new CallCenter();
        callCenter3.setName("CallCenter3");

        List<CallCenter> callCenterList = callCenterService.findAll();
        verifyFindAllCallCenterIsCalledOnce();

        assertThat(callCenterList).hasSize(3).extracting(CallCenter::getName).containsOnly(
                callCenter1.getName(), callCenter2.getName(), callCenter3.getName() );
    }

    private void verifyFindAllCallCenterIsCalledOnce() {
        Mockito.verify(callCenterRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset( callCenterRepository );
    }

    @Test
    public void whenSaveCallCenter_thenReturnCallCenter() {

        CallCenter callCenter = callCenterService.save(  new CallCenter() );
        CallCenter fromDb = callCenterService.findById( callCenter.getId() );
        verifySaveCallCenterCalledOnce();
        assertThat(fromDb.getId() ).isEqualTo( callCenter.getId() );
    }

    private void verifySaveCallCenterCalledOnce(){

        Mockito.verify(callCenterRepository, VerificationModeFactory.times(1) ).save( any(CallCenter.class) );
        Mockito.reset( callCenterRepository );
    }

}
