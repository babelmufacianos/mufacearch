package muface.application.domain.repository.diplomas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DiplomaRepository<D, ID> extends CrudRepository<D, ID> {

    List<D> findDiplomasByTitulo(String titulo);

    Page<D> findDiplomasByTitulo(String titulo, Pageable pageable);

}
