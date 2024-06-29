package muface.application.domain.valueobject.diplomas;

import muface.application.domain.model.Diploma;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class DiplomaDTOMapper implements IArqDTOMapper<DiplomaDTO> {

    private String typeOfRepoImpl;

    @Override
    public DiplomaDTO newInstance() {
        return new DiplomaDTO();
    }

    @Override
    public Diploma newInstanceOfEntity() {
        return new Diploma();
    }

    @Override
    public void setTypeOfRepoImplementation(String typeOfRepoImpl) {
        this.typeOfRepoImpl = typeOfRepoImpl;
    }

}


