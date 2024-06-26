package muface.application.usecases.clientesNoSQL;

import muface.application.domain.service.ClienteService;
import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.command.ArqAbstractUseCasePagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ConsultasPaginadasClientesNoSQLUseCase extends ArqAbstractUseCasePagination<Page<ClienteDocumentDTO>, ClienteDocumentDTO> {
    @Autowired
    private ClienteService clienteService;

    public Page<ClienteDocumentDTO> execute(ClienteDocumentDTO clienteDocumentDTO, Pageable pageable) {
        return this.clienteService.buscarClientesPorTitulacion(clienteDocumentDTO.getTitulacionOficial(), pageable);
    }


}
