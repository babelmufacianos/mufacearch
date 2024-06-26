package muface.arch.command.usecase;

import muface.arch.command.IArqCommand;
import muface.arch.service.ArqGenericService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ArqAbstractUseCaseDeleteById<R extends String, Long> implements IArqCommand<R, Long> {

    public abstract String execute(Long id);


    @Override
    public String executeInner(Object entidadDto) {
        return this.execute((Long)entidadDto);
    }


}

