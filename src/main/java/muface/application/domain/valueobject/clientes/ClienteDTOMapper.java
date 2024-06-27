package muface.application.domain.valueobject.clientes;

import muface.application.domain.model.ClienteDocument;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteDTOMapper implements IArqDTOMapper<ClienteDocument, ClienteDocumentDTO> {
    @Override
    public ClienteDocumentDTO map(ClienteDocument entity) {
        ClienteDocumentDTO diplomaDTO = new ClienteDocumentDTO();
        diplomaDTO.setEntity(entity);
        return diplomaDTO;
    }

    @Override
    public ClienteDocumentDTO newInstance() {
        return new ClienteDocumentDTO();
    }

    @Override
    public ClienteDocument getNewInnerInstance() {
        return new ClienteDocument();
    }

}
