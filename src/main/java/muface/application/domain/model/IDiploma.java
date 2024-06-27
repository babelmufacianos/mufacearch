package muface.application.domain.model;

import java.io.Serializable;

public interface IDiploma extends Serializable {

    Object getId();

    void setId(Object id);

    Long getIdcustomer();

    void setIdcustomer(Long idcustomer);

    String getName();

    void setName(String name);

    String getTitulo();

    void setTitulo(String titulo);

    String getRegion();

    void setRegion(String region);

}
