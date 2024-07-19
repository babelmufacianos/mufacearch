package muface.arch.command;

import java.io.Serializable;

public interface IMapper<TDto extends IArqDTO, T extends Serializable> {

    T toEntity(TDto dto);
    TDto toDto(T entity);
    String sortMappingFields(String fieldInDto);
}

