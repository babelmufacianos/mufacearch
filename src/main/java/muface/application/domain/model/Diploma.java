package muface.application.domain.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

//@Entity
@Document
@Data
public class Diploma implements Serializable {

    @Id
    private String id;

    @Max(value = 999999, message = "{idCliente.max}")
    private Long idcustomer;

    @NotEmpty(message = "{nombreCliente.notnull}")
    private String name;

    private String titulo;

    private String region;

}

