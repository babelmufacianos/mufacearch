package muface.application.domain.service;

import muface.application.domain.repository.customers.CustomerRepository;
import muface.application.domain.valueobject.customers.CustomerDocumentDTO;
import muface.arch.service.ArqGenericService;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends ArqGenericService<CustomerDocumentDTO, String> {

    public CustomerService(CustomerRepository repo) {
        super(repo);
    }

}
