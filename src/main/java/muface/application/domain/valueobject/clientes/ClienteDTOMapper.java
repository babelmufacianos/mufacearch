package muface.application.domain.valueobject.clientes;

import muface.application.domain.model.ClienteDocument;
import muface.arch.command.IArqDTOMapper;
import muface.arch.repository.ArqPortRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ClienteDTOMapper implements IArqDTOMapper<ClienteDocumentDTO> {

    private String typeOfRepoImpl = ArqPortRepository.JPA_IMPL;
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

    @Override
    public void setTypeOfRepoImplementation(String typeOfRepoImpl) {
        this.typeOfRepoImpl = typeOfRepoImpl;
    }

}
