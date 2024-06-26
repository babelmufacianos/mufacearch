package muface.arch.controller;

import muface.arch.command.ArqUseCaseDefinition;
import muface.arch.command.ArqUseCaseType;
import muface.arch.command.IArqDTO;
import muface.arch.command.usecase.ArqUseCaseExecutor;
import jakarta.transaction.Transactional;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Order(1)
public abstract class ArqBaseRestController {
    Logger logger = LoggerFactory.getLogger(ArqBaseRestController.class);
    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected ArqUseCaseExecutor useCaseExecutor;

    protected String getPrefix() {
        return "unknown-uri";
    }

    protected String getCasoUso(String key) {
        return applicationContext.getEnvironment().getProperty("arch.use-cases." + getPrefix() + "." + key);
    }

    protected String getCasoUsoInsercion() {
        return getCasoUso("insercion");
    }

    protected String getCasoUsoModificacion() {
        return getCasoUso("modificacion");
    }

    protected String getCasoUsoBorrado() {
        return getCasoUso("borrado-general");
    }

    protected String getCasoUsoBorradoPorId() {
        return getCasoUso("borrado-por-id");
    }

    protected String getCasoUsoConsultaPorId() {
        return getCasoUso("consulta-por-id");
    }

    protected String getCasoUsoConsultaGeneral() {
        return getCasoUso("consulta-general");
    }

    protected String getCasoUsoConsultaPaginada() {
        return getCasoUso("consulta-paginada");
    }

    /**** private methods ****/

    @Transactional
    protected final ResponseEntity<Object> executeUseCaseWithRequestBody(final String useCase, IArqDTO dtoInBody) {
        Object result = useCaseExecutor.executeUseCase(useCase, dtoInBody);
        return ResponseEntity.ok(result);
    }

    @Transactional
    protected final ResponseEntity<Object> executeUseCaseWithRequestId(final String useCase, Object id) {
        Object result = useCaseExecutor.executeUseCase(useCase, id);
        return ResponseEntity.ok(result);
    }

    protected final ResponseEntity<Object> executeUseQueryPagination(final String useCase, IArqDTO paramsObject,
                                                             Pageable pageable) {
        Object result = useCaseExecutor.executePaginationUseCase(useCase, paramsObject, pageable);
        return ResponseEntity.ok(result);
    }

    protected final ResponseEntity<Object> executeUseCaseWithReqParams(final String useCase, Object[] paramsObject) {
        Object result = useCaseExecutor.executeUseCase(useCase, paramsObject);
        return ResponseEntity.ok(result);
    }


    /*** comportamiento AOP **/

    @Around("@annotation(muface.arch.controller.ArqUseCaseDefinition)")
    public ResponseEntity handleUseCase(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ArqUseCaseDefinition useCaseDefinition = signature.getMethod().getAnnotation(ArqUseCaseDefinition.class);
        String useCaseValue = useCaseDefinition.value();
        ArqUseCaseType useCaseType = useCaseDefinition.type();

        Object[] args = joinPoint.getArgs();
        validateParameters(useCaseType, args);

        // Aplicar el mapeo HTTP dinÃ¡micamente (Simulado aquÃ­, en realidad no se puede aplicar dinÃ¡micamente)
        Method method = signature.getMethod();
        applyHttpMapping(method, useCaseType);

        switch (useCaseType) {
            case CREATE, UPDATE, DELETE, QUERY_BY_PARAMS:
                return executeUseCaseWithRequestBody(useCaseValue, (IArqDTO) args[0]);
            case DELETE_BY_ID, QUERY_BY_ID:
                return executeUseCaseWithRequestId(useCaseValue, args[0]);
            case QUERY_PAGINATED:
                return executeUseQueryPagination(useCaseValue, (IArqDTO) args[0], (Pageable) args[1]);
            default:
                return (ResponseEntity<Object>) joinPoint.proceed();
        }
    }

    private void validateParameters(ArqUseCaseType useCaseType, Object[] args) {
        switch (useCaseType) {
            case CREATE:
            case UPDATE:
            case DELETE:
            case QUERY_BY_PARAMS:
                if (args.length != 1 || !(args[0] instanceof IArqDTO)) {
                    throw new IllegalArgumentException("Invalid parameters for use case type: " + useCaseType);
                }
                break;
            case DELETE_BY_ID:
            case QUERY_BY_ID:
                if (args.length != 1 || !(args[0] instanceof Long)) {
                    throw new IllegalArgumentException("Invalid parameters for use case type: " + useCaseType);
                }
                break;
            case QUERY_PAGINATED:
                if (args.length != 2 || !(args[0] instanceof IArqDTO) || !(args[1] instanceof Pageable)) {
                    throw new IllegalArgumentException("Invalid parameters for use case type: " + useCaseType);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown use case type: " + useCaseType);
        }
    }

    private void applyHttpMapping(Method method, ArqUseCaseType useCaseType) {
        switch (useCaseType) {
            case CREATE:
                if (!method.isAnnotationPresent(PostMapping.class)) {
                    throw new IllegalArgumentException("Missing @PostMapping() for use case type: " + useCaseType);
                    // AquÃ­ no se puede realmente aÃ±adir dinÃ¡micamente una anotaciÃ³n a un mÃ©todo en tiempo de ejecuciÃ³n
                    // Se supone que las anotaciones ya estÃ¡n presentes o se manejan en la definiciÃ³n inicial
                }
                break;
            case UPDATE:
                if (!method.isAnnotationPresent(PutMapping.class)) {
                    throw new IllegalArgumentException("Missing @PutMapping() for use case type: " + useCaseType);
                }
                break;
            case DELETE:
                if (!method.isAnnotationPresent(DeleteMapping.class)) {
                    throw new IllegalArgumentException("Missing @DeleteMapping() for use case type: " + useCaseType);
                }
                break;
            case DELETE_BY_ID:
                if (!method.isAnnotationPresent(DeleteMapping.class)) {
                    throw new IllegalArgumentException("Missing @DeleteMapping(\"{id}\") for use case type: " + useCaseType);

                }
                if (!isAnnotationPresentWithValue(method, DeleteMapping.class, "{id}")) {
                    throw new IllegalArgumentException("Missing @DeleteMapping(\"{id}\") for use case type: " + useCaseType);
                }
                break;
            case QUERY_BY_ID:
                if (!method.isAnnotationPresent(GetMapping.class)) {
                    throw new IllegalArgumentException("Missing @GetMapping(\"{id}\") for use case type: " + useCaseType);
                }
                if (!isAnnotationPresentWithValue(method, GetMapping.class, "{id}")) {
                    throw new IllegalArgumentException("Missing @GetMapping(\"{id}\") for use case type: " + useCaseType);
                }
                break;
            case QUERY_BY_PARAMS:
                if (!method.isAnnotationPresent(PostMapping.class)) {
                    throw new IllegalArgumentException("Missing @PostMapping(\"consulta\") for use case type: " + useCaseType);
                }
                if (!isAnnotationPresentWithValue(method, PostMapping.class, "consulta")) {
                    throw new IllegalArgumentException("Missing @PostMapping(\"consulta\") for use case type: " + useCaseType);
                }
                break;
            case QUERY_PAGINATED:
                if (!method.isAnnotationPresent(PostMapping.class)) {
                    throw new IllegalArgumentException("Missing @PostMapping(\"consultapaginados\") for use case type: " + useCaseType);
                }
                if (!isAnnotationPresentWithValue(method, PostMapping.class, "consultapaginados")) {
                    throw new IllegalArgumentException("Missing @PostMapping(\"consultapaginados\") for use case type: " + useCaseType);
                }
                break;
        }
    }

    private boolean isAnnotationPresentWithValue(Method method, Class<? extends Annotation> annotationClass, String value) {
        Annotation annotation = method.getAnnotation(annotationClass);
        if (annotation == null) {
            return false;
        }

        try {
            Method valueMethod = annotation.getClass().getMethod("value");
            String[] values = (String[]) valueMethod.invoke(annotation);
            for (String v : values) {
                if (value.equals(v)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }


}
