package muface.application.usecases.diplomas;

import jakarta.transaction.Transactional;
import muface.application.domain.service.DiplomaDTOService;
import muface.arch.command.ArqAbstractUseCaseDeleteList;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BorrarDiplomasUseCase extends ArqAbstractUseCaseDeleteList<String, DiplomaDTO> {

    @Autowired
    private DiplomaDTOService diplomaDTOService;
    @Override
    @Transactional
    public String execute(DiplomaDTO diplomaDTO) {
        return this.diplomaDTOService.borrarEntidades(diplomaDTO);
    }


}
