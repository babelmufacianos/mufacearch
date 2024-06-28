package muface.application.domain.valueobject.clientes;

import muface.application.domain.model.ClienteDocument;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ClienteDTOMapper implements IArqDTOMapper<ClienteDocumentDTO> {
    @Override
    public ClienteDocumentDTO map(Serializable entity) {
        ClienteDocumentDTO diplomaDTO = new ClienteDocumentDTO();
        diplomaDTO.actualizarDTO((ClienteDocument) entity);
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
