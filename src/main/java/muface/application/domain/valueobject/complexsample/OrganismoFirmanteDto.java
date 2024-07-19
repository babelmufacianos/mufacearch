package muface.application.domain.valueobject.complexsample;

import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;

import java.util.List;

@Data
public class OrganismoFirmanteDto extends ArqAbstractSimpleDTO {

    private Long id;
    private String description;

    private List<DelegacionDto> delegaciones;

}

