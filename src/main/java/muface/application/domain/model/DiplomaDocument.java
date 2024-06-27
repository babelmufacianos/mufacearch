package muface.application.domain.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Diploma")
@Data
public class DiplomaDocument implements Serializable {

    @Id
    @org.springframework.data.annotation.Id
    private String id;

    @Max(value = 999999, message = "{idCliente.max}")
    private Long idcustomer;

    @NotEmpty(message = "{nombreCliente.notnull}")
    private String name;

    private String titulo;

    private String region;

}

