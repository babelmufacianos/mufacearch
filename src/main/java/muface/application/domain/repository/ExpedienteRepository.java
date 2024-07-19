package muface.application.domain.repository;

import muface.application.domain.model.complexsample.Expediente;
import muface.arch.repository.ArqRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Long>, ArqRepository<Expediente, Long> {


}

