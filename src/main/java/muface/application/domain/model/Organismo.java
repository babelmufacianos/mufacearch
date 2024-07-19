package muface.application.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Document o @Entity
@Entity
@Data
public class Organismo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Column(name="name", length = 100)
    private String nombre;

    private String state;

    public Organismo() {

    }

    public Organismo(String name, String state) {
        this.nombre = name;
        this.state = state;
    }

}
