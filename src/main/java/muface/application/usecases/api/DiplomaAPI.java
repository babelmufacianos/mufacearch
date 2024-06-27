package muface.application.usecases.api;

import muface.application.domain.valueobject.diplomas.DiplomaDTO;
import muface.arch.aspect.ArqUseCaseDefinition;
import muface.arch.aspect.ArqUseCaseType;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "diplomas")
public class DiplomaAPI {
    @ArqUseCaseDefinition(value = "CrearDiplomaUseCase", type = ArqUseCaseType.CREATE)
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody DiplomaDTO dtoInBody) {
        return ResponseEntity.ok("");
    }
    @ArqUseCaseDefinition(value = "ActualizarDiplomaUseCase", type = ArqUseCaseType.UPDATE)
    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody DiplomaDTO dtoInBody) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "BorrarDiplomasUseCase", type = ArqUseCaseType.DELETE)
    @DeleteMapping
    public ResponseEntity<Object> borrar(@RequestBody DiplomaDTO dtoInBody) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "BorrarDiplomaPorIdUseCase", type = ArqUseCaseType.DELETE_BY_ID)
    @DeleteMapping("{id}")
    public ResponseEntity<Object> borrarPorId(@PathVariable Long id) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ConsultaPorIdDiplomasUseCase", type = ArqUseCaseType.QUERY_BY_ID)
    @GetMapping("{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable Long id) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ConsultasDiplomasUseCase", type = ArqUseCaseType.QUERY_BY_PARAMS)
    @PostMapping("consulta")
    public ResponseEntity<Object> consultaPorCampos(@RequestBody DiplomaDTO filter) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ConsultasPaginadasDiplomasUseCase", type = ArqUseCaseType.QUERY_PAGINATED)
    @PostMapping("consulta-paginada")
    public ResponseEntity<Object> consultaPaginadaPorCampos(@RequestBody DiplomaDTO filter, Pageable pageable) {
        return ResponseEntity.ok("");
    }

}

