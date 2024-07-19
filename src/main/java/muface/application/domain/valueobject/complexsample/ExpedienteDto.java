package muface.application.domain.valueobject.complexsample;

import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;

import java.util.List;

@Data
public class ExpedienteDto extends ArqAbstractSimpleDTO {

    private Long id;
    private String name;

    private List<OrganismoFirmanteDto> organismosFirmantes;
    private List<ResolucionDto> resoluciones;
    private CiudadanoDto ciudadano;

}

