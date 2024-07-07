package muface.application.usecases.diplomas;

import muface.arch.command.ArqAbstractUseCase;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CasoUsoPersonalizado extends ArqAbstractUseCase<String, Map<String, Object>> {

    @Override
    public String execute(Map<String, Object> params) {
        StringBuilder str = new StringBuilder();
        params.keySet().forEach((key) -> {
            str.append(params.get(key));
            str.append(" ");
        });
        return str.toString();
    }

}
