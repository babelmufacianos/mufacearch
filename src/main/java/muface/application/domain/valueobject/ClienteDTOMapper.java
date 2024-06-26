package muface.application.domain.valueobject;

import muface.application.domain.model.ClienteDocument;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ClienteDTOMapper implements IArqDTOMapper<Serializable, ClienteDocumentDTO> {
    @Override
    public ClienteDocumentDTO map(Serializable entity) {
        ClienteDocumentDTO diplomaDTO = new ClienteDocumentDTO();
        diplomaDTO.setEntity((ClienteDocument) entity);
        return diplomaDTO;
    }

    @Override
    public ClienteDocumentDTO newInstance() {
        return new ClienteDocumentDTO();
    }

}
