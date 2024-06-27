package muface.application.domain.repository.diplomas;

import muface.application.domain.model.Diploma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DiplomaRepository<D, ID> extends CrudRepository<D, ID> {

    List<Diploma> findDiplomasByTitulo(String titulo);

    Page<Diploma> findDiplomasByTitulo(String titulo, Pageable pageable);

}
