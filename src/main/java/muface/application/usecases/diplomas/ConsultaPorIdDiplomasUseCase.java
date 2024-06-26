package muface.application.usecases.diplomas;

import muface.application.domain.service.DiplomaDTOService;
import muface.arch.command.ArqAbstractUseCaseById;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaPorIdDiplomasUseCase extends ArqAbstractUseCaseById<DiplomaDTO, Long> {

    @Autowired
    private DiplomaDTOService diplomaDTOService;
    @Override
    public DiplomaDTO execute(Long id) {
        return (DiplomaDTO) this.diplomaDTOService.buscarPorId(id);
    }

}
