package muface.application.domain.valueobject;

import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;

import java.io.Serializable;
@Data
public class OrganismoDTO extends ArqAbstractSimpleDTO {

    private Serializable id;
    private String nombreCompleto;
    private String estadoORegion;


}
