package br.com.beltis.controller;

import br.com.beltis.model.Projeto;
import br.com.beltis.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "projetoController")
@RequestScoped
@Controller
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    private Projeto projetoSelecionado = new Projeto();
    private List<Projeto> projetos;

    public ProjetoController() {
        listarProjetos();
    }

    public void adicionarProjeto() {
        try {
            if (projetoSelecionado.getId() == null) {
                projetoService.adicionarProjeto(projetoSelecionado);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto salvo com sucesso!", null));
            } else {
                projetoService.editarProjeto(projetoSelecionado);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto atualizado com sucesso!", null));
            }
            listarProjetos();
            projetoSelecionado = new Projeto();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o projeto.", e.getMessage()));
        }
    }

    public void excluirProjeto(Long id) {
        try {
            projetoService.excluirProjeto(id);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Projeto excluído com sucesso!", null));
            listarProjetos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir o projeto.", e.getMessage()));
        }
    }

    public void listarProjetos() {
        projetos = projetoService.listarProjetos();
    }

    // Getters e Setters
    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }
}

