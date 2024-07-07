package muface.application.domain.valueobject.customers;

import muface.application.domain.model.CustomerDocument;
import muface.arch.command.ArqAbstractDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOMapper extends ArqAbstractDTOMapper<CustomerDocumentDTO> {

    @Override
    public CustomerDocumentDTO nuevaInstancia() {
        return new CustomerDocumentDTO();
    }

    @Override
    public CustomerDocument nuevaInstanciaEntidad() {
        return new CustomerDocument();
    }

}
