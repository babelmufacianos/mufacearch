package muface.application.domain.service;

import muface.application.domain.valueobject.OrganismoDTO;
import muface.arch.command.IArqDTOMapper;
import muface.arch.service.ArqGenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganismoService extends ArqGenericService<OrganismoDTO, Long> {

    public OrganismoService(@Qualifier("organismoJPARepository") CrudRepository repo/*,
                            @Qualifier("organismoDTOMapper") IArqDTOMapper dtoMapper*/) {
        super(repo);//, dtoMapper);
    }
}
