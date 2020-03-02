package p12.ocr.prestataire.repository;

import p12.ocr.prestataire.model.CallCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICallCenterRepository extends JpaRepository<CallCenter,Long> {
}
