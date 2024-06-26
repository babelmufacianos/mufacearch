package muface.arch.controller;

import muface.arch.command.IArqDTO;
import muface.arch.command.usecase.ArqUseCaseExecutor;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;



public class ArqBaseRestController {
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

}
