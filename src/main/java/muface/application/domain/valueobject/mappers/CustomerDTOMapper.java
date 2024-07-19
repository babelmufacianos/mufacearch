package muface.application.domain.valueobject.mappers;

import muface.application.domain.model.Customer;
import muface.application.domain.valueobject.CustomerDTO;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;


@Component
public class CustomerDTOMapper implements IMapper<CustomerDTO, Customer> {

    @Override
    public Customer toEntity(CustomerDTO dto) {
        Customer entidad = new Customer();
        entidad.setId(dto.getId());
        entidad.setCountry(dto.getPais());
        entidad.setOfficial(dto.getTitulacionOficial());
        entidad.setName(dto.getNombre());
        return entidad;
    }

    @Override
    public CustomerDTO toDto(Customer entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setPais(entity.getCountry());
        dto.setTitulacionOficial(entity.getOfficial());
        dto.setNombre(entity.getName());
        return dto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if (fieldInDto.contentEquals("titulacionOficial")) {
            return "official";
        } else if (fieldInDto.contentEquals("nombre")) {
            return "name";
        } else if (fieldInDto.contentEquals("pais")) {
            return "country";
        } else {
            return "id";
        }
    }

}
