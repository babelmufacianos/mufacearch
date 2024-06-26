package muface.arch.command.usecase;

import muface.arch.command.IArqCommand;
import muface.arch.command.IArqDTO;

public abstract class ArqAbstractUseCase<R extends IArqDTO, P extends IArqDTO> implements IArqCommand {

    public abstract R execute(P params);

    @Override
    public Object executeInner(Object entidadDto) {
        return this.execute((P)entidadDto);
    }


}

