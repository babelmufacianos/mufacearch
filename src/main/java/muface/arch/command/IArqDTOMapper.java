package muface.arch.command;

import java.io.Serializable;

public interface IArqDTOMapper<D extends IArqDTO> {

    D newInstance();

    Serializable getNewInnerInstance();

    void setTypeOfRepoImplementation(String typeOfRepoImpl);

}
