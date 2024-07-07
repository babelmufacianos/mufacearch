package muface.application.usecases.customers;

import jakarta.transaction.Transactional;
import muface.application.domain.service.CustomerService;
import muface.application.domain.valueobject.customers.CustomerDocumentDTO;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualizarCustomerUseCase extends ArqAbstractUseCase<CustomerDocumentDTO, CustomerDocumentDTO> {

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public CustomerDocumentDTO execute(CustomerDocumentDTO clienteDocumentDTO) {
        return this.customerService.actualizar(clienteDocumentDTO);
    }

}
