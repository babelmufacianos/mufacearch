package muface.application.domain.repository.clientes;

import muface.application.domain.model.ClienteDocument;
import muface.arch.repository.ArqRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends MongoRepository<ClienteDocument, String>, ArqRepository<ClienteDocument, String> {

    List<ClienteDocument> findByOfficial(String titulacionName);

    Page<ClienteDocument> findByOfficial(String titulacionName, Pageable pageable);

}

