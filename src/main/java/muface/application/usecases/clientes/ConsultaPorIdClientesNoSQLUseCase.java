package muface.application.usecases.clientes;

import muface.application.domain.service.ClienteService;
import muface.application.domain.valueobject.clientes.ClienteDocumentDTO;
import muface.arch.command.usecase.ArqAbstractUseCaseById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPorIdClientesNoSQLUseCase extends ArqAbstractUseCaseById<ClienteDocumentDTO, String> {

    @Autowired
    private ClienteService clienteService;

    @Override
    public ClienteDocumentDTO execute(String id) {
        return this.clienteService.buscarPorId(id);
    }

}
