package muface.application.domain.repository;

import muface.application.domain.model.ClienteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteMongoRepository extends MongoRepository<ClienteDocument, String>, ClienteRepository {

        List<ClienteDocument> findByOfficial(String titulacion);

        Page<ClienteDocument> findByOfficial(String titulacion, Pageable pageable);

}

