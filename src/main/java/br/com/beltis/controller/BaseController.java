package br.com.beltis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseController {

    public static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    private boolean exibirPopup;

    public boolean isExibirPopup() {
        return exibirPopup;
    }

    public void setExibirPopup(boolean exibirPopup) {
        this.exibirPopup = exibirPopup;
    }

    public void adicionarMensagem(String mensagem, FacesMessage.Severity severidade) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(severidade, mensagem, null));
        exibirPopup = true;
    }

    public void fecharPopup() {
        exibirPopup = false;
    }
}
