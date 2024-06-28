package muface.application.domain.valueobject.diplomas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import muface.application.domain.model.Diploma;
import muface.arch.command.IArqDTO;
import lombok.Data;

@Data
public class DiplomaDTO implements IArqDTO<Long, Diploma> {

    private Long id;
    private Long idCliente;
    private String nombreCompleto;
    private String regionOComarca;
    private String titulacion;

    /** campo calculado transient que no está en el modelo (entidad-relacional o document-non-relational) **/
    @JsonIgnore
    private String continente;

    public DiplomaDTO() {
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public String getInnerOrderField(String fieldInDto) {
        if (fieldInDto.contentEquals("id")) {
            return "id";
        } else if (fieldInDto.contentEquals("idCliente")) {
            return "idcustomer";
        } else if (fieldInDto.contentEquals("nombreCompleto")) {
            return "name";
        } else if (fieldInDto.contentEquals("regionOComarca")) {
            return "region";
        } else {
            return "";
        }
    }

    @Override
    public void actualizarDTO(Diploma diploma) {
        this.id = (Long) diploma.getId();
        this.idCliente = diploma.getIdcustomer();
        this.nombreCompleto = diploma.getName();
        this.titulacion = diploma.getTitulo();
        this.regionOComarca = diploma.getRegion();
    }


    @Override
    public void actualizarEntidad(Diploma diploma) {
        diploma.setIdcustomer(this.idCliente);
        diploma.setName(this.nombreCompleto);
        diploma.setTitulo(this.titulacion);
        diploma.setRegion(this.regionOComarca);
    }


}
