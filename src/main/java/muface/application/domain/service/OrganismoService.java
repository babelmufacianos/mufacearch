package muface.application.domain.service;

import muface.application.domain.repository.organismos.OrganismoRepository;
import muface.application.domain.valueobject.organismos.OrganismoDTO;
import muface.application.domain.valueobject.organismos.OrganismoDTOMapper;
import muface.arch.repository.ArqPortRepository;
import muface.arch.service.ArqGenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrganismoService extends ArqGenericService<OrganismoDTO, Long> {

    public OrganismoService(@Qualifier("organismoJPARepository") OrganismoRepository repo, OrganismoDTOMapper organismoDTOMapper) {
        super(repo, organismoDTOMapper);
        organismoDTOMapper.setTypeOfRepoImplementation(ArqPortRepository.JPA_IMPL);
    }

    /*public DiplomaService(@Qualifier("organismoMongoRepository") CrudRepository repo, organismoDTOMapper dtomapper) {
        super(repo, dtoMapper);
    }*/

}
