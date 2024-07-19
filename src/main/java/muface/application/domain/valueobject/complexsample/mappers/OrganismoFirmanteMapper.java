package muface.application.domain.valueobject.complexsample.mappers;

import muface.application.domain.model.complexsample.OrganismoFirmante;
import muface.application.domain.model.complexsample.Delegacion;
import muface.application.domain.valueobject.complexsample.OrganismoFirmanteDto;
import muface.application.domain.valueobject.complexsample.DelegacionDto;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrganismoFirmanteMapper implements IMapper<OrganismoFirmanteDto, OrganismoFirmante> {

    private final IMapper<DelegacionDto, Delegacion> grandChildMapper;

    public OrganismoFirmanteMapper(IMapper<DelegacionDto, Delegacion> mapper) {
        this.grandChildMapper = mapper;
    }

    @Override
    public OrganismoFirmante toEntity(OrganismoFirmanteDto dto) {
        OrganismoFirmante organismoFirmante = new OrganismoFirmante();
        organismoFirmante.setId(dto.getId());
        organismoFirmante.setDescription(dto.getDescription());
        if (dto.getDelegaciones() != null) {
            organismoFirmante.setDelegaciones(dto.getDelegaciones().stream()
                    .map(grandChildMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return organismoFirmante;
    }

    @Override
    public OrganismoFirmanteDto toDto(OrganismoFirmante entity) {
        OrganismoFirmanteDto organismoFirmanteDto = new OrganismoFirmanteDto();
        organismoFirmanteDto.setId(entity.getId());
        organismoFirmanteDto.setDescription(entity.getDescription());
        if (entity.getDelegaciones() != null) {
            organismoFirmanteDto.setDelegaciones(entity.getDelegaciones().stream()
                    .map(grandChildMapper::toDto)
                    .collect(Collectors.toList()));
        }
        return organismoFirmanteDto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if ("description".contentEquals(fieldInDto)) {
            return "description";
        } else {
            return "id";
        }
    }

}

