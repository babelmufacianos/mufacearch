package muface.application.domain.service;

import muface.application.domain.model.Organismo;
import muface.application.domain.repository.OrganismoRepository;
import muface.application.domain.valueobject.OrganismoDTO;
import muface.arch.service.ArqGenericService;
import org.springframework.stereotype.Service;


@Service
public class OrganismoService extends ArqGenericService<OrganismoDTO, Organismo> {

    public OrganismoService(OrganismoRepository repo){
        super(repo);
    }

}
