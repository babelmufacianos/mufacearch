package muface.application.domain.valueobject.clientes;

import muface.application.domain.model.ClienteDocument;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteDTOMapper implements IArqDTOMapper<ClienteDocumentDTO> {

    private String typeOfRepoImpl;

    @Override
    public ClienteDocumentDTO nuevaInstancia() {
        return new ClienteDocumentDTO();
    }

    @Override
    public ClienteDocument nuevaInstanciaEntidad() {
        return new ClienteDocument();
    }

    @Override
    public void setJPAONoSQL(String typeOfRepoImpl) {
        this.typeOfRepoImpl = typeOfRepoImpl;
    }

}
