package p12.ocr.prestataire.repository;
import p12.ocr.prestataire.model.CallCenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CallCenterRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ICallCenterRepository callCenterRepository;

    @Autowired
    private ISitePrestataireRepository sitePrestataireRepository;

    @Before
    public void setUp(){
        sitePrestataireRepository.deleteAll();
        callCenterRepository.deleteAll(); }

    @After
    public void erase(){
        callCenterRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnCallCenter() {

        CallCenter callCenter = createTestCallCenter("CallCenter");

        entityManager.persistAndFlush(callCenter);

        CallCenter fromDb = callCenterRepository.findById( callCenter.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( callCenter.getId() );
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        CallCenter fromDb = callCenterRepository.findById( 1L).orElse(null );
        assertThat(fromDb).isNull();
    }


    @Test
    public void whenSaveCallCenter_thenReturnCallCenter() {
        CallCenter callCenter = createTestCallCenter("CallCenter");
        callCenterRepository.save( callCenter );
        CallCenter fromDb = callCenterRepository.findById( callCenter.getId() ).orElse(null);
        assertThat(fromDb.getId()).isEqualTo( callCenter.getId() );
    }


    @Test
    public void givenSetOfCallCenter_whenFindAll_thenReturnAllCallCenter() {

        CallCenter callCenter1 = createTestCallCenter("CallCenter1");
        CallCenter callCenter2 = createTestCallCenter("CallCenter2");
        CallCenter callCenter3 = createTestCallCenter("CallCenter3");

        entityManager.persist(callCenter1);
        entityManager.persist(callCenter2);
        entityManager.persist(callCenter3);
        entityManager.flush();

        List<CallCenter> allCallCenter = callCenterRepository.findAll();

        assertThat(allCallCenter).hasSize(3).extracting(CallCenter::getId).containsOnly(
                callCenter1.getId(), callCenter2.getId(), callCenter3.getId());
    }

    private CallCenter createTestCallCenter(String name){
        CallCenter callCenter = new CallCenter();
        callCenter.setName( name );
        return callCenter;
    }
}
