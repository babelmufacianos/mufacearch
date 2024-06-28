package muface.application.domain.repository.organismos;

import muface.application.domain.model.OrganismoNoSQL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismoMongoRepository extends MongoRepository<OrganismoNoSQL, Long>, OrganismoRepository<OrganismoNoSQL, Long> {

}
