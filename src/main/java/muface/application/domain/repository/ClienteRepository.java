package muface.application.domain.repository;

import muface.application.domain.model.ClienteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteRepository {

    List<ClienteDocument> buscarClienteConUnaTitulacion(String titulacionName);

    Page<ClienteDocument> buscarClienteConUnaTitulacion(String titulacionName, Pageable pageable);

}
