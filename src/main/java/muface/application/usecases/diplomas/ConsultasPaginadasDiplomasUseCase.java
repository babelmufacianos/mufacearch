package muface.application.usecases.diplomas;

import muface.arch.command.ArqAbstractUseCase;
import muface.application.domain.valueobject.diplomas.DiplomaDTO;
import muface.application.domain.service.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ConsultasPaginadasDiplomasUseCase extends ArqAbstractUseCase<Page<DiplomaDTO>, DiplomaDTO> {

    @Autowired
    private DiplomaService diplomaService;

    public Page<DiplomaDTO> execute(DiplomaDTO diplomaDTO) {
        return diplomaDTO.getTitulacion() == null ? this.diplomaService.buscarTodosPaginados(diplomaDTO.getPageable())
        : this.diplomaService.buscarDiplomasPorNombreDeTitulacion(diplomaDTO.getTitulacion(), diplomaDTO.getPageable());
    }


}
