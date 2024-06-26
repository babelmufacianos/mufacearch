package muface.application.usecases.diplomas;

import muface.arch.command.ArqAbstractUseCaseConsulta;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultasDiplomasUseCase extends ArqAbstractUseCaseConsulta<List<DiplomaDTO>, DiplomaDTO> {

    @Override
    public List<DiplomaDTO> execute(DiplomaDTO diplomaDTOFilter) {
        return (diplomaDTOFilter.getNombreCompleto() != null && !diplomaDTOFilter.getNombreCompleto().isEmpty())
                ? this.commandService.buscarCoincidenciasNoEstricto(diplomaDTOFilter) :
                  this.commandService.buscarTodos();
    }


}
