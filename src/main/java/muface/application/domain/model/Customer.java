package muface.application.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "clientes")
public class Customer implements Serializable {

    @Id
    private String id;

    private String name;
    private String official;
    private String country;



}
