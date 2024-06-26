package muface.application.usecases.clientesNoSQL;

import jakarta.transaction.Transactional;
import muface.application.domain.service.ClienteService;
import muface.arch.command.usecase.ArqAbstractUseCaseDeleteById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BorrarClienteNoSQLPorIdUseCase extends ArqAbstractUseCaseDeleteById<String, String> {

    @Autowired
    private ClienteService clienteService;

    @Override
    @Transactional
    public String execute(String id) {
        return this.clienteService.borrarEntidad(id);
    }


}
