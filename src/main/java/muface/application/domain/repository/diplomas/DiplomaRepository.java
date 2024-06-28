package muface.application.domain.repository.diplomas;

import muface.application.domain.model.Diploma;
import muface.arch.repository.ArqRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DiplomaRepository extends ArqRepository<Diploma, Long> {

    List<Diploma> findDiplomasByTitulo(String titulo);

    Page<Diploma> findDiplomasByTitulo(String titulo, Pageable pageable);

}
