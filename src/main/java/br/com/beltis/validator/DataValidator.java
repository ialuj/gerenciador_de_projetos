package br.com.beltis.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || value.toString().isEmpty()) {
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);  // Desabilitar interpretação leniente de datas
        try {
            Date data = sdf.parse(value.toString());
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("A data deve estar no formato dd/MM/yyyy.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

