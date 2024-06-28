package muface.application.domain.valueobject.clientes;

import muface.application.domain.model.ClienteDocument;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteDTOMapper implements IArqDTOMapper<ClienteDocumentDTO> {

    private String typeOfRepoImpl;

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
