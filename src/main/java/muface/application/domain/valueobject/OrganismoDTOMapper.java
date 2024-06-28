package muface.application.domain.valueobject;

import muface.application.domain.model.Organismo;
import muface.application.domain.valueobject.clientes.ClienteDocumentDTO;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class OrganismoDTOMapper implements IArqDTOMapper<OrganismoDTO> {

    @Override
    public OrganismoDTO map(Serializable entity) {
        OrganismoDTO organismoDTO = new OrganismoDTO();
        organismoDTO.setEntity((Organismo) entity);
        return organismoDTO;
    }

    @Override
    public OrganismoDTO newInstance() {
        return new OrganismoDTO();
    }

    @Override
    public Serializable getNewInnerInstance() {
        return new Organismo();
    }


}
