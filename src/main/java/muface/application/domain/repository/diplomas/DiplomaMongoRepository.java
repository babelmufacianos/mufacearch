package muface.application.domain.repository.diplomas;

import muface.application.domain.model.Diploma;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiplomaMongoRepository  extends MongoRepository<Diploma, Long>, DiplomaRepository {

}
