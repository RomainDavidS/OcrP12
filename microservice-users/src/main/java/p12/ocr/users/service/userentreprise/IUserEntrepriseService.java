package p12.ocr.users.service.userentreprise;

import p12.ocr.users.model.UserEntreprise;

import java.util.List;

public interface IUserEntrepriseService {


    UserEntreprise findById(Long id);

    UserEntreprise findByNni(String nni);

    List<UserEntreprise> findAll();

    UserEntreprise save(UserEntreprise userEntreprise);
}
