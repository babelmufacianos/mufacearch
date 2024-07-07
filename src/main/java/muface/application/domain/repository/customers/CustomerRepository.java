package muface.application.domain.repository.customers;

import muface.application.domain.model.CustomerDocument;
import muface.arch.repository.ArqRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<CustomerDocument, String>, ArqRepository<CustomerDocument, String> {


}

