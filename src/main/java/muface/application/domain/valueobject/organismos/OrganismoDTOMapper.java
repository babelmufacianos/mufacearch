package muface.application.domain.valueobject.organismos;

import muface.application.domain.model.IOrganismo;
import muface.application.domain.model.OrganismoNoSQL;
import muface.application.domain.model.OrganismoRel;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;


@Component
public class OrganismoDTOMapper implements IArqDTOMapper<OrganismoDTO> {

    private String typeOfRepoImpl;

    @Override
    public OrganismoDTO nuevaInstancia() {
        return new OrganismoDTO();
    }

    @Override
    public IOrganismo nuevaInstanciaEntidad() {
        if (typeOfRepoImpl.contentEquals(JPA_TYPE)) {
            return new OrganismoRel();
        } else {
            return new OrganismoNoSQL();
        }
    }

    @Override
    public void setJPAONoSQL(String typeOfRepoImpl) {
        this.typeOfRepoImpl = typeOfRepoImpl;
    }


}
