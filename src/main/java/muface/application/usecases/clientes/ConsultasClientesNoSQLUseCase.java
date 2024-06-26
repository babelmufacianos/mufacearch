package muface.application.usecases.clientes;

import muface.application.domain.service.ClienteService;
import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.command.usecase.ArqAbstractUseCaseConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultasClientesNoSQLUseCase extends ArqAbstractUseCaseConsulta<List<ClienteDocumentDTO>, ClienteDocumentDTO> {
    @Autowired
    private ClienteService clienteService;

    @Override
    public List<ClienteDocumentDTO> execute(ClienteDocumentDTO clienteDocumentDTO) {
        return this.clienteService.buscarCoincidenciasNoEstricto(clienteDocumentDTO);

    }


}
