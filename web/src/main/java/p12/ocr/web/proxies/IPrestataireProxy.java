package p12.ocr.web.proxies;


import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import p12.ocr.web.beans.prestataire.*;

import java.util.List;

@FeignClient(name = "zuul-server",contextId = "prestataireProxy")
@RibbonClient(name = "microservice-prestataire")
@RequestMapping("/microservice-prestataire")
public interface IPrestataireProxy {

    /* CallCenter */
    @GetMapping("/callCenter/find/{id}")
    CallCenterBean findCallCenterById(@PathVariable("id") Long id);

    @GetMapping("/callCenter/all")
    List<CallCenterBean> findAllCallcenter();

    @PostMapping("/callCenter/save")
    CallCenterBean saveCallCenter( @RequestBody CallCenterBean callCenter);

    @PutMapping("/callCenter/update")
    @ResponseStatus(HttpStatus.OK)
    void updateCallCenter( @RequestBody CallCenterBean callCenter);

    /* Employee */
    @GetMapping("/employee/find/{id}")
    EmployeeBean findEmployeeById(@PathVariable Long id);

    @GetMapping("/employee/all")
    List<EmployeeBean> findAllEmployee();

    @GetMapping("/employee/all/actif/{id}")
    List<EmployeeBean> findAllEmployeeActif(@PathVariable Long id);

    @GetMapping("/employee/all/inactif/{id}")
    List<EmployeeBean> findAllEmployeeInactif(@PathVariable Long id);

    @PostMapping("/employee/save")
    EmployeeBean saveEmployee(@RequestBody EmployeeBean employee);

    @PutMapping("/employee/update")
    void updateEmployee( @RequestBody EmployeeBean employee);

    /* Entreprise */
    @GetMapping("/entreprise/find/{id}")
    EntrepriseBean findEntrepriseById(@PathVariable Long id);

    @GetMapping("/entreprise/find/siret/{siret}")
    EntrepriseBean findEntrepriseBySiret(@PathVariable String siret);

    @GetMapping("/entreprise/all")
    List<EntrepriseBean> findAllEntreprise();

    @PostMapping("/entreprise/save")
    EntrepriseBean saveEntreprise( @RequestBody EntrepriseBean entreprise);

    @PutMapping("/entreprise/update")
    void updateEntreprise( @RequestBody EntrepriseBean entreprise);

    /* Site */
    @GetMapping("/sitePrestataire/find/{id}")
    SitePrestataireBean findSiteById(@PathVariable("id") Long id);

    @GetMapping("/sitePrestataire/all")
    List<SitePrestataireBean> findAllSite();

    @PostMapping("/sitePrestataire/save")
    SitePrestataireBean saveSite(@RequestBody SitePrestataireBean site);

    @PutMapping("/sitePrestataire/update")
    void updateSite( @RequestBody SitePrestataireBean site);

    /* FonctionPrestataire */
    @GetMapping("/fonctionPrestataire/find/{id}")
    FonctionPrestataireBean findFonctionPrestataireById(@PathVariable Long id);

    @GetMapping("/fonctionPrestataire/all")
    List<FonctionPrestataireBean> findAllFonctionPrestataire();

    @PostMapping("/fonctionPrestataire/save")
    FonctionPrestataireBean saveFonctionPrestataire( @RequestBody FonctionPrestataireBean fonctionPrestataire);

    @PutMapping("/fonctionPrestataire/update")
    void updateFontionPrestataire(@RequestBody FonctionPrestataireBean fonctionPrestataire);

    /* SiteOffice */
    @GetMapping("/siteOffice/find/{id}")
    SiteOfficeBean findSiteOfficeById(@PathVariable Long id);

    @GetMapping("/siteOffice/all")
    List<SiteOfficeBean> findAllSiteOffice();

    @PostMapping("/siteOffice/save")
    SiteOfficeBean saveSiteOffice( @RequestBody SiteOfficeBean siteOffice);

    @PutMapping("/siteOffice/update")
    void updateSiteOffice( @RequestBody SiteOfficeBean siteOffice);

    @DeleteMapping("/siteOffice/delete/{id}")
     void deleteSiteOffice(@PathVariable Long id);


}
