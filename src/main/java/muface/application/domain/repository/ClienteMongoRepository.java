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

    @Query("SELECT d FROM ClienteDocument d WHERE d.official LIKE %:titulacionName%")
    List<ClienteDocument> findClientesByTitulacionName(@Param("titulacionName") String titulacionName);

    @Query("SELECT d FROM ClienteDocument d WHERE d.official LIKE %:titulacionName%")
    Page<ClienteDocument> findClientesByTitulacionName(@Param("titulacionName") String titulacionName,
                                                       Pageable pageable);

}

