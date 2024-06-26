package muface.application.domain.service;

import muface.application.domain.repository.DiplomaRepository;
import muface.application.domain.valueobject.DiplomaDTO;
import muface.application.domain.model.Diploma;
import muface.arch.service.ArqGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiplomaService extends ArqGenericService<DiplomaDTO, Long> {

    /*** métodos personalizados ***/

    public List<DiplomaDTO> buscarDiplomasPorNombreDeTitulacion(String nameOfTitulacion) {
        List<DiplomaDTO> resultado = new ArrayList<>();
        DiplomaRepository diplomaRepository = (DiplomaRepository) getRepositorio();
        List<Diploma> listaEntities = diplomaRepository.findDiplomasByTitulacionName(nameOfTitulacion);
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
        Page<Diploma> resultado = diplomaRepository.findDiplomasByTitulacionName(nameOfTitulacion, newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }


}
