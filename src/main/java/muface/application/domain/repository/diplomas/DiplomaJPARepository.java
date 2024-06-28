package muface.application.domain.repository.diplomas;

import muface.application.domain.model.Diploma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaJPARepository extends DiplomaRepository, JpaRepository<Diploma, Long> {

}
