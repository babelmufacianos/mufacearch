package muface.application.domain.repository;

import muface.application.domain.model.complexsample.Delegacion;
import muface.arch.repository.ArqRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DelegacionRepository extends JpaRepository<Delegacion, Long>, ArqRepository<Delegacion, Long> {


}

