package muface.application.usecases.diplomas;

import jakarta.transaction.Transactional;
import muface.application.domain.service.DiplomaDTOService;
import muface.arch.command.ArqAbstractUseCase;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualizarDiplomaUseCase extends ArqAbstractUseCase<DiplomaDTO, DiplomaDTO> {

    @Autowired
    private DiplomaDTOService diplomaDTOService;
    @Override
    @Transactional
    public DiplomaDTO execute(DiplomaDTO diplomaDTO) {
        return (DiplomaDTO) this.diplomaDTOService.actualizar(diplomaDTO);
    }

}
