package muface.application.usecases.diplomasSQL;


import jakarta.transaction.Transactional;
import muface.application.domain.service.DiplomaService;
import muface.arch.command.usecase.ArqAbstractUseCaseDeleteById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BorrarDiplomaPorIdUseCase extends ArqAbstractUseCaseDeleteById<String, Long> {

    @Autowired
    private DiplomaService diplomaService;
    @Override
    @Transactional
    public String execute(Long id) {
        return this.diplomaService.borrarEntidad(id);
    }


}
