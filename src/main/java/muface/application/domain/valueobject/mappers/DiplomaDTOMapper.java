package muface.application.domain.valueobject.mappers;

import muface.application.domain.model.Diploma;
import muface.application.domain.valueobject.DiplomaDTO;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;

@Component
public class DiplomaDTOMapper implements IMapper<DiplomaDTO, Diploma> {

    @Override
    public Diploma toEntity(DiplomaDTO dto) {
        Diploma entidad = new Diploma();
        entidad.setId((String) dto.getId());
        entidad.setIdcustomer(dto.getIdCliente());
        entidad.setName(dto.getNombreCompleto());
        entidad.setTitulo(dto.getTitulacion());
        entidad.setRegion(dto.getRegionOComarca());
        return entidad;
    }

    @Override
    public DiplomaDTO toDto(Diploma entidad) {
        DiplomaDTO dto = new DiplomaDTO();
        dto.setId(entidad.getId());
        dto.setIdCliente(entidad.getIdcustomer());
        dto.setNombreCompleto(entidad.getName());
        dto.setTitulacion(entidad.getTitulo());
        dto.setRegionOComarca(entidad.getRegion());
        return dto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if (fieldInDto.contentEquals("idCliente")) {
            return "idcustomer";
        } else if (fieldInDto.contentEquals("nombreCompleto")) {
            return "name";
        } else if (fieldInDto.contentEquals("regionOComarca")) {
            return "region";
        } else {
            return "id";
        }
    }

}
