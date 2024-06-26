package muface.application.usecases.diplomasSQL;

import muface.application.domain.service.DiplomaService;
import muface.arch.command.ArqAbstractUseCase;
import muface.application.domain.valueobject.DiplomaDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrearDiplomaUseCase extends ArqAbstractUseCase<DiplomaDTO, DiplomaDTO> {

    @Autowired
    private DiplomaService diplomaService;
    @Override
    @Transactional
    public DiplomaDTO execute(DiplomaDTO diplomaDTO) {
        return (DiplomaDTO) this.diplomaService.insertar(diplomaDTO);
    }

}
