package muface.application.usecases.diplomas;

import muface.arch.command.usecase.ArqAbstractUseCasePagination;
import muface.application.domain.valueobject.diplomas.DiplomaDTO;
import muface.application.domain.service.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ConsultasPaginadasDiplomasUseCase extends ArqAbstractUseCasePagination<Page<DiplomaDTO>, DiplomaDTO> {

    @Autowired
    private DiplomaService diplomaService;

    public Page<DiplomaDTO> execute(DiplomaDTO diplomaDTO, Pageable pageable) {
        return diplomaDTO.getTitulacion() == null ? this.diplomaService.buscarTodosPaginados(pageable) : this.diplomaService.buscarDiplomasPorNombreDeTitulacion(diplomaDTO.getTitulacion(), pageable);
    }


}
