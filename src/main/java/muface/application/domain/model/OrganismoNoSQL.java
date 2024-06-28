package muface.application.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "Organismo")
@Data
public class OrganismoNoSQL implements IOrganismo {

    @Id
    private String id;

    private String nombre;
    private String state;

    @Override
    public void setId(Serializable id) {
        this.id = (String) id;
    }

}
