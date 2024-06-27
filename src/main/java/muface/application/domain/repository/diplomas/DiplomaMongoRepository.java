package muface.application.domain.repository.diplomas;

import muface.application.domain.model.DiplomaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomaMongoRepository extends DiplomaRepository<DiplomaDocument, String>, MongoRepository<DiplomaDocument, String> {

}

