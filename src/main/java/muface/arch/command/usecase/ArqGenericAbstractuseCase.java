package muface.arch.command.usecase;

import muface.arch.command.IArqCommand;

public abstract class ArqGenericAbstractuseCase<R, P> implements IArqCommand<R, P> {

    public abstract R execute(P filter);


    @Override
    public R executeInner(P params) {
        return (R) execute((P)params);
    }

}
