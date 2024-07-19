package muface.application.usecases.expedientes;

import muface.application.domain.service.ExpedienteService;
import muface.application.domain.valueobject.complexsample.ExpedienteDto;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ConsultaPorIdExpedienteUseCase extends ArqAbstractUseCase<ExpedienteDto, Serializable> {

    @Autowired
    private ExpedienteService expedienteService;


    @Override
    public ExpedienteDto execute(Serializable id) {
        return this.expedienteService.buscarPorId(id);
    }

}
