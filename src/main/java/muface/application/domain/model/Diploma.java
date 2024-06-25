package muface.application.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Diploma implements Serializable {

    @Id
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


}

