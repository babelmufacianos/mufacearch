package muface.application.usecases.diplomas;

import muface.arch.command.ArqAbstractUseCasePagination;
import muface.application.domain.valueobject.DiplomaDTO;
import muface.application.domain.service.DiplomaDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ConsultasPaginadasDiplomasUseCase extends ArqAbstractUseCasePagination<Page<DiplomaDTO>, DiplomaDTO> {

    @Autowired
    private DiplomaDTOService diplomaDTOService;

    public Page<DiplomaDTO> execute(DiplomaDTO diplomaDTO, Pageable pageable) {
        return (diplomaDTO.getTitulacion() != null && !diplomaDTO.getTitulacion().isEmpty())
                ? this.diplomaDTOService.buscarDiplomasPorNombreDeTitulacion(diplomaDTO.getTitulacion(), pageable)
                : this.diplomaDTOService.buscarTodosPaginados(pageable);
    }


}
