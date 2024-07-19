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
public class DiplomaService extends ArqGenericService<DiplomaDTO, Diploma> {

    public DiplomaService(DiplomaRepository repo){
        super(repo);
    }

    public List<DiplomaDTO> buscarDiplomasPorNombreDeTitulacion(String nameOfTitulacion) {
        DiplomaRepository diplomaRepository = (DiplomaRepository) this.getRepositorio();
        List<Diploma> listaEntities = diplomaRepository.findDiplomasByTitulo(nameOfTitulacion);
        List<DiplomaDTO> resultado = new ArrayList<>();
        listaEntities.forEach((diploma) -> {
            resultado.add(mapper.toDto(diploma));
        });
        return resultado;
    }

    public Page<DiplomaDTO> buscarDiplomasPorNombreDeTitulacion(String nameOfTitulacion, Pageable pageable) {
        DiplomaRepository diplomaRepository = (DiplomaRepository) getRepositorio();
        Pageable newPageable = mapearCamposOrdenacionDeEntidad(pageable);
        Page<Diploma> resultado = diplomaRepository.findDiplomasByTitulo(nameOfTitulacion, newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }


}
