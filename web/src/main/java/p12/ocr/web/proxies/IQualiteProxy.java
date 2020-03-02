package p12.ocr.web.proxies;

import p12.ocr.web.beans.qualite.BulletinBean;
import p12.ocr.web.beans.qualite.MarcheQualiteBean;
import p12.ocr.web.beans.qualite.PortageBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server",contextId = "qualiteProxy")
@RibbonClient(name = "microservice-qualite")
@RequestMapping("/microservice-qualite")
public interface IQualiteProxy {

    /* Bulletin */
    @GetMapping("/bulletin/find/{id}")
    BulletinBean findBulletinById(@PathVariable("id") Long id);

    @GetMapping("/bulletin/all")
    List<BulletinBean> findAllBulletin();

    @PostMapping("/bulletin/save")
    BulletinBean saveBulletin(@RequestBody BulletinBean bulletin);

    @PutMapping("/bulletin/update")
    void updateBulletin(@RequestBody BulletinBean bulletin);

    /* Marche */
    @GetMapping("/marche/find/{id}")
    MarcheQualiteBean findMarcheById(@PathVariable("id") Long id);

    @GetMapping("/marche/all")
    List<MarcheQualiteBean> findAllMarche();

    @PostMapping("/marche/save")
    MarcheQualiteBean saveMarche(@RequestBody MarcheQualiteBean marche);

    @PutMapping("/marche/update")
    void updateMarche(@RequestBody MarcheQualiteBean marche);

    /* Portage */
    @GetMapping("/portage/find/{id}")
    PortageBean findPortageById(@PathVariable("id") Long id);

    @GetMapping("/portage/all")
    List<PortageBean> findAllPortage();

    @PostMapping("/portage/save")
    PortageBean savePortage(@RequestBody PortageBean portage);

    @PutMapping("/portage/update")
    void updatePortage(@RequestBody PortageBean portage);

}
