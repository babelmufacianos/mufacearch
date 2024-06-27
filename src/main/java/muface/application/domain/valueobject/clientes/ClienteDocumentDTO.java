package muface.application.domain.valueobject.clientes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import muface.application.domain.model.IDiploma;
import muface.application.domain.valueobject.diplomas.DiplomaDTO;
import muface.arch.command.IArqDTO;
import muface.application.domain.model.ClienteDocument;
import muface.arch.command.IArqDTOMapper;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClienteDocumentDTO implements IArqDTO<String, ClienteDocument> {

    @JsonIgnore
    private IArqDTOMapper<IDiploma, DiplomaDTO> mapper;
    private String id;
    private String nombre;
    private String titulacionOficial;
    private String pais;

    public ClienteDocumentDTO() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getInnerOrderField(String fieldInDto) {
        if (fieldInDto.contentEquals("id")) {
            return "id";
        } else if (fieldInDto.contentEquals("titulacionOficial")) {
            return "official";
        } else if (fieldInDto.contentEquals("nombre")) {
            return "name";
        } else if (fieldInDto.contentEquals("pais")) {
            return "country";
        } else {
            return "";
        }
    }

    @Override
    public void setEntity(ClienteDocument cliente) {
        this.id = cliente.getId();
        this.pais = cliente.getCountry();
        this.nombre = cliente.getName();
        this.titulacionOficial = cliente.getOfficial();
    }

    @Override
    @JsonIgnore
    public ClienteDocument getEntity() {
        ClienteDocument cliente = new ClienteDocument();
        cliente.setId(this.id);
        cliente.setName(this.nombre);
        cliente.setOfficial(this.titulacionOficial);
        cliente.setCountry(this.pais);
        return cliente;
    }

    @Override
    public void setDtoMapper(IArqDTOMapper mapperInfered) {
        this.mapper = mapperInfered;
    }

    @Override
    public void actualizarEntidad(ClienteDocument cliente) {
        cliente.setName(this.nombre);
        cliente.setOfficial(this.titulacionOficial);
        cliente.setCountry(this.pais);
    }

}
