package muface.arch.command;

import org.springframework.data.domain.Pageable;
import com.fasterxml.jackson.annotation.JsonIgnore;


public abstract class ArqAbstractDTO<ID, D> implements IArqDTO<ID, D> {

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
