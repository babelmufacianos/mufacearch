package muface.application.domain.repository;

import muface.application.domain.model.Organismo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganismoJPARepository extends JpaRepository<Organismo, Long>, OrganismoRepository {

}
