package br.com.beltis.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return formatter.parse(value);
        } catch (ParseException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro de Conversão", "A data deve estar no formato dd/MM/yyyy.");
            FacesContext.getCurrentInstance().addMessage(component.getClientId(context), message);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Date) {
            return formatter.format((Date) value);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro de Conversão", "A data deve estar no formato dd/MM/yyyy.");
            FacesContext.getCurrentInstance().addMessage(component.getClientId(context), message);
            return "";
        }
    }
}
