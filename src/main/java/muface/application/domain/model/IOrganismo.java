package muface.application.domain.model;

import java.io.Serializable;

public interface IOrganismo extends Serializable {

    Serializable getId();

    void setId(Serializable id);

    String getNombre();

    void setNombre(String nombre);

    String getState();

    void setState(String state);

}
