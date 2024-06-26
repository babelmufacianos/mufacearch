package muface.application.domain.repository;

import io.lettuce.core.dynamic.annotation.Param;
import muface.application.domain.model.ClienteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteMongoRepository extends MongoRepository<ClienteDocument, String>, ClienteRepository {

    @Query("SELECT d FROM ClienteDocument d WHERE d.official LIKE %:official%")
    List<ClienteDocument> buscarClienteConUnaTitulacion(@Param("official") String official);

    @Query("SELECT d FROM ClienteDocument d WHERE d.official LIKE %:official%")
    Page<ClienteDocument> buscarClienteConUnaTitulacion(@Param("official") String official, Pageable pageable);

}

