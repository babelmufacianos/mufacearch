package muface.application.domain.valueobject.diplomas;

import muface.application.domain.model.DiplomaDocument;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class DiplomaDocumentDTOMapper implements IArqDTOMapper<DiplomaDocument, DiplomaDTO> {

    @Override
    public DiplomaDTO map(DiplomaDocument entity) {
        DiplomaDTO diplomaDTO = new DiplomaDTO();
        diplomaDTO.setEntity(entity);
        return diplomaDTO;
    }

    @Override
    public DiplomaDTO newInstance() {
        return new DiplomaDTO();
    }


    @Override
    public DiplomaDocument getNewInnerInstance() {
        return new DiplomaDocument();
    }

}
