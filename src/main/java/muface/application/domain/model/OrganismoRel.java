package muface.application.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "Organismo")
@Data
public class OrganismoRel implements IOrganismo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String nombre;
    @Column
    private String state;

    @Override
    public void setId(Serializable id) {
        this.id = (Long) id;
    }

}
