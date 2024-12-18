package br.com.beltis.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("integerConverter")
public class IntegerConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro de Conversão", "O valor deve ser um numero inteiro.");
            FacesContext.getCurrentInstance().addMessage(component.getClientId(context), message);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Integer) {
            return value.toString();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro de Conversão", "O valor deve ser um numero inteiro.");
            FacesContext.getCurrentInstance().addMessage(component.getClientId(context), message);
            return "";
        }
    }
}
