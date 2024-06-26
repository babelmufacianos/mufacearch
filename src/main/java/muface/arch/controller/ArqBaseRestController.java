package muface.arch.controller;

import muface.arch.command.usecase.ArqUseCaseExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;



public class ArqBaseRestController {
    Logger logger = LoggerFactory.getLogger(ArqBaseRestController.class);

    @Autowired
    protected ArqUseCaseExecutor useCaseExecutor;

    protected final ResponseEntity<Object> executeUseCaseWithReqParams(final String useCase, Object[] paramsObject) {
        Object result = useCaseExecutor.executeUseCase(useCase, paramsObject);
        return ResponseEntity.ok(result);
    }

}
