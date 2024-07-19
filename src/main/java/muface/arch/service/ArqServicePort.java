package muface.arch.service;

import muface.arch.command.IArqDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;


public interface ArqServicePort<D extends IArqDTO> {

    D insertar(D entity);

    D actualizar(D entity) throws NoSuchMethodException;

    D buscarPorId(Serializable id);

    List<D> buscarPorIds(List<Serializable> ids);

    String borrarEntidad(Serializable id);
    String borrarEntidad(D entity);

    String borrarEntidades(D filter);

    String borrarEntidades(List<D> entities);

    String borrarTodos();

    List<D> buscarTodos();

    List<D> buscarTodosConOrdenacion(Sort sort);

    List<D> buscarCoincidenciasEstricto(D filterObject);

    List<D> buscarCoincidenciasNoEstricto(D filterObject);

    Page<D> buscarTodosPaginados(Pageable pageable);

    Page<D> buscarCoincidenciasEstrictoPaginados(D filterObject, Pageable pageable);

    Page<D> buscarCoincidenciasNoEstrictoPaginados(D filterObject, Pageable pageable);

}
