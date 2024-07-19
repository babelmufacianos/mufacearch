package muface.application.usecases.api;

import muface.application.domain.valueobject.CustomerDTO;
import muface.arch.aspect.ArqUseCaseType;
import muface.arch.aspect.ArqUseCaseDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "customers")
public class CustomerAPI {

    @ArqUseCaseDefinition(value = "CrearCustomerUseCase", type = ArqUseCaseType.CREATE)
    @PostMapping
    public ResponseEntity<Object> insertar(@RequestBody CustomerDTO dtoInBody) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ActualizarCustomerUseCase", type = ArqUseCaseType.UPDATE)
    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody CustomerDTO dtoInBody) {
        return ResponseEntity.ok("");
    }




}
