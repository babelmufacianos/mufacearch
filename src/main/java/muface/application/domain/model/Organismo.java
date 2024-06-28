package muface.application.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Entity
@Table(name = "Organismo")
@Data
@Document(collection = "Organismo")
public class Organismo implements Serializable {
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String state;

}
