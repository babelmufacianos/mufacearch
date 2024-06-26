package muface.application.usecases.api;

import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.controller.ArqBaseRestController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody ClienteDocumentDTO dtoInBody) { // usaríamos la Entidad no el DTO
        return this.executeUseCaseWithInputBody(getCasoUsoInsercion(), dtoInBody);
    }

    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody ClienteDocumentDTO dtoInBody) { // usaríamos la Entidad no el DTO
        return this.executeUseCaseWithInputBody(getCasoUsoModificacion(), dtoInBody);
    }

    @DeleteMapping
    public ResponseEntity<Object> borrarAll() {
        return this.executeUseCaseWithInputBody(getCasoUsoBorrado(), null);
    }

    @PostMapping("borrarSeleccion")
    public ResponseEntity<Object> borrarSeleccion(@RequestBody ClienteDocumentDTO dtoInBody) {
        return this.executeUseCaseWithInputBody(getCasoUsoBorrado(), dtoInBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> borrarPorId(@PathVariable ClienteDocumentDTO id) {
        return this.executeUseCaseById(getCasoUsoBorradoPorId(), id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable String id) {
        return this.executeUseCaseById(getCasoUsoConsultaPorId(), id);
    }

    @PostMapping("consulta")
    public ResponseEntity<Object> consulta(@RequestBody ClienteDocumentDTO dtoInBody) { // usaríamos la Entidad no el DTO
        return this.executeUseCaseWithInputBody(getCasoUsoConsultaGeneral(), dtoInBody);
    }

    @PostMapping("consulta-paginada")
    public ResponseEntity<Object> consultapaginados(@RequestBody ClienteDocumentDTO dtoInBody, Pageable pageable) {
        return this.executeUseQueryPagination(getCasoUsoConsultaPaginada(), dtoInBody, pageable);
    }

    /** pesonalized endpoints **/

    @PostMapping("bienvenido")
    public ResponseEntity<Object> saludar(@RequestParam String param1, @RequestParam String param2) {
        return this.executeUseCaseWithReqParams("CasoUsoSaludoEspecial", new Object[]{param1, param2});
    }


}
