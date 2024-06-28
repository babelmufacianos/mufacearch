package muface.arch.aspect;

import muface.arch.command.IArqDTO;
import muface.arch.command.usecase.ArqUseCaseExecutor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class ArqUseCaseAspect {
    @Autowired
    protected ArqUseCaseExecutor useCaseExecutor;

    public void arqUseCaseDefinitionPointcut() {
        // Pointcut para métodos anotados con @ArqUseCaseDefinition
        int a = 0;
    }

    @Around("@annotation(ArqUseCaseDefinition)")
    public ResponseEntity<Object> handleUseCase(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ArqUseCaseDefinition useCaseDefinition = signature.getMethod().getAnnotation(ArqUseCaseDefinition.class);
        String useCaseValue = useCaseDefinition.value();
        ArqUseCaseType useCaseType = useCaseDefinition.type();

        Object[] args = joinPoint.getArgs();
        validateParameters(useCaseType, args);

        // Aplicar el mapeo HTTP dinamicamente (Simulado aqui, en realidad no se puede aplicar dinamicamente)
        Method method = signature.getMethod();
        applyHttpMapping(method, useCaseType);

        switch (useCaseType) {
            case CREATE, UPDATE, DELETE, QUERY_BY_PARAMS:
                return useCaseExecutor.executeUseCaseWithRequestBody(useCaseValue, (IArqDTO) args[0]);
            case DELETE_BY_ID, QUERY_BY_ID:
                return useCaseExecutor.executeUseCaseWithRequestId(useCaseValue, args[0]);
            case QUERY_PAGINATED:
                return useCaseExecutor.executeUseQueryPagination(useCaseValue, (IArqDTO) args[0], (Pageable) args[1]);
            case REQUEST_PARAMS:
                // Enviamos el array de Objects que viajan en la Request
                //TODO: map of param-name: param-value
                // Map<String, Object> mapaParams = new HashMap<>();
                // TODO: return useCaseExecutor.executeUseCaseWithReqParams(useCaseValue, mapaParams);
                return useCaseExecutor.executeUseCaseWithReqParams(useCaseValue, args);
            default:
                return (ResponseEntity<Object>) joinPoint.proceed();
        }
    }

    private void validateParameters(ArqUseCaseType useCaseType, Object[] args) {
        switch (useCaseType) {
            case CREATE, UPDATE, DELETE, QUERY_BY_PARAMS:
                if (args.length != 1 || !(args[0] instanceof IArqDTO)) {
                    throw new IllegalArgumentException("Invalid parameters for use case type: " + useCaseType);
                }
                break;
            case DELETE_BY_ID, QUERY_BY_ID:
                if (args.length != 1 || !(args[0] instanceof Long || args[0] instanceof String)) {
                    throw new IllegalArgumentException("Invalid parameters for use case type: " + useCaseType);
                }
                break;
            case QUERY_PAGINATED:
                if (args.length != 2 || !(args[0] instanceof IArqDTO) || !(args[1] instanceof Pageable)) {
                    throw new IllegalArgumentException("Invalid parameters for use case type: " + useCaseType);
                }
                break;
            case REQUEST_PARAMS:
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
                break;
            case QUERY_BY_ID:
                if (!method.isAnnotationPresent(GetMapping.class)) {
                    throw new IllegalArgumentException("Missing @GetMapping(\"{id}\") for use case type: " + useCaseType);
                }
                break;
            case QUERY_BY_PARAMS:
                if (!method.isAnnotationPresent(PostMapping.class)) {
                    throw new IllegalArgumentException("Missing @PostMapping(\"consulta\") for use case type: " + useCaseType);
                }
                break;
            case QUERY_PAGINATED:
                if (!method.isAnnotationPresent(PostMapping.class)) {
                    throw new IllegalArgumentException("Missing @PostMapping(\"consulta-paginada\") for use case type: " + useCaseType);
                }
                break;
            case REQUEST_PARAMS:
                if (!method.isAnnotationPresent(GetMapping.class)) {
                    throw new IllegalArgumentException("Missing @GetMapping(\"...\") for use case type: " + useCaseType);
                }
                break;
            default:
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
