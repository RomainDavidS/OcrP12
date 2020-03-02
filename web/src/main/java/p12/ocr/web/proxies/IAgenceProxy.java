package p12.ocr.web.proxies;

import p12.ocr.web.beans.agence.ManagementVisuelBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server",contextId = "agenceProxy")
@RibbonClient(name = "microservice-agence")
@RequestMapping("/microservice-agence")
public interface IAgenceProxy {

    /* ManagementVisuel */
    @GetMapping("/managementVisuel/find/{id}")
    ManagementVisuelBean findManagementVisuelById(@PathVariable("id") String id);

    @GetMapping("/managementVisuel/all")
    List<ManagementVisuelBean> findAllManagementVisuel();

    @PostMapping("/managementVisuel/save")
    ManagementVisuelBean saveManagementVisuel(@RequestBody ManagementVisuelBean managementVisuel);

    @PutMapping("/managementVisuel/update")
    void updateManagementVisuel(@RequestBody ManagementVisuelBean managementVisuel);


}
