package muface.application.domain.valueobject.diplomas;

import muface.application.domain.model.Diploma;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DiplomaDTOMapper implements IArqDTOMapper<DiplomaDTO> {

    @Override
    public DiplomaDTO map(Serializable entity) {
        DiplomaDTO diplomaDTO = new DiplomaDTO();
        diplomaDTO.setEntity((Diploma) entity);
        return diplomaDTO;
    }

    @Override
    public DiplomaDTO newInstance() {
        return new DiplomaDTO();
    }

    @Override
    public Serializable getNewInnerInstance() {
        return new Diploma();
    }

}


