package muface.arch.command;

import java.io.Serializable;

public interface IArqDTOMapper<S extends Serializable, D extends IArqDTO> {
    D map(S entity);

    D newInstance();

    S getNewInnerInstance();

}
