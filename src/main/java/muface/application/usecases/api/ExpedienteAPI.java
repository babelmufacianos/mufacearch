package muface.application.usecases.api;

import muface.application.domain.valueobject.OrganismoDTO;
import muface.application.domain.valueobject.complexsample.ExpedienteDto;
import muface.arch.aspect.ArqUseCaseDefinition;
import muface.arch.aspect.ArqUseCaseType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping(value = "expedientes")
public class ExpedienteAPI {


    @ArqUseCaseDefinition(value = "CrearExpedienteUseCase", type = ArqUseCaseType.CREATE)
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody ExpedienteDto dtoInBody) {
        return ResponseEntity.ok("");
    }


    @ArqUseCaseDefinition(value = "ActualizarExpedienteUseCase", type = ArqUseCaseType.UPDATE)
    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody ExpedienteDto dtoInBody) {
        return ResponseEntity.ok("");
    }


    @ArqUseCaseDefinition(value = "ConsultaPorIdExpedienteUseCase", type = ArqUseCaseType.QUERY_BY_ID)
    @GetMapping("{id}")
    public ResponseEntity<Object> consultaPorId(@PathVariable Serializable id) {
        return ResponseEntity.ok("");
    }

}
