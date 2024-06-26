package muface.application.usecases.diplomasSQL;

import jakarta.transaction.Transactional;
import muface.application.domain.service.DiplomaService;
import muface.arch.command.ArqAbstractUseCase;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualizarDiplomaUseCase extends ArqAbstractUseCase<DiplomaDTO, DiplomaDTO> {

    @Autowired
    private DiplomaService diplomaService;
    @Override
    @Transactional
    public DiplomaDTO execute(DiplomaDTO diplomaDTO) {
        return (DiplomaDTO) this.diplomaService.actualizar(diplomaDTO);
    }

}