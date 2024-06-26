package muface.application.usecases.api;

import muface.arch.controller.ArqBaseRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "clientes")
public class ClienteAPI extends ArqBaseRestController {

    /**
     * @return
     * Devuelve el valor de la propiedad del application.yml, use-cases:  diplomas   para esta API
     */
    @Override
    protected String getPrefix() {
        return "clientes";
    }

    /** pesonalized endpoints **/

    @PostMapping("bienvenido")
    public ResponseEntity<Object> saludar(@RequestParam String param1, @RequestParam String param2) {
        return this.executeUseCaseWithReqParams("CasoUsoSaludoEspecial", new Object[]{param1, param2});
    }


}
