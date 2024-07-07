package muface.application.domain.valueobject.diplomas;

import muface.application.domain.model.Diploma;
import muface.arch.command.ArqAbstractDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class DiplomaDTOMapper extends ArqAbstractDTOMapper<DiplomaDTO> {

    @Override
    public DiplomaDTO nuevaInstancia() {
        return new DiplomaDTO();
    }

    @Override
    public Diploma nuevaInstanciaEntidad() {
        return new Diploma();
    }


}


