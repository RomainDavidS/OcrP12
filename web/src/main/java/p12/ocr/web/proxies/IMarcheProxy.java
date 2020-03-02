package p12.ocr.web.proxies;

import p12.ocr.web.beans.marche.IndemnisationBean;
import p12.ocr.web.beans.marche.MarcheBean;
import p12.ocr.web.beans.marche.TypeMarcheBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server",contextId = "marcheProxy")
@RibbonClient(name = "microservice-marche")
@RequestMapping("/microservice-marche")
public interface IMarcheProxy {

    /* Marche */
    @GetMapping("/marche/find/{id}")
    MarcheBean findMarcheById(@PathVariable("id") Long id);

    @GetMapping("/marche/all")
    List<MarcheBean> findAllMarche();

    @PostMapping("/marche/save")
    MarcheBean saveMarche( @RequestBody MarcheBean marche);

    @PutMapping("/marche/update")
    void updateMarche( @RequestBody MarcheBean marche);

    /* TypeMarche */
    @GetMapping("/typeMarche/find/{id}")
    TypeMarcheBean findTypeMarcheById(@PathVariable("id") Long id);

    @GetMapping("/typeMarche/all")
    List<TypeMarcheBean> findAllTypeMarche();

    @PostMapping("/typeMarche/save")
    TypeMarcheBean saveTypeMarche( @RequestBody TypeMarcheBean typeMarche);

    @PutMapping("/typeMarche/update")
    void updateTypeMarche(@RequestBody TypeMarcheBean typeMarche);

    /* Indemnisation */
    @GetMapping("/indemnisation/find/{id}")
    IndemnisationBean findIndemnisationById(@PathVariable("id") Long id);

    @GetMapping("/indemnisation/all")
    List<IndemnisationBean> findAllIndemnisation();

    @PostMapping("/indemnisation/save")
    IndemnisationBean saveIndemnisation(@RequestBody IndemnisationBean indemnisation);

    @PutMapping("/indemnisation/update")
    void updateIndeminisation(@RequestBody IndemnisationBean indemnisation);
}
