package muface.arch.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import muface.arch.service.ArqGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;

public abstract class ArqAbstractSimpleDTO implements IArqDTO {

    Logger logger = LoggerFactory.getLogger(ArqGenericService.class);

    @Autowired
    MessageSource messageSource;

    @JsonIgnore
    private Pageable pageable;

    @Override
    public Pageable getPageable() {
        return pageable;
    }

    @Override
    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
