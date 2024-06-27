package muface.application.usecases.diplomas;

import muface.application.domain.service.DiplomaService;
import muface.arch.command.usecase.ArqAbstractUseCaseById;
import muface.application.domain.valueobject.diplomas.DiplomaDTO;
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
