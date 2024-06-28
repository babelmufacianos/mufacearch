package muface.application.domain.valueobject.diplomas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import muface.application.domain.model.IDiploma;
import muface.arch.command.IArqDTO;
import lombok.Data;
import muface.arch.command.IArqDTOMapper;

@Data
public class DiplomaDTO implements IArqDTO<Long, IDiploma> {

    @JsonIgnore
    private IArqDTOMapper<IDiploma, DiplomaDTO> mapper;

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
    public void setDtoMapper(IArqDTOMapper mapperInfered) {
        this.mapper = mapperInfered;
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
    public void setEntity(IDiploma diploma) {
        this.id = (Long) diploma.getId();
        this.idCliente = diploma.getIdcustomer();
        this.nombreCompleto = diploma.getName();
        this.titulacion = diploma.getTitulo();
        this.regionOComarca = diploma.getRegion();
    }

    @Override
    @JsonIgnore
    public IDiploma getEntity() {
        IDiploma diploma = mapper.getNewInnerInstance();
        diploma.setId(this.id);
        diploma.setIdcustomer(this.idCliente);
        diploma.setName(this.nombreCompleto);
        diploma.setTitulo(this.titulacion);
        diploma.setRegion(this.regionOComarca);
        return diploma;
    }

    @Override
    public void actualizarEntidad(IDiploma diploma) {
        diploma.setIdcustomer(this.idCliente);
        diploma.setName(this.nombreCompleto);
        diploma.setTitulo(this.titulacion);
        diploma.setRegion(this.regionOComarca);
    }



}
