package muface.arch.command;

import java.io.Serializable;

public interface IArqDTOMapper<D extends IArqDTO> {

    public static final String JPA_TYPE = "JPA";
    D nuevaInstancia();

    Serializable nuevaInstanciaEntidad();

    void setJPAONoSQL(String typeOfRepoImpl);

}
