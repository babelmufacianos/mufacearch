package muface.application.domain.repository.clientes;

import muface.application.domain.model.ClienteDocument;
import muface.arch.repository.ArqRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteRepository extends ArqRepository<ClienteDocument, String> {

    List<ClienteDocument> findByOfficial(String titulacionName);

    Page<ClienteDocument> findByOfficial(String titulacionName, Pageable pageable);

}
