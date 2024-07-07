package muface.application.usecases.api;

import muface.application.domain.valueobject.customers.CustomerDocumentDTO;
import muface.arch.aspect.ArqUseCaseType;
import muface.arch.aspect.ArqUseCaseDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "customers")
public class CustomerAPI {

    @ArqUseCaseDefinition(value = "CrearCustomerUseCase", type = ArqUseCaseType.CREATE)
    @PostMapping
    public ResponseEntity<Object> insertar(@RequestBody CustomerDocumentDTO dtoInBody) {
        return ResponseEntity.ok("");
    }

    @ArqUseCaseDefinition(value = "ActualizarCustomerUseCase", type = ArqUseCaseType.UPDATE)
    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody CustomerDocumentDTO dtoInBody) {
        return ResponseEntity.ok("");
    }




}
