package muface.application.domain.service;

import muface.application.domain.repository.organismos.OrganismoRepository;
import muface.application.domain.valueobject.organismos.OrganismoDTO;
import muface.arch.service.ArqGenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrganismoService extends ArqGenericService<OrganismoDTO, Long> {

    public OrganismoService(@Qualifier("organismoJPARepository") OrganismoRepository repo, String tyoeImpl) {
        super(repo, TYPE_REPO_JPA);
    }

    /*public DiplomaService(@Qualifier("organismoMongoRepository") CrudRepository repo, String typeImpl) {
        super(repo, TYPE_REPO_MONGO);
    }*/

}
