package muface.application.domain.valueobject.complexsample.mappers;

import muface.application.domain.model.complexsample.Ciudadano;
import muface.application.domain.valueobject.complexsample.CiudadanoDto;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;


@Component
public class CiudadanoMapper implements IMapper<CiudadanoDto, Ciudadano> {


    @Override
    public Ciudadano toEntity(CiudadanoDto dto) {
        Ciudadano entidad = new Ciudadano();
        entidad.setId(dto.getId());
        entidad.setApellidos(dto.getApellidosCiudadano());
        entidad.setCif(dto.getCifCiudadano());
        entidad.setNombre(dto.getNombreCiudadano());
        return entidad;
    }

    @Override
    public CiudadanoDto toDto(Ciudadano entity) {
        CiudadanoDto dto = new CiudadanoDto();
        dto.setId(entity.getId());
        dto.setCifCiudadano(entity.getCif());
        dto.setApellidosCiudadano(entity.getApellidos());
        dto.setNombreCiudadano(entity.getNombre());
        return dto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if ("nombreCiudadano".contentEquals(fieldInDto)) {
            return "nombre";
        } else if ("apellidosCiudadano".contentEquals(fieldInDto)) {
            return "apellidos";
        } else if ("cifCiudadano".contentEquals(fieldInDto)) {
            return "cif";
        } else {
            return "id";
        }
    }

}
