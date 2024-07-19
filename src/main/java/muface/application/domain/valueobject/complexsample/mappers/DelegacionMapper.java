package muface.application.domain.valueobject.complexsample.mappers;

import muface.application.domain.model.complexsample.Delegacion;
import muface.application.domain.valueobject.complexsample.DelegacionDto;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;

@Component
public class DelegacionMapper implements IMapper<DelegacionDto, Delegacion> {

    @Override
    public Delegacion toEntity(DelegacionDto dto) {
        Delegacion delegacion = new Delegacion();
        delegacion.setId(dto.getId());
        delegacion.setDetail(dto.getDetail());
        return delegacion;
    }

    @Override
    public DelegacionDto toDto(Delegacion entity) {
        DelegacionDto delegacionDto = new DelegacionDto();
        delegacionDto.setId(entity.getId());
        delegacionDto.setDetail(entity.getDetail());
        return delegacionDto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if ("detail".contentEquals(fieldInDto)) {
            return "detail";
        } else {
            return "id";
        }
    }

}

