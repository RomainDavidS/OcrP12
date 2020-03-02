package p12.ocr.web.proxies;

import p12.ocr.web.beans.users.FonctionBean;
import p12.ocr.web.beans.users.OrganizationBean;
import p12.ocr.web.beans.users.PrivilegeBean;
import p12.ocr.web.beans.users.RoleBean;
import p12.ocr.web.beans.users.SiteEntrepriseBean;
import p12.ocr.web.beans.users.TypeSiteBean;
import p12.ocr.web.beans.users.UserEntrepriseBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server",contextId = "usersProxy")
@RibbonClient(name = "microservice-users")
@RequestMapping("/microservice-users")
public interface IUsersProxy {

    /* UserEntreprise */
    @GetMapping("/userEntreprise/id/{id}")
    UserEntrepriseBean findUserEntrepriseById(@PathVariable("id") Long id);

    @GetMapping("/userEntreprise/nni/{nni}")
    UserEntrepriseBean findUserEntrepriseByNni(@PathVariable("nni") String nni);

    @GetMapping("/userEntreprise/all")
    List<UserEntrepriseBean> findAllUserEntreprise();

    @PostMapping("/userEntreprise/save")
    UserEntrepriseBean saveUserEntreprise(@RequestBody UserEntrepriseBean userEntreprise);

    @PutMapping("/userEntreprise/update")
    void updateUserEntreprise( @RequestBody UserEntrepriseBean userEntreprise);


    /* Fonction */
    @GetMapping("/fonction/find/{id}")
    FonctionBean findFonctionById(@PathVariable("id") Long id);

    @GetMapping("/fonction/all")
    List<FonctionBean> findAllFonction();

    @PostMapping("/fonction/save")
    FonctionBean saveFonction(@RequestBody FonctionBean fonction);

    @PutMapping("/fonction/update")
    void updateFonction( @RequestBody FonctionBean fonction);

    /* Organization */
    @GetMapping("/organization/find/{id}")
    OrganizationBean findOrganizationById(@PathVariable("id") Long id);

    @GetMapping("/organization/all")
    List<OrganizationBean> findAllOrganization();

    @PostMapping("/organization/save")
    OrganizationBean saveOrganization( @RequestBody OrganizationBean organization);

    @PutMapping("/organization/update")
    void updateOrganization( @RequestBody OrganizationBean organization);

    /* Privilege */
    @GetMapping("/privilege/find/{name}")
    PrivilegeBean findPrivilegeByName(@PathVariable("name") String name);

    @GetMapping("/privilege/all")
    List<PrivilegeBean> findAllPrivilege();


    /* Role */
    @GetMapping("/role/find/{id}")
    RoleBean findRoleById(@PathVariable("id") Long id);

    @GetMapping("/role/all")
    List<RoleBean> findAllRole();

    /* Site */
    @GetMapping("/site/find/{id}")
    SiteEntrepriseBean findSiteById(@PathVariable Long id);

    @GetMapping("/site/all")
    List<SiteEntrepriseBean> findAllSite();

    @PostMapping("/site/save")
    SiteEntrepriseBean saveSite(@RequestBody SiteEntrepriseBean site);

    @PutMapping("/site/update")
    void updateSite(@RequestBody SiteEntrepriseBean site);

    /* TypeSite */
    @GetMapping("/typeSite/find/{id}")
    TypeSiteBean findTypeSiteById(@PathVariable Long id);

    @GetMapping("/typeSite/all")
    List<TypeSiteBean> findAllTypeSite();

    @PostMapping("/typeSite/save")
    TypeSiteBean saveTypeSite( @RequestBody TypeSiteBean typeSite);

    @PutMapping("/typeSite/update")
    void updateTypeSite( @RequestBody TypeSiteBean typeSite);
}
