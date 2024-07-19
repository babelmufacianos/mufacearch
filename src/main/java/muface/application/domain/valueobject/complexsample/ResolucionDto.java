package muface.application.domain.valueobject.complexsample;

import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;

import java.sql.Timestamp;


@Data
public class ResolucionDto extends ArqAbstractSimpleDTO {

    private Long id;

    private String tipoResolucion;

    private Timestamp fechaEmision;

    private Timestamp fecIniPeriodoAlegaciones;

    private Timestamp fecFinPeriodoAlegaciones;

    private String textoJustificativo;

    private Integer aprobatoria; //o: no, 1: SI

}
