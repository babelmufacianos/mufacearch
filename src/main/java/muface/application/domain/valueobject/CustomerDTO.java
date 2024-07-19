package muface.application.domain.valueobject;

import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;


@Data
public class CustomerDTO extends ArqAbstractSimpleDTO {

    private String id;
    private String nombre;
    private String titulacionOficial;
    private String pais;



}
