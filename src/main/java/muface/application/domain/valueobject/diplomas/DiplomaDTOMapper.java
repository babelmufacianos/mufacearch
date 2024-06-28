package muface.application.domain.valueobject.diplomas;

import muface.application.domain.model.Diploma;
import muface.arch.command.IArqDTOMapper;
import muface.arch.repository.ArqPortRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DiplomaDTOMapper implements IArqDTOMapper<DiplomaDTO> {

    private String typeOfRepoImpl = ArqPortRepository.JPA_IMPL;
    @Override
    public DiplomaDTO map(Serializable entity) {
        DiplomaDTO diplomaDTO = new DiplomaDTO();
        diplomaDTO.actualizarDTO((Diploma) entity);
        return diplomaDTO;
    }

    @Override
    public DiplomaDTO newInstance() {
        return new DiplomaDTO();
    }

    @Override
    public Diploma getNewInnerInstance() {
        return new Diploma();
    }

    @Override
    public void setTypeOfRepoImplementation(String typeOfRepoImpl) {
        this.typeOfRepoImpl = typeOfRepoImpl;
    }

}


