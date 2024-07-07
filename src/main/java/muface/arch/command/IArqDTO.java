package muface.arch.command;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;


public interface IArqDTO<ID, D> extends Serializable {

    ID getId();

    String mappingDTOAEntity(String fieldInDto);

    void actualizarDTO(D entity);

    void actualizarEntidad(D entity);

    void setPageable(Pageable pageable);

    Pageable getPageable();

}
