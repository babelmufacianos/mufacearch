package muface.application.domain.model;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Diploma")
@Data
public class DiplomaDocument implements Serializable, IDiploma {

    @Id
    private String id;

    @Max(value = 999999, message = "{idCliente.max}")
    private Long idcustomer;

    @NotEmpty(message = "{nombreCliente.notnull}")
    private String name;

    private String titulo;

    private String region;

    @Override
    public void setId(Object id) {
        this.id = (String) id;
    }
}

