package muface.application.domain.valueobject.organismos;

import muface.application.domain.model.IOrganismo;
import muface.application.domain.model.OrganismoNoSQL;
import muface.application.domain.model.OrganismoRel;
import muface.arch.command.IArqDTOMapper;
import muface.arch.repository.ArqPortRepository;
import org.springframework.stereotype.Component;


@Component
public class OrganismoDTOMapper implements IArqDTOMapper<OrganismoDTO> {

    private String typeOfRepoImpl = ArqPortRepository.JPA_IMPL;

    @Override
    public OrganismoDTO newInstance() {
        return new OrganismoDTO();
    }

    @Override
    public IOrganismo getNewInnerInstance() {
        if (typeOfRepoImpl.contentEquals(ArqPortRepository.JPA_IMPL)) {
            return new OrganismoRel();
        } else {
            return new OrganismoNoSQL();
        }
    }

    @Override
    public void setTypeOfRepoImplementation(String typeOfRepoImpl) {
        this.typeOfRepoImpl = typeOfRepoImpl;
    }


}
