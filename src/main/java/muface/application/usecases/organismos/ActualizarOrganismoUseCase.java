package muface.application.usecases.organismos;

import jakarta.transaction.Transactional;
import muface.application.domain.service.OrganismoService;
import muface.application.domain.valueobject.organismos.OrganismoDTO;
import muface.arch.command.ArqAbstractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualizarOrganismoUseCase extends ArqAbstractUseCase<OrganismoDTO, OrganismoDTO> {

    @Autowired
    private OrganismoService organismoService;

    @Override
    @Transactional
    public OrganismoDTO execute(OrganismoDTO organismoDTO) {
        return this.organismoService.actualizar(organismoDTO);
    }

}
