package muface.application.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
public class Organismo implements Serializable {
    private Long id;
    private String nombre;
    private String state;

}
