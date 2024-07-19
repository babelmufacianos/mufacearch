package muface.arch.command;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;


public interface IArqDTO extends Serializable {

    Serializable getId();

    void setPageable(Pageable pageable);

    Pageable getPageable();



}
