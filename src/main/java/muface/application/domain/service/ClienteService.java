package muface.application.domain.service;

import muface.application.domain.model.ClienteDocument;
import muface.application.domain.repository.clientes.ClienteRepository;
import muface.application.domain.valueobject.clientes.ClienteDTOMapper;
import muface.application.domain.valueobject.clientes.ClienteDocumentDTO;
import muface.arch.service.ArqGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService extends ArqGenericService<ClienteDocumentDTO, String> {

    public ClienteService(ClienteRepository repo, ClienteDTOMapper clienteDTOMapper) {
        super(repo, clienteDTOMapper);
    }

    /*** métodos personalizados ***/

    public List<ClienteDocumentDTO> buscarClientesPorTitulacion(String nameOfTitulacion) {
        List<ClienteDocumentDTO> resultado = new ArrayList<>();
        ClienteRepository clienteRepository = (ClienteRepository) getRepositorio();
        List<ClienteDocument> listaEntities = clienteRepository.findByOfficial(nameOfTitulacion);
        listaEntities.forEach((cliente) -> {
            ClienteDocumentDTO clienteDTO = new ClienteDocumentDTO();
            clienteDTO.actualizarDTO(cliente);
            resultado.add(clienteDTO);
        });
        return resultado;
    }

    public Page<ClienteDocumentDTO> buscarClientesPorTitulacion(String nameOfTitulacion, Pageable pageable) {
        ClienteRepository clienteRepository = (ClienteRepository) getRepositorio();
        Pageable newPageable = mapearCamposOrdenacionDeEntidad(new ClienteDocumentDTO(), pageable);
        Page<ClienteDocument> resultado = clienteRepository.findByOfficial(nameOfTitulacion, newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }


}
