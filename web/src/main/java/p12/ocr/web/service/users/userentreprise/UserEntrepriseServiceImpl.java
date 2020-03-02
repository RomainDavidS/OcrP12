package p12.ocr.web.service.users.userentreprise;

import p12.ocr.web.beans.users.RoleBean;
import p12.ocr.web.beans.users.UserEntrepriseBean;
import p12.ocr.web.proxies.IUsersProxy;
import p12.ocr.web.service.users.fonction.IFonctionService;
import p12.ocr.web.service.users.organization.IOrganizationService;
import p12.ocr.web.service.users.role.IRoleService;
import p12.ocr.web.service.users.site.ISiteEntrepriseService;
import p12.ocr.web.technical.authentificationfacade.IAuthenticationFacade;
import p12.ocr.web.technical.rolechecker.IRoleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserEntrepriseServiceImpl implements IUserEntrepriseService {

    @Autowired
    private IUsersProxy usersProxy;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IFonctionService fonctionService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ISiteEntrepriseService siteEntrepriseService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private IRoleChecker roleChecker;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserEntrepriseBean currentUser;

    private String currentUserFullName;


    @Override
    public UserDetails loadUserByUsername(String nni) throws UsernameNotFoundException {
        currentUser = usersProxy.findUserEntrepriseByNni( nni );

        if (currentUser == null) {
            throw new UsernameNotFoundException("Utilisateur ou mot de passe incorrect.");
        }

        currentUserFullName = currentUser.getFirstName() + " " + currentUser.getLastName() + " ("+ currentUser.getNni() + ") - " + currentUser.getFonction().getName();

        return new org.springframework.security.core.userdetails.User(
                currentUser.getNni(),
                currentUser.getPassword(),
                currentUser.isEnabled(),
                currentUser.isAccountNoExpired(),
                currentUser.isTokenNoExpired(),
                currentUser.isAccountNoLocked(),
                getAuthorities(Arrays.asList( currentUser.getRole() ) ) );
    }

    private Collection<? extends GrantedAuthority> getAuthorities( List<RoleBean> roles ) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleBean role: roles) {
            authorities.add( new SimpleGrantedAuthority(role.getName() ) );
            role.getPrivilegeList().stream()
                    .map(p -> new SimpleGrantedAuthority( p.getName() ) )
                    .forEach(authorities::add);
        }

        return authorities;
    }

    /**
     * Permet la recherche d'un user
     * @param id Identifiant de l'user recherché
     * @return UserEntreprise si connu sinon null
     */
    public UserEntrepriseBean findById(Long id){ return usersProxy.findUserEntrepriseById( id );}

    /**
     * Permet la recherche d'un user
     * @param nni Nni de l'user recherché
     * @return UserEntreprise si nni !="" sinon null
     */
    public UserEntrepriseBean findByNni(String nni){
        if (nni == "")
            return null;

        return usersProxy.findUserEntrepriseByNni( nni );
    }

    /**
     * Permet la recherche de tous les users entreprise
     * @return List< UserEntrepriseBean>
     */
    public List<UserEntrepriseBean> findAll(){return usersProxy.findAllUserEntreprise();}

    /**
     * Permet la création d'un user
     * Default password = a - nécessaire pour la démonstration. A supprimer lors de la mise en place de l'authentification LDAP lors du déploiement
     * @param userEntreprise UserEntrepriseBean à créer
     * @return UserEntrepriseBean
     */
    public UserEntrepriseBean save(UserEntrepriseBean userEntreprise){

        userEntreprise.setRole( roleService.findById( userEntreprise.getRole().getId() ) );
        userEntreprise.setFonction( fonctionService.findById( userEntreprise.getFonction().getId() ) );
        userEntreprise.setOrganization( organizationService.findById( userEntreprise.getOrganization().getId()) );
        userEntreprise.setSite( siteEntrepriseService.findById( userEntreprise.getSite().getId() ) );

        // A supprimer
        userEntreprise.setPassword( passwordEncoder.encode("a") );
        return usersProxy.saveUserEntreprise( userEntreprise );
   }
    /**
     * On recherche quel utilisateur est authentifié
     * @return L'indentifiant d'authentification de l'utilisateur
     */
    public String currentUserNameSimple() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

    /**
     * Permet la mise à jour de l'heure de la dernière connexion
     */
    public void setDateLastConnexion(){
        UserEntrepriseBean userEntreprise = findByNni( currentUserNameSimple() );
        userEntreprise.setLastConnection( new Date() );
        update( userEntreprise );
    }

    /**
     * Permet la mise à jour d'user
     * Default password = a - nécessaire pour la démonstration. A supprimer lors de la mise en place de l'authentification LDAP lors du déploiement
     * @param userEntreprise UserEntrepriseBean à mettre à jour
     */
    public void update( UserEntrepriseBean userEntreprise){

        userEntreprise.setRole( roleService.findById( userEntreprise.getRole().getId() ) );
        userEntreprise.setFonction( fonctionService.findById( userEntreprise.getFonction().getId() ) );
        userEntreprise.setOrganization( organizationService.findById( userEntreprise.getOrganization().getId()) );
        userEntreprise.setSite( siteEntrepriseService.findById( userEntreprise.getSite().getId() ) );

        // A supprimer
        userEntreprise.setPassword( passwordEncoder.encode("a") );
        usersProxy.updateUserEntreprise( userEntreprise );
   }

    public UserEntrepriseBean getCurrentUser() {
        return currentUser;
    }

    public String getCurrentUserFullName(){ return currentUserFullName;}
}
