package muface.arch.command;

public abstract class ArqAbstractUseCase<R, P> implements IArqCommand {

    public abstract R execute(P params);

    @Override
    public Object executeInner(Object entidadDto) {
        return this.execute((P)entidadDto);
    }


}

