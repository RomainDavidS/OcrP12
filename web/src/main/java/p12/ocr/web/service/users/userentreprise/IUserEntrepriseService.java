package p12.ocr.web.service.users.userentreprise;

import p12.ocr.web.beans.users.UserEntrepriseBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserEntrepriseService extends UserDetailsService {

    UserEntrepriseBean findById(Long id);
    UserEntrepriseBean findByNni(String nni);
    List<UserEntrepriseBean> findAll();
    UserEntrepriseBean save(UserEntrepriseBean userEntreprise);
    void update( UserEntrepriseBean userEntreprise);
    UserEntrepriseBean getCurrentUser();

    void setDateLastConnexion();
    String currentUserNameSimple();
    String getCurrentUserFullName();
}
