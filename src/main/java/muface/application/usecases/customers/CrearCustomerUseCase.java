package muface.application.usecases.customers;

import jakarta.transaction.Transactional;
import muface.application.domain.service.CustomerService;
import muface.application.domain.valueobject.CustomerDTO;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrearCustomerUseCase extends ArqAbstractUseCase<CustomerDTO, CustomerDTO> {

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public CustomerDTO execute(CustomerDTO clienteDocumentDTO) {
        return this.customerService.insertar(clienteDocumentDTO);
    }

}
