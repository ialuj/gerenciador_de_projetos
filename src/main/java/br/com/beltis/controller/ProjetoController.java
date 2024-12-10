package br.com.beltis.controller;

import br.com.beltis.model.Projeto;
import br.com.beltis.service.ProjetoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@Controller
@RequestScoped
public class ProjetoController {

    public static final Logger LOG = LoggerFactory.getLogger(ProjetoController.class);

    @Autowired
    private ProjetoService projetoService;

    private Projeto projetoSelecionado = new Projeto();
    private List<Projeto> projetos;

    @PostConstruct
    public void init() {
        if (projetoService == null) {
            throw new IllegalStateException("projetoService was not injected.");
        }
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
        LOG.info("Lista de Projetos");
        projetos = projetoService.listarProjetos();
        LOG.info("Projetos encontrados - " + projetos.size());
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

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }
}

