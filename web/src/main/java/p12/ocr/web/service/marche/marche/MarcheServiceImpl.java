package p12.ocr.web.service.marche.marche;

import p12.ocr.web.beans.marche.MarcheBean;
import p12.ocr.web.proxies.IMarcheProxy;
import p12.ocr.web.service.users.organization.IOrganizationService;
import p12.ocr.web.service.users.userentreprise.IUserEntrepriseService;
import p12.ocr.web.service.marche.typemarche.ITypeMarcheService;
import p12.ocr.web.service.prestataire.entreprise.IEntrepriseService;
import p12.ocr.web.service.prestataire.siteprestataire.ISitePrestataireService;
import p12.ocr.web.technical.menu.marche.MenuGender;
import p12.ocr.web.technical.menu.marche.MenuMarche;
import p12.ocr.web.technical.menu.marche.SimpleMenuMarche;
import p12.ocr.web.technical.menu.marche.MenuOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarcheServiceImpl implements IMarcheService {

    @Autowired
    private IMarcheProxy marcheProxy;

    @Autowired
    private ITypeMarcheService typeMarcheService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private IEntrepriseService entrepriseService;

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @Autowired
    private IUserEntrepriseService userEntrepriseService;

    /**
     * Permet la recherche d'un marché
     * @param id Identifiant du marché recherché
     * @return MarcheBean si connu sinon null
     */
    public MarcheBean findById( Long id){return marcheProxy.findMarcheById( id );}

    /**
     * Permet la recherche de la liste des marchés
     * @return List<MarcheBean>
     */
    public List<MarcheBean> findAll(){ return marcheProxy.findAllMarche();}

    /**
     * Permet la création d'un marché
     * @param marche MarcheBean à créer
     * @return MarcheBean
     */
    public MarcheBean save( MarcheBean marche){

        marche.setTypeMarche( typeMarcheService.findById( marche.getTypeMarche().getId() ) );

        return marcheProxy.saveMarche( marche );
    }

    /**
     * Permet la mise à jour d'un marché
     * @param marche MarcheBean à mettre à jour
     */
    public void update( MarcheBean marche){
        marche.setTypeMarche( typeMarcheService.findById( marche.getTypeMarche().getId() ) );

        marcheProxy.updateMarche( marche );
    }

    /**
     * Permet la contruction automatique du menu des marchés
     * @return List<MenuOrganization>
     */
    public List<MenuOrganization>  getMenuMarche(){

       ArrayList<MenuMarche> menuMarcheList = new ArrayList<>();
       List<MarcheBean> marcheList = findAll();

       // Liste des marchés par EPDR
       HashMap< Long , String > hashMapMenuMarcheN0 = new HashMap <>();

       //N2
       for (MarcheBean m : marcheList ) {

           if( !hashMapMenuMarcheN0.containsKey( m.getIdOrganization() ) )
               hashMapMenuMarcheN0.put( m.getIdOrganization(),"Marchés " + organizationService.findById( m.getIdOrganization() ).getName() );

           String N0 = hashMapMenuMarcheN0.get( m.getIdOrganization() );
           String N1 = m.getTypeMarche().getGender().getCode();
           String N2 = m.getName();
           String URL = "/marche/info/detail/" + m.getId() ;
           menuMarcheList.add( new MenuMarche( N0, N1, N2, URL ) );
       }

       return menuConstruct( hashMapMenuMarcheN0, menuMarcheList );

   }

    /**
     * Complément de la méthode getMenuMarche
     * @param hashMapMenuMarcheN0 HashMap du menu du niveau 0
     * @param menuMarcheList Liste des marchés
     * @return List<MenuOrganization>
     */
    private List<MenuOrganization> menuConstruct(HashMap< Long , String > hashMapMenuMarcheN0,ArrayList< MenuMarche > menuMarcheList){
        List<MenuOrganization> menuOrganizations = new ArrayList<>();
        for(Map.Entry mEntry: hashMapMenuMarcheN0.entrySet() ){
            String N0 = mEntry.getValue().toString();

            List<String> genders = new ArrayList<>();
            for (MenuMarche m : menuMarcheList )
                if( m.getOrganization().equals( N0 ) )
                    if( !genders.contains( m.getGender() ) )
                        genders.add( m.getGender() );

            List<MenuGender> menuGenders = new ArrayList<>();
            for (String mG : genders  ) {
                List<SimpleMenuMarche> simpleMenuMarches = new ArrayList<>();
                for (MenuMarche m : menuMarcheList )
                    if (m.getOrganization().equals(N0) && m.getGender().equals(mG))
                        simpleMenuMarches.add(new SimpleMenuMarche(m.getName(),m.getUrl() ) );

                menuGenders.add( new MenuGender( mG , simpleMenuMarches ) );
            }
            menuOrganizations.add( new MenuOrganization( N0, menuGenders ) );
        }
        return menuOrganizations;
    }


}
