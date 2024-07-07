package muface.arch.aspect;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import muface.arch.command.IArqCommand;
import muface.arch.command.IArqDTO;
import muface.arch.exceptions.ArqBussinessRuleException;
import muface.arch.exceptions.NotExistException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ArqUseCaseAspect {

    @Autowired
    ApplicationContext applicationContext;

    public void arqUseCaseDefinitionPointcut() {
        // Pointcut para métodos anotados con @ArqUseCaseDefinition
        int a = 0;
    }

    @Transactional
    private final ResponseEntity<Object> executeUseCase(IArqCommand useCase, Object paramObj) {
        try {
            return ResponseEntity.ok(useCase.executeInner(paramObj));
        } catch (ConstraintViolationException | NotExistException | ArqBussinessRuleException excConstraint) {
            throw excConstraint;
        } catch (Throwable exc) {
            throw new ArqBussinessRuleException(exc.getMessage(), null);
        }
    }

    @Around("@annotation(ArqUseCaseDefinition)")
    public ResponseEntity<Object> handleUseCase(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ArqUseCaseDefinition useCaseDefinition = method.getAnnotation(ArqUseCaseDefinition.class);
        String useCaseValue = useCaseDefinition.value();
        ArqUseCaseType useCaseType = useCaseDefinition.type();

        Object[] args = joinPoint.getArgs();
        Map<String, Object> params = getMethodParameters(method, args);

        // Aplicar el mapeo HTTP dinamicamente (Simulado aqui, en realidad no se puede aplicar dinamicamente)
        applyHttpMapping(method, useCaseType);
        Object useCase = applicationContext.getBean(useCaseValue.substring(0,1).toLowerCase() + useCaseValue.substring(1));
        if (useCase == null) {
            throw new RuntimeException("El caso de Uso <" + useCaseValue + "> no existe");
        }

        switch (useCaseType) {
            case CREATE, UPDATE, DELETE, DELETE_BY_ID, QUERY_BY_ID, QUERY_BY_PARAMS:
                return executeUseCase((IArqCommand) useCase, args[0]);
            case QUERY_PAGINATED:
                ((IArqDTO) args[0]).setPageable((Pageable) args[1]);
                return executeUseCase((IArqCommand) useCase, args[0]);
            case REQUEST_PARAMS:
                return executeUseCase((IArqCommand) useCase, params);
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


    private Map<String, Object> getMethodParameters(Method method, Object[] args) {
        Map<String, Object> params = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            params.put(parameters[i].getName(), args[i]);
        }
        return params;
    }


}
