package br.com.beltis.converter;

import br.com.beltis.model.Projeto;
import br.com.beltis.repository.ProjetoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@Named
@FacesConverter(value = "projetoConverter")
public class ProjetoConverter implements Converter {

    public static final Logger LOG = LoggerFactory.getLogger(ProjetoConverter.class);

    @Inject
    private ProjetoRepository projetoRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        // Tentar injetar o repositório se a injeção padrão falhar
        if (projetoRepository == null) {
            projetoRepository = (ProjetoRepository) facesContext.getApplication()
                    .evaluateExpressionGet(facesContext, "#{projetoRepository}", ProjetoRepository.class);
        }

        try {
            Long projetoId = Long.valueOf(value);
            return projetoRepository.findById(projetoId);
        } catch (Exception e) {
            LOG.error("Erro ao converter o ID do projeto", e);
            throw new ConverterException("Erro ao converter o ID do projeto", e);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof Projeto) {
            Projeto projeto = (Projeto) value;
            return projeto.getId().toString();  // Retorna o ID como String
        }

        return "";
    }
}
