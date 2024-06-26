package muface.application.domain.repository;

import muface.application.domain.model.ClienteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteMongoRepository extends MongoRepository<ClienteDocument, String>, ClienteRepository {

    @Query("{ 'official' : { $regex: ?0, $options: 'i' } }")
    List<ClienteDocument> buscarClienteConUnaTitulacion(String titulo);

    @Query("{ 'official' : { $regex: ?0, $options: 'i' } }")
    Page<ClienteDocument> buscarClienteConUnaTitulacion(String titulo, Pageable pageable);

}

