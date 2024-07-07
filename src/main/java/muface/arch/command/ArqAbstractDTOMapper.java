package muface.arch.command;

import lombok.Data;

@Data
public abstract class ArqAbstractDTOMapper<E extends IArqDTO> implements IArqDTOMapper<E> {

    protected String typeOfRepoImpl;

    @Override
    public void setJPAONoSQL(String typeOfRepoImpl) {
        this.typeOfRepoImpl = typeOfRepoImpl;
    }

}
