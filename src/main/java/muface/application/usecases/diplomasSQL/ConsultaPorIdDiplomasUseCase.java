package muface.application.usecases.diplomasSQL;

import muface.application.domain.service.DiplomaService;
import muface.arch.command.ArqAbstractUseCaseById;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPorIdDiplomasUseCase extends ArqAbstractUseCaseById<DiplomaDTO, Long> {

    @Autowired
    private DiplomaService diplomaService;
    @Override
    public DiplomaDTO execute(Long id) {
        return this.diplomaService.buscarPorId(id);
    }

}
