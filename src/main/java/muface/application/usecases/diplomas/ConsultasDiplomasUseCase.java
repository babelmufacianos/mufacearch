package muface.application.usecases.diplomas;

import muface.application.domain.service.DiplomaDTOService;
import muface.arch.command.ArqAbstractUseCaseConsulta;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultasDiplomasUseCase extends ArqAbstractUseCaseConsulta<List<DiplomaDTO>, DiplomaDTO> {

    @Autowired
    private DiplomaDTOService diplomaDTOService;
    @Override
    public List<DiplomaDTO> execute(DiplomaDTO diplomaDTOFilter) {
        return this.diplomaDTOService.buscarCoincidenciasNoEstricto(diplomaDTOFilter);

    }


}
