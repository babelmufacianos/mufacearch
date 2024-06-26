package muface.application.usecases.diplomas;


import jakarta.transaction.Transactional;
import muface.application.domain.service.DiplomaDTOService;
import muface.arch.command.ArqAbstractUseCaseDeleteById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BorrarDiplomaPorIdUseCase extends ArqAbstractUseCaseDeleteById<String, Long> {

    @Autowired
    private DiplomaDTOService diplomaDTOService;
    @Override
    @Transactional
    public String execute(Long id) {
        return this.diplomaDTOService.borrarEntidad(id);
    }


}
