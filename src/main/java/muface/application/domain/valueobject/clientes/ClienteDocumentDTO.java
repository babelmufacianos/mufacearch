package muface.application.domain.valueobject.clientes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import muface.arch.command.IArqDTO;
import muface.application.domain.model.ClienteDocument;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClienteDocumentDTO implements IArqDTO<String, ClienteDocument> {

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
    public void actualizarDTO(ClienteDocument cliente) {
        this.id = cliente.getId();
        this.pais = cliente.getCountry();
        this.nombre = cliente.getName();
        this.titulacionOficial = cliente.getOfficial();
    }

    @Override
    public void actualizarEntidad(ClienteDocument cliente) {
        cliente.setName(this.nombre);
        cliente.setOfficial(this.titulacionOficial);
        cliente.setCountry(this.pais);
    }

}
