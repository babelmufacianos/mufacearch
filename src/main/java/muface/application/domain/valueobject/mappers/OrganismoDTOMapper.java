package muface.application.domain.valueobject.mappers;

import muface.application.domain.model.Organismo;
import muface.application.domain.valueobject.OrganismoDTO;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;

@Component
public class OrganismoDTOMapper implements IMapper<OrganismoDTO, Organismo> {

    @Override
    public Organismo toEntity(OrganismoDTO dto) {
        Organismo entidad = new Organismo();
        entidad.setId((Long) dto.getId());
        entidad.setNombre(dto.getNombreCompleto());
        entidad.setState(dto.getEstadoORegion());
        return entidad;
    }

    @Override
    public OrganismoDTO toDto(Organismo entity) {
        OrganismoDTO dto = new OrganismoDTO();
        dto.setId(entity.getId());
        dto.setEstadoORegion(entity.getState());
        dto.setNombreCompleto(entity.getNombre());
        return dto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if ("nombreCompleto".contentEquals(fieldInDto)) {
            return "nombre";
        } else if ("estadoORegion".contentEquals(fieldInDto)) {
            return "state";
        } else {
            return "id";
        }
    }

}
