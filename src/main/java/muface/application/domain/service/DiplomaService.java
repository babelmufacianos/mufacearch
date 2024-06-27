package muface.application.domain.service;

import muface.application.domain.repository.diplomas.DiplomaRepository;
import muface.application.domain.valueobject.diplomas.DiplomaDTO;
import muface.application.domain.model.Diploma;
import muface.arch.command.IArqDTOMapper;
import muface.arch.service.ArqGenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiplomaService extends ArqGenericService<DiplomaDTO, Long> {

    public DiplomaService(@Qualifier("diplomaJPARepository") CrudRepository repo,
                          @Qualifier("diplomaDTOMapper") IArqDTOMapper dtoMapper) {
        super(repo, dtoMapper);
    }

    /*public DiplomaService(@Qualifier("diplomaMongoRepository") CrudRepository repo,
                          @Qualifier("diplomaDocumentDTOMapper") IArqDTOMapper dtoMapper) {
        super(repo, dtoMapper);
    }*/

    public List<DiplomaDTO> buscarDiplomasPorNombreDeTitulacion(String nameOfTitulacion) {
        List<DiplomaDTO> resultado = new ArrayList<>();
        DiplomaRepository diplomaRepository = (DiplomaRepository) getRepositorio();
        List<Diploma> listaEntities = diplomaRepository.findDiplomasByTitulo(nameOfTitulacion);
        listaEntities.forEach((diploma) -> {
            DiplomaDTO diplomaDTO = new DiplomaDTO();
            diplomaDTO.setEntity(diploma);
            resultado.add(diplomaDTO);
        });
        return resultado;
    }

    public Page<DiplomaDTO> buscarDiplomasPorNombreDeTitulacion(String nameOfTitulacion, Pageable pageable) {
        DiplomaRepository diplomaRepository = (DiplomaRepository) getRepositorio();
        Pageable newPageable = mapearCamposOrdenacionDeEntidad(new DiplomaDTO(), pageable);
        Page<Diploma> resultado = diplomaRepository.findDiplomasByTitulo(nameOfTitulacion, newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }


}
