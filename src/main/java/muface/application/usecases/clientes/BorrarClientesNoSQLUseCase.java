package muface.application.usecases.clientes;

import jakarta.transaction.Transactional;
import muface.application.domain.service.ClienteService;
import muface.application.domain.valueobject.clientes.ClienteDocumentDTO;
import muface.arch.command.usecase.ArqAbstractUseCaseDeleteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BorrarClientesNoSQLUseCase extends ArqAbstractUseCaseDeleteList<String, ClienteDocumentDTO> {

    @Autowired
    private ClienteService clienteService;

    @Override
    @Transactional
    public String execute(ClienteDocumentDTO clienteDocumentDTO) {
        return this.clienteService.borrarEntidades(clienteDocumentDTO);
    }


}
