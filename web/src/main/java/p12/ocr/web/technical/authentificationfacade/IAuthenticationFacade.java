package p12.ocr.web.technical.authentificationfacade;

import org.springframework.security.core.Authentication;


public interface IAuthenticationFacade  {

    Authentication getAuthentication();
}
