package muface.application.usecases.clientes;

import jakarta.transaction.Transactional;
import muface.application.domain.service.ClienteService;
import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.command.usecase.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrearClienteNoSQLUseCase extends ArqAbstractUseCase<ClienteDocumentDTO, ClienteDocumentDTO> {
    @Autowired
    private ClienteService clienteService;

    @Override
    @Transactional
    public ClienteDocumentDTO execute(ClienteDocumentDTO clienteDocumentDTO) {
        return this.clienteService.insertar(clienteDocumentDTO);
    }

}
