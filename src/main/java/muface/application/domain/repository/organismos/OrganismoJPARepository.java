package muface.application.domain.repository.organismos;

import muface.application.domain.model.OrganismoRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismoJPARepository extends JpaRepository<OrganismoRel, Long>, OrganismoRepository<OrganismoRel, Long> {

}
