package muface.application.domain.valueobject.complexsample.mappers;

import muface.application.domain.model.complexsample.Ciudadano;
import muface.application.domain.model.complexsample.OrganismoFirmante;
import muface.application.domain.model.complexsample.Expediente;
import muface.application.domain.model.complexsample.Resolucion;
import muface.application.domain.valueobject.complexsample.CiudadanoDto;
import muface.application.domain.valueobject.complexsample.OrganismoFirmanteDto;
import muface.application.domain.valueobject.complexsample.ExpedienteDto;
import muface.application.domain.valueobject.complexsample.ResolucionDto;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ExpedienteMapper implements IMapper<ExpedienteDto, Expediente> {

    private final IMapper<OrganismoFirmanteDto, OrganismoFirmante> mapperOrgaFirmante;
    private final IMapper<CiudadanoDto, Ciudadano> mapperCiudadano;
    private final IMapper<ResolucionDto, Resolucion> mapperResolucion;

    public ExpedienteMapper(IMapper<OrganismoFirmanteDto, OrganismoFirmante> mapperOrgaFirmante,
                            IMapper<CiudadanoDto, Ciudadano> mapperCiudadano,
                            IMapper<ResolucionDto, Resolucion> mapperResolucion) {
        this.mapperOrgaFirmante = mapperOrgaFirmante;
        this.mapperCiudadano = mapperCiudadano;
        this.mapperResolucion = mapperResolucion;
    }

    @Override
    public Expediente toEntity(ExpedienteDto dto) {
        Expediente expediente = new Expediente();
        expediente.setId(dto.getId());
        expediente.setName(dto.getName());
        if (dto.getOrganismosFirmantes() != null) {
            expediente.setOrganismoFirmantes(
                            dto.getOrganismosFirmantes().stream()
                    .map(mapperOrgaFirmante::toEntity)
                    .collect(Collectors.toList()));
        }
        if (dto.getResoluciones() != null) {
            expediente.setResoluciones(
                    dto.getResoluciones().stream()
                            .map(mapperResolucion::toEntity)
                            .collect(Collectors.toList()));
        }
        if (dto.getCiudadano() != null) {
            expediente.setCiudadano(mapperCiudadano.toEntity(dto.getCiudadano()));
        }
        return expediente;
    }

    @Override
    public ExpedienteDto toDto(Expediente entity) {
        ExpedienteDto expedienteDto = new ExpedienteDto();
        expedienteDto.setId(entity.getId());
        expedienteDto.setName(entity.getName());
        if (entity.getOrganismoFirmantes() != null) {
            expedienteDto.setOrganismosFirmantes(entity.getOrganismoFirmantes().stream()
                    .map(mapperOrgaFirmante::toDto)
                    .collect(Collectors.toList()));
        }
        if (entity.getResoluciones() != null) {
            expedienteDto.setResoluciones(entity.getResoluciones().stream()
                    .map(mapperResolucion::toDto)
                    .collect(Collectors.toList()));
        }
        if (entity.getCiudadano() != null) {
            expedienteDto.setCiudadano(mapperCiudadano.toDto(entity.getCiudadano()));
        }
        return expedienteDto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if ("name".contentEquals(fieldInDto)) {
            return "name";
        } else {
            return "id";
        }
    }

}

