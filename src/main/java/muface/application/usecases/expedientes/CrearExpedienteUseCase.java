package muface.application.usecases.expedientes;

import jakarta.transaction.Transactional;
import muface.application.domain.service.ExpedienteService;
import muface.application.domain.valueobject.complexsample.ExpedienteDto;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrearExpedienteUseCase extends ArqAbstractUseCase<ExpedienteDto, ExpedienteDto> {

    @Autowired
    private ExpedienteService expedienteService;

    @Override
    @Transactional
    public ExpedienteDto execute(ExpedienteDto expedienteDto) {
        return this.expedienteService.insertar(expedienteDto);
    }

}
