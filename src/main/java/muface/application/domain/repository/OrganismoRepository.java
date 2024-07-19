package muface.application.domain.repository;

import muface.application.domain.model.Organismo;
import muface.arch.repository.ArqRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrganismoRepository
        //extends MongoRepository<OrganismoNoSQL, String>, ArqRepository<Organismo, String> {
         extends JpaRepository<Organismo, Long>, ArqRepository<Organismo, Long> {

}
