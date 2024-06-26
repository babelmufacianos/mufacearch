package muface.arch.command;

import java.io.Serializable;


public interface IArqDTO<ID, D> extends Serializable {

    ID getId();

    String mappingDTOAEntity(String fieldInDto);

    void actualizarDTO(D entity);

    void actualizarEntidad(D entity);

}
