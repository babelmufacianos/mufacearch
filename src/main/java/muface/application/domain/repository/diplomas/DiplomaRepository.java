package muface.application.domain.repository.diplomas;

import muface.application.domain.model.Diploma;
import muface.arch.repository.ArqRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Long>, ArqRepository<Diploma, Long> {
    List<Diploma> findDiplomasByTitulo(String titulo);

    Page<Diploma> findDiplomasByTitulo(String titulo, Pageable pageable);

}
