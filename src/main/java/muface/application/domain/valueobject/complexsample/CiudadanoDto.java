package muface.application.domain.valueobject.complexsample;

import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;

@Data
public class CiudadanoDto extends ArqAbstractSimpleDTO {

    private Long id;

    private String nombreCiudadano;

    private String apellidosCiudadano;

    private String cifCiudadano;

}

