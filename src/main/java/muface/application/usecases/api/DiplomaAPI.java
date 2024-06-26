package muface.application.usecases.api;

import muface.application.domain.valueobject.DiplomaDTO;
import muface.arch.controller.ArqBaseRestController;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "diplomas")
public class DiplomaAPI extends ArqBaseRestController {

    /**
     * @return
     * Devuelve el valor de la propiedad del application.yml, use-cases:  diplomas   para esta API
     */
    @Override
    protected String getPrefix() {
        return "diplomas";
    }

    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody DiplomaDTO dtoInBody) { // usaríamos la Entidad no el DTO
        return this.executeCreateUseCaseWithInputBody(getCasoUsoInsercion(), dtoInBody);
    }

    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody DiplomaDTO dtoInBody) { // usaríamos la Entidad no el DTO
        return this.executeCreateUseCaseWithInputBody(getCasoUsoModificacion(), dtoInBody);
    }

    @DeleteMapping
    public ResponseEntity<Object> borrarAll() {
        return this.executeCreateUseCaseWithInputBody(getCasoUsoBorrado(), null);
    }

    @PostMapping("borrarSeleccion")
    public ResponseEntity<Object> borrarSeleccion(@RequestBody DiplomaDTO dtoInBody) {
        return this.executeCreateUseCaseWithInputBody(getCasoUsoBorrado(), dtoInBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> borrarPorId(@PathVariable DiplomaDTO id) {
        return this.executeUseCaseById(getCasoUsoBorradoPorId(), id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable String id) {
        return this.executeUseCaseById(getCasoUsoConsultaPorId(), id);
    }

    @PostMapping("consulta")
    public ResponseEntity<Object> consulta(@RequestBody DiplomaDTO dtoInBody) { // usaríamos la Entidad no el DTO
        return this.executeCreateUseCaseWithInputBody(getCasoUsoConsultaGeneral(), dtoInBody);
    }

    @PostMapping("consulta-paginada")
    public ResponseEntity<Object> consultapaginados(@RequestBody DiplomaDTO dtoInBody, Pageable pageable) {
        return this.executeUseQueryPagination(getCasoUsoConsultaPaginada(), dtoInBody, pageable);
    }

    /** pesonalized endpoints **/

    @PostMapping("saludar")
    public ResponseEntity<Object> saludar(@RequestParam String param1, @RequestParam String param2) {
        return this.executeUseCaseWithReqParams("CasoUsoSaludoEspecial", new Object[]{param1, param2});
    }


}
