package muface.application.usecases.diplomas;

import muface.application.domain.service.DiplomaService;
import muface.application.domain.valueobject.diplomas.DiplomaDTO;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultasDiplomasUseCase extends ArqAbstractUseCase<List<DiplomaDTO>, DiplomaDTO> {

    @Autowired
    private DiplomaService diplomaService;
    @Override
    public List<DiplomaDTO> execute(DiplomaDTO diplomaDTOFilter) {
        return this.diplomaService.buscarCoincidenciasNoEstricto(diplomaDTOFilter);
    }


}
