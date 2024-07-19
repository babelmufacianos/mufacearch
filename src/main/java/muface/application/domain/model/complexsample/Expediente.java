package muface.application.domain.model.complexsample;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Expediente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Ciudadano ciudadano;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganismoFirmante> organismoFirmantes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resolucion> resoluciones;

}
