package muface.application.domain.service;

import muface.application.domain.model.complexsample.Expediente;
import muface.application.domain.repository.ExpedienteRepository;
import muface.application.domain.valueobject.complexsample.ExpedienteDto;
import muface.arch.service.ArqGenericService;
import org.springframework.stereotype.Service;

@Service
public class ExpedienteService extends ArqGenericService<ExpedienteDto, Expediente> {

    public ExpedienteService(ExpedienteRepository repo) {
        super(repo);
    }

}
