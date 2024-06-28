package muface.application.domain.valueobject.organismos;

import lombok.Data;
import muface.application.domain.model.Organismo;
import muface.arch.command.IArqDTO;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class OrganismoDTO implements IArqDTO<Serializable, Organismo> {

    private Long id;

    private String nombreCompleto;
    private String estadoORegion;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getInnerOrderField(String fieldInDto) {
        if ("nombreCompleto".contentEquals(fieldInDto)) {
            return "nombre";
        } else if ("estadoORegion".contentEquals(fieldInDto)) {
            return "state";
        } else {
            return "state";
        }
    }

    @Override
    public void actualizarDTO(Organismo entity) {
        this.nombreCompleto = entity.getNombre();
        this.estadoORegion = entity.getState();
    }

    @Override
    public void actualizarEntidad(Organismo entity) {
        entity.setNombre(this.nombreCompleto);
        entity.setState(this.estadoORegion);
    }

}
