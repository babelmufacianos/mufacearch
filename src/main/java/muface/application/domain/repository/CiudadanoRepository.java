package muface.application.domain.repository;

import muface.application.domain.model.complexsample.Ciudadano;
import muface.arch.repository.ArqRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long>, ArqRepository<Ciudadano, Long> {


}

