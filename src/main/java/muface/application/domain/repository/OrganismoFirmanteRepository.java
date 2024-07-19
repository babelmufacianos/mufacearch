package muface.application.domain.repository;

import muface.application.domain.model.complexsample.OrganismoFirmante;
import muface.arch.repository.ArqRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrganismoFirmanteRepository extends JpaRepository<OrganismoFirmante, Long>,
        ArqRepository<OrganismoFirmante, Long> {


}

