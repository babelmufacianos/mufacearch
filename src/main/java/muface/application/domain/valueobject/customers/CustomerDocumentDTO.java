package muface.application.domain.valueobject.customers;

import lombok.Data;
import muface.arch.command.ArqAbstractDTO;
import muface.application.domain.model.CustomerDocument;


@Data
public class CustomerDocumentDTO extends ArqAbstractDTO<String, CustomerDocument> {

    private String id;
    private String nombre;
    private String titulacionOficial;
    private String pais;

    public CustomerDocumentDTO() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String mappingDTOAEntity(String fieldInDto) {
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
    public void actualizarDTO(CustomerDocument cliente) {
        this.id = cliente.getId();
        this.pais = cliente.getCountry();
        this.nombre = cliente.getName();
        this.titulacionOficial = cliente.getOfficial();
    }

    @Override
    public void actualizarEntidad(CustomerDocument cliente) {
        cliente.setName(this.nombre);
        cliente.setOfficial(this.titulacionOficial);
        cliente.setCountry(this.pais);
    }

}
