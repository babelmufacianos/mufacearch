package muface.application.domain.repository;

import muface.application.domain.model.Customer;
import muface.arch.repository.ArqRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>, ArqRepository<Customer, String> {


}

