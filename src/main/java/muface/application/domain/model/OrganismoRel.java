package muface.application.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Organismo")
public class OrganismoRel extends Organismo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

}
