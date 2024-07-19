package muface.application.domain.service;

import muface.application.domain.model.Customer;
import muface.application.domain.repository.CustomerRepository;
import muface.application.domain.valueobject.CustomerDTO;
import muface.arch.service.ArqGenericService;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends ArqGenericService<CustomerDTO, Customer> {

    public CustomerService(CustomerRepository repo) {
        super(repo);
    }

}
