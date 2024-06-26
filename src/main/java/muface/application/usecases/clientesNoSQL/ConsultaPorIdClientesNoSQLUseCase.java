package muface.application.usecases.clientesNoSQL;

import muface.application.domain.service.ClienteService;
import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.command.ArqAbstractUseCaseById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPorIdClientesNoSQLUseCase extends ArqAbstractUseCaseById<ClienteDocumentDTO, String> {

    @Autowired
    private ClienteService clienteService;

    @Override
    public ClienteDocumentDTO execute(String id) {
        return (ClienteDocumentDTO) this.clienteService.buscarPorId(id);
    }

}
