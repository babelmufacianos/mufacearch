package muface.application.domain.valueobject.diplomas;

import muface.application.domain.model.Diploma;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class DiplomaDTOMapper implements IArqDTOMapper<Diploma, DiplomaDTO> {

    @Override
    public DiplomaDTO map(Diploma entity) {
        DiplomaDTO diplomaDTO = new DiplomaDTO();
        diplomaDTO.setEntity(entity);
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

}
