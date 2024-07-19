package muface.application.usecases.api;

import muface.application.domain.valueobject.OrganismoDTO;
import muface.arch.aspect.ArqUseCaseDefinition;
import muface.arch.aspect.ArqUseCaseType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "organismos")
public class OrganismoAPI {


    @ArqUseCaseDefinition(value = "CrearOrganismoUseCase", type = ArqUseCaseType.CREATE)
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody OrganismoDTO dtoInBody) {
        return ResponseEntity.ok("");
    }


    @ArqUseCaseDefinition(value = "ActualizarOrganismoUseCase", type = ArqUseCaseType.UPDATE)
    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody OrganismoDTO dtoInBody) {
        return ResponseEntity.ok("");
    }

}
