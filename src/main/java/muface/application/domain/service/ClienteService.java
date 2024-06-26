package muface.application.domain.service;

import muface.application.domain.model.ClienteDocument;
import muface.application.domain.repository.ClienteRepository;
import muface.application.domain.valueobject.ClienteDocumentDTO;
import muface.arch.service.ArqGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService extends ArqGenericService<ClienteDocumentDTO, String> {

    /*** métodos personalizados ***/

    public List<ClienteDocumentDTO> buscarClientesPorTitulacion(String nameOfTitulacion) {
        List<ClienteDocumentDTO> resultado = new ArrayList<>();
        ClienteRepository clienteRepository = (ClienteRepository) getRepositorio();
        List<ClienteDocument> listaEntities = clienteRepository.findClientesByTitulacionName(nameOfTitulacion);
        listaEntities.forEach((cliente) -> {
            ClienteDocumentDTO clienteDTO = new ClienteDocumentDTO();
            clienteDTO.setEntity(cliente);
            resultado.add(clienteDTO);
        });
        return resultado;
    }

    public Page<ClienteDocumentDTO> buscarClientesPorTitulacion(String nameOfTitulacion, Pageable pageable) {
        ClienteRepository clienteRepository = (ClienteRepository) getRepositorio();
        Pageable newPageable = mapearCamposOrdenacionDeEntidad(new ClienteDocumentDTO(), pageable);
        Page<ClienteDocument> resultado = clienteRepository.findClientesByTitulacionName(nameOfTitulacion, newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }


}
