package muface.application.usecases.diplomas;

import muface.application.domain.service.DiplomaService;
import muface.application.domain.valueobject.diplomas.DiplomaDTO;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPorIdDiplomasUseCase extends ArqAbstractUseCase<DiplomaDTO, Long> {

    @Autowired
    private DiplomaService diplomaService;
    @Override
    public DiplomaDTO execute(Long id) {
        return this.diplomaService.buscarPorId(id);
    }

}
