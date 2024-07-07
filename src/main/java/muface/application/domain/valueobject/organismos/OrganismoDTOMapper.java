package muface.application.domain.valueobject.organismos;

import muface.application.domain.model.IOrganismo;
import muface.application.domain.model.OrganismoNoSQL;
import muface.application.domain.model.OrganismoRel;
import muface.arch.command.ArqAbstractDTOMapper;
import org.springframework.stereotype.Component;


@Component
public class OrganismoDTOMapper extends ArqAbstractDTOMapper<OrganismoDTO> {

    @Override
    public OrganismoDTO nuevaInstancia() {
        return new OrganismoDTO();
    }

    @Override
    public IOrganismo nuevaInstanciaEntidad() {
        if (getTypeOfRepoImpl().contentEquals(JPA_TYPE)) {
            return new OrganismoRel();
        } else {
            return new OrganismoNoSQL();
        }
    }



}
