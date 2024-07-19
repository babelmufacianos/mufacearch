package muface.application.domain.model.complexsample;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class Resolucion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private Timestamp fechaEmisionResolucion;

    private Timestamp fechaInicioPeriodoReclamacion;

    private Timestamp fechaFinPeriodoReclamacion;

    private String textoJustificativo;

    private Integer aprobatoria; //o: no, 1: SI

}
