package muface.application.domain.repository.clientes;

import muface.application.domain.model.ClienteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<ClienteDocument, String> {

    List<ClienteDocument> findByOfficial(String titulacionName);

    Page<ClienteDocument> findByOfficial(String titulacionName, Pageable pageable);

}
