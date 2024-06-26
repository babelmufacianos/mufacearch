package muface.application.usecases.api;

import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.command.usecase.ArqUseCaseType;
import muface.arch.controller.ArqBaseRestController;
import muface.arch.controller.ArqUseCaseDefinition;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "clientes")
public class ClienteAPI extends ArqBaseRestController {

    @ArqUseCaseDefinition(value = "CrearClienteNoSQLUseCase", type = ArqUseCaseType.CREATE)
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody ClienteDocumentDTO dtoInBody) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ActualizarClienteNoSQLUseCase", type = ArqUseCaseType.UPDATE)
    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody ClienteDocumentDTO dtoInBody) {
        return null;
    }

    @ArqUseCaseDefinition(value = "BorrarClientesNoSQLUseCase", type = ArqUseCaseType.DELETE)
    @DeleteMapping
    public ResponseEntity<Object> borrar(@RequestBody ClienteDocumentDTO dtoInBody) {
        return null;
    }

    @ArqUseCaseDefinition(value = "BorrarClienteNoSQLPorIdUseCase", type = ArqUseCaseType.DELETE_BY_ID)
    @DeleteMapping("{id}")
    public ResponseEntity<Object> borrarPorId(@PathVariable String id) {
        return null;
    }

    @ArqUseCaseDefinition(value = "ConsultaPorIdClientesNoSQLUseCase", type = ArqUseCaseType.QUERY_BY_ID)
    @GetMapping("{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable String id) {
        return null;
    }

    @ArqUseCaseDefinition(value = "ConsultasClientesNoSQLUseCase", type = ArqUseCaseType.QUERY_BY_PARAMS)
    @PostMapping("consulta")
    public ResponseEntity<Object> consultaPorCampos(@RequestBody ClienteDocumentDTO filter) {
        return null;
    }

    @ArqUseCaseDefinition(value = "ConsultasPaginadasClientesNoSQLUseCase", type = ArqUseCaseType.QUERY_PAGINATED)
    @PostMapping("consulta-paginada")
    public ResponseEntity<Object> consultaPaginadaPorCampos(@RequestBody ClienteDocumentDTO filter, Pageable pageable) {
        return null;
    }
    /** pesonalized endpoints **/

    @PostMapping("bienvenido")
    public ResponseEntity<Object> saludar(@RequestParam String param1, @RequestParam String param2) {
        return this.executeUseCaseWithReqParams("CasoUsoSaludoEspecial", new Object[]{param1, param2});
    }


}
