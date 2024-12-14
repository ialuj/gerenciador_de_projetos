package br.com.beltis.controller;

import br.com.beltis.model.Projeto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class NavigationBean {

    @ManagedProperty("#{projetoController}")
    private ProjetoController projetoController;

    public String inicio() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("projetoSelecionado");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("projetos");
        return "home";
    }

    public String produtos() {
        return "projetos";
    }

    public String tarefas() {
        return "tarefas";
    }

    public String tarefas(Long projetoId) {
        Projeto projeto = projetoController.findProjetoById(projetoId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projetoSelecionado", projeto);
        projetoController.listarProjetos();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projetos", projetoController.getProjetos());
        return "tarefas?faces-redirect=true";
    }

    public ProjetoController getProjetoController() {
        return projetoController;
    }

    public void setProjetoController(ProjetoController projetoController) {
        this.projetoController = projetoController;
    }
}

