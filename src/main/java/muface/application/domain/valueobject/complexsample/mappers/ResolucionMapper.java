package muface.application.domain.valueobject.complexsample.mappers;

import muface.application.domain.model.complexsample.Resolucion;
import muface.application.domain.valueobject.complexsample.ResolucionDto;
import muface.arch.command.IMapper;
import org.springframework.stereotype.Component;

@Component
public class ResolucionMapper implements IMapper<ResolucionDto, Resolucion> {

    @Override
    public Resolucion toEntity(ResolucionDto dto) {
        Resolucion entidad = new Resolucion();
        entidad.setId(dto.getId());
        entidad.setAprobatoria(dto.getAprobatoria());
        entidad.setTipo(dto.getTipoResolucion());
        entidad.setFechaEmisionResolucion(dto.getFechaEmision());
        entidad.setFechaInicioPeriodoReclamacion(dto.getFecIniPeriodoAlegaciones());
        entidad.setFechaFinPeriodoReclamacion(dto.getFecFinPeriodoAlegaciones());
        entidad.setTextoJustificativo(dto.getTextoJustificativo());
        return entidad;
    }

    @Override
    public ResolucionDto toDto(Resolucion entidad) {
        ResolucionDto dto = new ResolucionDto();
        dto.setId(entidad.getId());
        dto.setAprobatoria(entidad.getAprobatoria());
        dto.setTipoResolucion(entidad.getTipo());
        dto.setFechaEmision(entidad.getFechaEmisionResolucion());
        dto.setFecIniPeriodoAlegaciones(entidad.getFechaInicioPeriodoReclamacion());
        dto.setFecFinPeriodoAlegaciones(entidad.getFechaFinPeriodoReclamacion());
        dto.setTextoJustificativo(entidad.getTextoJustificativo());
        return dto;
    }

    @Override
    public String sortMappingFields(String fieldInDto) {
        if ("aprobatoria".contentEquals(fieldInDto)) {
            return "Aprobatoria";
        } else if ("tipoResolucion".contentEquals(fieldInDto)) {
            return "tipo";
        } else if ("fechaEmision".contentEquals(fieldInDto)) {
            return "fechaEmisionResolucion";
        } else if ("fecIniPeriodoAlegaciones".contentEquals(fieldInDto)) {
            return "fechaInicioPeriodoReclamacion";
        } else if ("fecFinPeriodoAlegaciones".contentEquals(fieldInDto)) {
            return "fechaFinPeriodoReclamacion";
        } else if ("textoJustificativo".contentEquals(fieldInDto)) {
            return "textoJustificativo";
        } else {
            return "id";
        }
    }

}

