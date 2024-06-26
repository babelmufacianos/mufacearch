package muface.application.usecases.diplomas;

import jakarta.transaction.Transactional;
import muface.application.domain.service.DiplomaService;
import muface.arch.command.usecase.ArqAbstractUseCaseDeleteList;
import muface.application.domain.valueobject.DiplomaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BorrarDiplomasUseCase extends ArqAbstractUseCaseDeleteList<String, DiplomaDTO> {

    @Autowired
    private DiplomaService diplomaService;
    @Override
    @Transactional
    public String execute(DiplomaDTO diplomaDTO) {
        return this.diplomaService.borrarEntidades(diplomaDTO);
    }


}
