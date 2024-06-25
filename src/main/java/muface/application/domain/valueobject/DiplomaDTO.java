package muface.application.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import muface.arch.command.IArqDTO;
import muface.application.domain.model.Diploma;
import lombok.Data;

@Data
public class DiplomaDTO implements IArqDTO<Long, Diploma> {

    private Long id;
    private Long idCliente;
    private String nombreCompleto;
    private String regionOComarca;
    private String titulacion;

    /** campo calculado transient que no está en el modelo (entidad-relacional o document-non-relational) **/
    private String continente;

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
    public void setEntity(Diploma diploma) {
        this.id = diploma.getId();
        this.idCliente = diploma.getIdcustomer();
        this.nombreCompleto = diploma.getName();
        this.titulacion = diploma.getTitulo();
        this.regionOComarca = diploma.getRegion();
    }

    @Override
    @JsonIgnore
    public Diploma getEntity() {
        Diploma diploma = new Diploma();
        diploma.setId(this.id);
        diploma.setIdcustomer(this.idCliente);
        diploma.setName(this.nombreCompleto);
        diploma.setTitulo(this.titulacion);
        diploma.setRegion(this.regionOComarca);
        return diploma;
    }

    @Override
    public void actualizarEntidad(Diploma diploma) {
        diploma.setId(this.id);
        diploma.setIdcustomer(this.idCliente);
        diploma.setName(this.nombreCompleto);
        diploma.setTitulo(this.titulacion);
        diploma.setRegion(this.regionOComarca);
    }



}
