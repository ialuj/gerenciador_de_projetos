package br.com.beltis.converter;

import br.com.beltis.utils.TiposDePrioridade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = TiposDePrioridade.class)
public class TiposDePrioridadeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return TiposDePrioridade.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Erro ao converter valor para TiposDePrioridade: " + value, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((TiposDePrioridade) value).name();
    }
}

