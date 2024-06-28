package muface.arch.command;

import java.io.Serializable;

public interface IArqDTOMapper<D extends IArqDTO> {
    D map(Serializable entity);

    D newInstance();

    Serializable getNewInnerInstance();

    void setTypeOfRepoImplementation(String typeOfRepoImpl);

}
