package muface.application.domain.repository.clientes;

import muface.application.domain.model.ClienteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteMongoRepository extends MongoRepository<ClienteDocument, String>, ClienteRepository {

}

