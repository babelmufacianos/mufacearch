package muface.application.domain.valueobject.organismos;

import muface.application.domain.model.Organismo;
import muface.arch.command.IArqDTO;

import java.io.Serializable;

public class OrganismoDTO implements IArqDTO<Serializable, Organismo> {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getInnerOrderField(String fieldInDto) {
        return fieldInDto;
    }

    @Override
    public void setEntity(Organismo entity) {

    }

    @Override
    public void actualizarEntidad(Organismo entity) {

    }
    @Override
    public Organismo getEntity() {
        return new Organismo();
    }

}
