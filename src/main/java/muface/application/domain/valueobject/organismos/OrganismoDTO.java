package muface.application.domain.valueobject.organismos;

import lombok.Data;
import muface.application.domain.model.IOrganismo;
import muface.arch.command.ArqAbstractDTO;

import java.io.Serializable;
@Data
public class OrganismoDTO extends ArqAbstractDTO<Serializable, IOrganismo> {

    private Long id;
    private String nombreCompleto;
    private String estadoORegion;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String mappingDTOAEntity(String fieldInDto) {
        if ("nombreCompleto".contentEquals(fieldInDto)) {
            return "nombre";
        } else if ("estadoORegion".contentEquals(fieldInDto)) {
            return "state";
        } else {
            return "state";
        }
    }

    @Override
    public void actualizarDTO(IOrganismo entity) {
        this.nombreCompleto = entity.getNombre();
        this.estadoORegion = entity.getState();
    }

    @Override
    public void actualizarEntidad(IOrganismo entity) {
        entity.setNombre(this.nombreCompleto);
        entity.setState(this.estadoORegion);
    }

}
