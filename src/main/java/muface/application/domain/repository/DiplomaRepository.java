package muface.application.domain.repository;

import muface.application.domain.model.Diploma;
import muface.arch.repository.ArqRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplomaRepository extends MongoRepository<Diploma, String>, ArqRepository<Diploma, String> {
        //JpaRepository<Diploma, Long>, ArqRepository<Diploma, Long> {

    List<Diploma> findDiplomasByTitulo(String titulo);

    Page<Diploma> findDiplomasByTitulo(String titulo, Pageable pageable);

}
