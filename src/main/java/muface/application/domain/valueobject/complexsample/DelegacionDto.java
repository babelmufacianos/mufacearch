package muface.application.domain.valueobject.complexsample;

import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;

@Data
public class DelegacionDto extends ArqAbstractSimpleDTO {

    private Long id;
    private String detail;

}

