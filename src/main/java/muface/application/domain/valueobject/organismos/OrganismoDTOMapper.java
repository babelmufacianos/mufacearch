package muface.application.domain.valueobject.organismos;

import muface.application.domain.model.Organismo;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class OrganismoDTOMapper implements IArqDTOMapper<OrganismoDTO> {

    @Override
    public OrganismoDTO map(Serializable entity) {
        OrganismoDTO organismoDTO = new OrganismoDTO();
        organismoDTO.actualizarDTO((Organismo) entity);
        return organismoDTO;
    }

    @Override
    public OrganismoDTO newInstance() {
        return new OrganismoDTO();
    }

    @Override
    public Organismo getNewInnerInstance() {
        return new Organismo();
    }


}
