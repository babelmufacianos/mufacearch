package muface.arch.command.usecase;

import muface.arch.command.IArqCommand;
import muface.arch.command.IArqDTO;
import muface.arch.service.ArqGenericService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ArqAbstractUseCaseDeleteList<R extends String, P extends IArqDTO> implements IArqCommand<R, P> {

    public abstract R execute(P filter);


    @Override
    public R executeInner(P params) {
        return (R) execute((P)params);
    }

}

