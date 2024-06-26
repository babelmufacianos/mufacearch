package muface.application.domain.repository;

import io.lettuce.core.dynamic.annotation.Param;
import muface.application.domain.model.Diploma;
import muface.application.domain.model.DiplomaDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiplomaMongoRepository extends MongoRepository<DiplomaDocument, String>, DiplomaRepository {

    @Query("SELECT d FROM Diploma d WHERE d.titulo LIKE %:titulacionName%")
    List<Diploma> findDiplomasByTitulacionName(@Param("titulacionName") String titulacionName);
    @Query("SELECT d FROM Diploma d WHERE d.titulo LIKE %:titulacionName%")
    Page<Diploma> findDiplomasByTitulacionName(@Param("titulacionName") String titulacionName, Pageable pageable);

}
