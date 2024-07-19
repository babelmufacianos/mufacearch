package muface.application.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import muface.arch.command.ArqAbstractSimpleDTO;

import java.io.Serializable;

@Data
public class DiplomaDTO extends ArqAbstractSimpleDTO {

    private Serializable id;
    private Long idCliente;
    private String nombreCompleto;
    private String regionOComarca;
    private String titulacion;

    /** campo calculado transient que no está en el modelo (entidad-relacional o document-non-relational) **/
    @JsonIgnore
    private String continente;




}
