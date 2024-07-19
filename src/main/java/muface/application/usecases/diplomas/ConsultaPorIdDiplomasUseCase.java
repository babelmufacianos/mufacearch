package muface.application.usecases.diplomas;

import muface.application.domain.service.DiplomaService;
import muface.application.domain.valueobject.DiplomaDTO;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ConsultaPorIdDiplomasUseCase extends ArqAbstractUseCase<DiplomaDTO, Serializable> {

    @Autowired
    private DiplomaService diplomaService;


    @Override
    public DiplomaDTO execute(Serializable id) {
        return this.diplomaService.buscarPorId(id);
    }

}
