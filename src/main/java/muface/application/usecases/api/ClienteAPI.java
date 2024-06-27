package muface.application.usecases.api;

import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.aspect.ArqUseCaseType;
import muface.arch.aspect.ArqUseCaseDefinition;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "clientes")
public class ClienteAPI {
    @ArqUseCaseDefinition(value = "CrearClienteNoSQLUseCase", type = ArqUseCaseType.CREATE)
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody ClienteDocumentDTO dtoInBody) {
        return ResponseEntity.ok("");
    }
    @ArqUseCaseDefinition(value = "ActualizarClienteNoSQLUseCase", type = ArqUseCaseType.UPDATE)
    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody ClienteDocumentDTO dtoInBody) {
        return ResponseEntity.ok("");
    }
    @ArqUseCaseDefinition(value = "BorrarClientesNoSQLUseCase", type = ArqUseCaseType.DELETE)
    @DeleteMapping
    public ResponseEntity<Object> borrar(@RequestBody ClienteDocumentDTO dtoInBody) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "BorrarClienteNoSQLPorIdUseCase", type = ArqUseCaseType.DELETE_BY_ID)
    @DeleteMapping("{id}")
    public ResponseEntity<Object> borrarPorId(@PathVariable String id) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ConsultaPorIdClientesNoSQLUseCase", type = ArqUseCaseType.QUERY_BY_ID)
    @GetMapping("{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable String id) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ConsultasClientesNoSQLUseCase", type = ArqUseCaseType.QUERY_BY_PARAMS)
    @PostMapping("consulta")
    public ResponseEntity<Object> consultaPorCampos(@RequestBody ClienteDocumentDTO filter) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ConsultasPaginadasClientesNoSQLUseCase", type = ArqUseCaseType.QUERY_PAGINATED)
    @PostMapping("consulta-paginada")
    public ResponseEntity<Object> consultaPaginadaPorCampos(@RequestBody ClienteDocumentDTO filter, Pageable pageable) {
        return ResponseEntity.ok("");
    }

    /** pesonalized endpoints **/

    @PostMapping("bienvenido")
    public ResponseEntity<Object> saludar(@RequestParam String login, @RequestParam String password) {
        // Consultar un sistema de aautenticaciçon externo, etc
        return ResponseEntity.ok("Binvenido");
    }


}
