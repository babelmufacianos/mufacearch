package muface.arch.service;

import jakarta.annotation.PostConstruct;
import muface.arch.command.IArqDTOMapper;
import muface.arch.command.IArqDTO;
import muface.arch.exceptions.ArqBaseOperationsException;
import muface.arch.exceptions.NotExistException;
import muface.arch.repository.ArqRepository;
import muface.arch.utils.ArqConstantMessages;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Transactional
public abstract class ArqGenericService<D extends IArqDTO, ID> implements ArqServicePort<D, ID> {
    Logger logger = LoggerFactory.getLogger(ArqGenericService.class);

    @Autowired
    IArqDTOMapper<D> mapper;
    @Autowired
    MessageSource messageSource;

    protected ArqRepository<Serializable, ID> repository;

    public ArqGenericService(ArqRepository repo) {
        this.repository = repo;
    }

    protected PagingAndSortingRepository getRepositorio() {
        return this.repository;
    }

    /*@PostConstruct
    public void init() {
        // Obtener la clase de entidad asociada al repositorio
        Class<?> entityClass = getEntityClass(this.repository);
        System.out.println("Entity Class: " + entityClass.getName());
    }

    private Class<?> getEntityClass(ArqRepository<?, ?> repository) {
        // Obtener el tipo genérico del repositorio usando reflexión
        ParameterizedType parameterizedType = (ParameterizedType) repository.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];
    }*/

    private String getClassOfDTO() {
        return mapper.getNewInnerInstance().getClass().getSimpleName();
    }

    @Override
    @Transactional
    public D insertar(D entityDto) {
        try {
            Serializable entidad = mapper.getNewInnerInstance();
            entityDto.actualizarEntidad(entidad);
            Serializable entityInserted = (Serializable) this.repository.save(entidad);
            entityDto.actualizarDTO(entityInserted);
            String info = messageSource.getMessage(ArqConstantMessages.CREATED_OK,
                    new Object[]{getClassOfDTO()}, new Locale("es"));
            logger.info(info);
        } catch (ConstraintViolationException ctExc) {
            throw ctExc;
        } catch (Throwable exc) {
            String error = messageSource.getMessage(ArqConstantMessages.CREATED_KO,
                    new Object[]{getClassOfDTO(), exc.getCause()},
                    new Locale("es"));
            logger.error(error);
            throw new ArqBaseOperationsException(ArqConstantMessages.CREATED_KO,
                    new Object[]{getClassOfDTO(), exc.getCause()});
        }
        return entityDto;
    }

    @Override
    @Transactional
    public D actualizar(D entityDto) {
        try {
            Optional<?> optionalT = this.repository.findById((ID) entityDto.getId());
            if (optionalT.isPresent()) {
                Serializable searchedInBBDD = (Serializable) optionalT.orElse(null);
                entityDto.actualizarEntidad(searchedInBBDD); //actualiza los campos que no son ids
                Serializable updated = (Serializable) this.repository.save(searchedInBBDD);
                String info = messageSource.getMessage(ArqConstantMessages.UPDATED_OK,
                        new Object[]{getClassOfDTO()}, new Locale("es"));
                logger.info(info);
            } else {
                throw new NotExistException(ArqConstantMessages.RECORD_NOT_FOUND,
                        new Object[]{getClassOfDTO(), (ID) entityDto.getId()});
            }
        } catch (ConstraintViolationException | NotExistException ctExc) {
            throw ctExc;
        } catch (Throwable exc) {
            String error = messageSource.getMessage(ArqConstantMessages.UPDATED_KO,
                    new Object[]{getClassOfDTO(), exc.getCause()},
                    new Locale("es"));
            logger.error(error);
            throw new ArqBaseOperationsException(ArqConstantMessages.UPDATED_KO,
                    new Object[]{getClassOfDTO(), exc.getCause()});
        }
        return entityDto;
    }

    @Override
    @Transactional
    public String borrarEntidad(ID id) {
        if (id == null) {
            throw new NotExistException(ArqConstantMessages.ERROR_BAD_REQUEST,
                    new Object[]{getClassOfDTO(), "id: <null>"});
        }
        String info = "";
        try {
            Optional<?> optionalT = this.repository.findById(id);
            if (optionalT.isPresent()) {
                Serializable entity = (Serializable) optionalT.orElse(null);
                this.repository.delete(entity);
                info = messageSource.getMessage(ArqConstantMessages.DELETED_OK,
                        new Object[]{getClassOfDTO()}, LocaleContextHolder.getLocale());
                logger.info(messageSource.getMessage(ArqConstantMessages.DELETED_OK,
                        new Object[]{getClassOfDTO()}, new Locale("es")));
            } else {
                throw new NotExistException(ArqConstantMessages.RECORD_NOT_FOUND,
                        new Object[]{getClassOfDTO(), id});
            }
        } catch (ConstraintViolationException | NotExistException ctExc) {
            throw ctExc;
        } catch (Throwable exc) {
            String error = messageSource.getMessage(ArqConstantMessages.DELETED_KO,
                    new Object[]{getClassOfDTO(), exc.getCause()},
                    LocaleContextHolder.getLocale());
            logger.error(error);
            throw new ArqBaseOperationsException(ArqConstantMessages.DELETED_KO,
                    new Object[]{getClassOfDTO(), exc.getCause()});
        }
        return info;
    }


    @Override
    @Transactional
    public String borrarEntidad(D entityDto) {
        Object id = entityDto.getId();
        return this.borrarEntidad((ID) id);
    }

    @Override
    @Transactional
    public String borrarEntidades(D filter) {
        if (filter == null){
            return borrarTodos();
        } else {
            return borrarEntidades(this.buscarCoincidenciasEstricto(filter));
        }
    }

    @Override
    @Transactional
    public String borrarEntidades(List<D> entities) {
        String info = "";
        try{
            entities.forEach((entityDTO) -> {
                borrarEntidad(entityDTO);
            });
            info = messageSource.getMessage(ArqConstantMessages.DELETED_LIST_OK,
                    new Object[]{entities.size(), getClassOfDTO()},
                    LocaleContextHolder.getLocale());
            logger.info(messageSource.getMessage(ArqConstantMessages.DELETED_LIST_OK,
                    new Object[]{getClassOfDTO()}, new Locale("es")));
            return info;
        } catch (Throwable exc) {
            throw exc;
        }
    }

    @Override
    @Transactional
    public String borrarTodos() {
        String info = "";
        Iterable<Serializable> registros = this.repository.findAll();
        if (registros.iterator().hasNext()) {
            info = messageSource.getMessage(ArqConstantMessages.NOTHING_TO_DELETE, null,
                    LocaleContextHolder.getLocale());
            logger.info(info);
        } else {
            try {
                this.repository.deleteAll();
                info = messageSource.getMessage(ArqConstantMessages.DELETED_ALL_OK,
                        new Object[]{getClassOfDTO()}, LocaleContextHolder.getLocale());
                logger.info(messageSource.getMessage(ArqConstantMessages.DELETED_ALL_OK,
                        new Object[]{getClassOfDTO()}, new Locale("es")));
            } catch (Throwable exc) {
                String error = messageSource.getMessage(ArqConstantMessages.DELETED_ALL_KO,
                        new Object[]{getClassOfDTO(), exc.getCause()},
                        new Locale("es"));
                logger.error(error);
                throw new ArqBaseOperationsException(ArqConstantMessages.DELETED_ALL_KO,
                        new Object[]{getClassOfDTO(), exc.getCause()});
            }
        }
        return info;
    }

    @Override
    public List<D> buscarTodos() {
        Iterable<Serializable> resultado = this.repository.findAll();
        return convertirListaEntitiesADtos(resultado);
    }
    @Override
    public List<D> buscarTodosConOrdenacion(Sort sort) {
        if (sort == null) {
            logger.error("Parámetro sort es nulo");
            throw new ArqBaseOperationsException(ArqConstantMessages.ERROR_INTERNAL_SERVER_ERROR,
                    new Object[]{"Parámetro sort es nulo"});
        }
        Iterable<Serializable> resultado = this.repository.findAll(sort);
        return convertirListaEntitiesADtos(resultado);
    }

    @Override
    public D buscarPorId(ID id) {
        if (id == null) {
            throw new NotExistException(ArqConstantMessages.ERROR_BAD_REQUEST,
                    new Object[]{getClassOfDTO(), "id: <null>"});
        }
        Optional<?> optionalT = this.repository.findById(id);
        if (optionalT.isPresent()) {
            Serializable entity = (Serializable) optionalT.orElse(null);
            D dto = (D) mapper.newInstance();
            dto.actualizarDTO(entity);
            return dto;
        } else {
            throw new NotExistException(ArqConstantMessages.RECORD_NOT_FOUND,
                    new Object[]{getClassOfDTO(), id});
        }
    }

    @Override
    public List<D> buscarPorIds(List<ID> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new NotExistException(ArqConstantMessages.ERROR_BAD_REQUEST,
                    new Object[]{getClassOfDTO(), "ids: <null or empty list>"});
        }
        Iterable<Serializable> resultado = this.repository.findAllById(ids);
        return convertirListaEntitiesADtos(resultado);
    }

    @Override
    public List<D> buscarCoincidenciasEstricto(D filterObject) {
        Serializable entidad = mapper.getNewInnerInstance();
        filterObject.actualizarEntidad(entidad);
        List<Serializable> resultadoEntities = this.findByExampleStricted(entidad);
        return convertirListaEntitiesADtos(resultadoEntities);
    }

    @Override
    public List<D> buscarCoincidenciasNoEstricto(D filterObject) {
        Serializable entidad = mapper.getNewInnerInstance();
        filterObject.actualizarEntidad(entidad);
        List<Serializable> resultadoEntities = this.findByExampleNotStricted(entidad);
        return convertirListaEntitiesADtos(resultadoEntities);
    }

    /*** Consultas paginadas ***/
    @Override
    public Page<D> buscarCoincidenciasEstrictoPaginados(D filterObject, Pageable pageable) {
        Pageable newPageable = mapearCamposOrdenacionDeEntidad(filterObject, pageable);
        if (newPageable == null) {
            logger.error("Parámetro pageable es nulo");
            throw new ArqBaseOperationsException(ArqConstantMessages.ERROR_INTERNAL_SERVER_ERROR,
                    new Object[]{"Parámetro pageable es nulo"});
        }
        Serializable entidad = mapper.getNewInnerInstance();
        filterObject.actualizarEntidad(entidad);
        Page<Serializable> resultado = this.findByExampleStrictedPaginated(entidad, newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }

    @Override
    public Page<D> buscarCoincidenciasNoEstrictoPaginados(D filterObject, Pageable pageable) {
        Pageable newPageable = mapearCamposOrdenacionDeEntidad(filterObject, pageable);
        if (newPageable == null) {
            logger.error("Parámetro pageable es nulo");
            throw new ArqBaseOperationsException(ArqConstantMessages.ERROR_INTERNAL_SERVER_ERROR,
                    new Object[]{"Parámetro pageable es nulo"});
        }
        Serializable entidad = mapper.getNewInnerInstance();
        filterObject.actualizarEntidad(entidad);
        Page<Serializable> resultado = this.findByExampleNotStrictedPaginated(entidad, newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }

    @Override
    public Page<D> buscarTodosPaginados(Pageable pageable) {
        Pageable newPageable = mapearCamposOrdenacionDeEntidad(mapper.newInstance(), pageable);
        if (newPageable == null) {
            logger.error("Parámetro pageable es nulo");
            throw new ArqBaseOperationsException(ArqConstantMessages.ERROR_INTERNAL_SERVER_ERROR,
                    new Object[]{"Parámetro pageable es nulo"});
        }
        Page<Serializable> resultado = this.findAllPaginated(newPageable);
        return convertirAPageOfDtos(resultado, newPageable);
    }

    protected final Page<D> convertirAPageOfDtos(Page pageimpl, Pageable pageable) {
        List<D> listConverted = new ArrayList<>();
        pageimpl.stream().toList().forEach((entity) -> {
            D dto = (D) mapper.newInstance();
            dto.actualizarDTO(entity);
            listConverted.add(dto);
        });
        return new PageImpl<>(listConverted,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()),
                pageimpl.getTotalElements());
    }


    protected final List<D> convertirListaEntitiesADtos(Iterable<Serializable> listaOrigen) {
        List<D> listConverted = new ArrayList<>();
        listaOrigen.forEach((entity) -> {
            D dto = (D) mapper.newInstance();
            dto.actualizarDTO((D) entity);
            listConverted.add(dto);
        });
        return listConverted;
    }

    protected static Pageable mapearCamposOrdenacionDeEntidad(IArqDTO dto, Pageable pageable) {
        if (pageable.getSort() == null || pageable.getSort().isEmpty()) {
            return pageable;
        }
        Sort originalSort = pageable.getSort();
        Sort newSort = Sort.unsorted();

        for (Sort.Order order : originalSort) {
            String property = order.getProperty();
            String transformedProperty = dto.getInnerOrderField(property);
            newSort = newSort.and(Sort.by(order.getDirection(), transformedProperty));
        }
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), newSort);
    }

    /*** metodos cocinados ***/

    private Page<Serializable> findAllPaginated(Pageable pageable) {
        if (this.getRepositorio() instanceof JpaRepository<?,?>) {
            return ((JpaRepository)this.repository).findAll(pageable);
        } else if (this.getRepositorio() instanceof MongoRepository<?,?>) {
            return ((MongoRepository)this.repository).findAll(pageable);
        } else {
            throw new RuntimeException("Not supported repository type");
        }
    }

    private List<Serializable> findByExampleStricted(Serializable example) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues();
        if (this.getRepositorio() instanceof JpaRepository<?,?>) {
            return ((JpaRepository)this.repository).findAll(Example.of(example, matcher));
        } else if (this.getRepositorio() instanceof MongoRepository<?,?>) {
            return ((MongoRepository)this.repository).findAll(Example.of(example, matcher));
        } else {
            throw new RuntimeException("Not supported repository type");
        }
    }

    private Page<Serializable> findByExampleStrictedPaginated(Serializable example, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues();
        if (this.getRepositorio() instanceof JpaRepository<?,?>) {
            return ((JpaRepository)this.repository).findAll(Example.of(example, matcher), pageable);
        } else if (this.getRepositorio() instanceof MongoRepository<?,?>) {
            return ((MongoRepository)this.repository).findAll(Example.of(example, matcher), pageable);
        } else {
            throw new RuntimeException("Not supported repository type");
        }
    }

    private List<Serializable> findByExampleNotStricted(Serializable example) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Realiza búsquedas LIKE %valor%
                .withIgnoreCase(); // Ignorar mayúsculas/minúsculas
        if (this.getRepositorio() instanceof JpaRepository<?,?>) {
            return ((JpaRepository)this.repository).findAll(Example.of(example, matcher));
        } else if (this.getRepositorio() instanceof MongoRepository<?,?>) {
            return ((MongoRepository)this.repository).findAll(Example.of(example, matcher));
        } else {
            throw new RuntimeException("Not supported repository type");
        }
    }

    private Page<Serializable> findByExampleNotStrictedPaginated(Serializable example, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // Realiza búsquedas LIKE %valor%
                .withIgnoreCase(); // Ignorar mayúsculas/minúsculas

        if (this.getRepositorio() instanceof JpaRepository<?,?>) {
            return ((JpaRepository)this.repository).findAll(Example.of(example, matcher), pageable);
        } else if (this.getRepositorio() instanceof MongoRepository<?,?>) {
            return ((MongoRepository)this.repository).findAll(Example.of(example, matcher), pageable);
        } else {
            throw new RuntimeException("Not supported repository type");
        }
    }


}
