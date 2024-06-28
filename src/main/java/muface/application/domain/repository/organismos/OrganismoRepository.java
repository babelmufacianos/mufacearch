package muface.application.domain.repository.organismos;

import muface.application.domain.model.Organismo;
import org.springframework.data.repository.CrudRepository;

public interface OrganismoRepository<T, ID> extends CrudRepository<T, ID> {

}
