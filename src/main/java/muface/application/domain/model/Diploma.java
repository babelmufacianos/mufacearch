package muface.application.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Entity
@Table(name = "Diploma")
@Document(collection = "Diploma")
@Data
public class Diploma implements Serializable, IDiploma {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Max(value = 999999, message = "{idCliente.max}")
    private Long idcustomer;

    @Column
    @NotEmpty(message = "{nombreCliente.notnull}")
    private String name;

    @Column
    private String titulo;

    @Column
    private String region;
    @Override
    public void setId(Object id) {
        this.id = (Long) id;
    }

}

