package br.com.beltis.controller;

import br.com.beltis.model.Projeto;
import br.com.beltis.model.Tarefa;
import br.com.beltis.service.ProjetoService;
import br.com.beltis.service.TarefaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionScoped
public class ProjetoController extends BaseController implements Serializable {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private TarefaService tarefaService;

    private Projeto projetoSelecionado = new Projeto();
    private List<Projeto> projetos = new ArrayList<>();
    private Long projetoSelecionadoId;
    private Long projetoSelecionadoIdParaVisualizacao;
    private Projeto projetoSelecionadoParaVisualizacao;

    @PostConstruct
    public void init() {
        if (projetoService == null) {
            LOG.error("projetoService was not injected.");
        }
        if (tarefaService == null) {
            LOG.error("projetoService was not injected.");
        }
        inicializarCampos();
        listarProjetos();
    }

    public void inicializarCampos() {
        projetoSelecionado = new Projeto();
        projetos = new ArrayList<>();
        projetoSelecionadoId = null;
        projetoSelecionadoIdParaVisualizacao = null;
        projetoSelecionadoParaVisualizacao = new Projeto();
    }

    public void limparCampos() {
        if(projetoSelecionado != null) {
            projetoSelecionado = new Projeto();
        }
    }

    public void salvarProjeto() {
        try {
            if (projetoSelecionado.getId() == null) {
                projetoService.adicionarProjeto(projetoSelecionado);
                adicionarMensagem("Projeto salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            } else {
                if(StringUtils.isBlank(projetoSelecionado.getTitulo())) {
                    adicionarMensagem("Insira o titulo do Projeto!", FacesMessage.SEVERITY_ERROR);
                    return;
                }
                if(projetoSelecionado.getDataInicio() == null) {
                    adicionarMensagem("Insira a data de inicio do Projeto!", FacesMessage.SEVERITY_ERROR);
                    return;
                }
                projetoService.editarProjeto(projetoSelecionado);
                adicionarMensagem("Projeto atualizado com sucesso!", FacesMessage.SEVERITY_INFO);
            }
            listarProjetos();
            projetoSelecionado = new Projeto();
        } catch (Exception e) {
            if (projetoSelecionado.getId() == null) {
                LOG.error("Erro ao adicionar o Projeto.", e.getMessage());
                adicionarMensagem("Erro ao adicionar o projeto.", FacesMessage.SEVERITY_ERROR);
            } else {
                LOG.error("Erro ao editar o Projeto com ID - " + projetoSelecionado.getId(), e.getMessage());
                adicionarMensagem("Erro ao editar o projeto.", FacesMessage.SEVERITY_ERROR);
            }
        } finally {
            this.projetoSelecionadoId = null;
            this.projetoSelecionado = new Projeto();
        }
    }

    public String selecionarProjeto(Projeto projeto) {
        this.projetoSelecionado = projeto;
        return null;
    }

    public void excluirProjeto() {
        try {
            if(projetoSelecionadoId != null) {
                projetoService.excluirProjeto(projetoSelecionadoId);
                listarProjetos();
                cancelarExclusao();
                adicionarMensagem("Projeto excluído com sucesso!", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            LOG.error("Erro ao excluir o Projeto com ID - " + projetoSelecionadoId, e.getMessage());
            cancelarExclusao();
            adicionarMensagem("Erro ao excluir o projeto.", FacesMessage.SEVERITY_ERROR);
        } finally {
            this.projetoSelecionadoIdParaVisualizacao = null;
            this.projetoSelecionadoParaVisualizacao = new Projeto();
        }
    }

    public void prepararExclusao(Projeto projeto) {
        this.projetoSelecionadoId = projeto.getId();
    }
    public void prepararProjetoParaVisualizar(Projeto projeto) {
        this.projetoSelecionadoParaVisualizacao = projeto;
        List<Tarefa> tarefas = tarefaService.listarTarefasPorProjeto(projeto.getId());
        this.projetoSelecionadoParaVisualizacao.setTarefas(tarefas == null ? new ArrayList<>() : tarefas);
        this.projetoSelecionadoIdParaVisualizacao = projeto.getId();
    }

    public void cancelarExclusao() {
        this.projetoSelecionadoId = null;
    }

    public void fecharVisualizacaoPopup() {
        if(projetoSelecionado != null) {
            projetoSelecionado = new Projeto();
        }
        this.projetoSelecionadoIdParaVisualizacao = null;
    }

    public void listarProjetos() {
        projetos = projetoService.listarProjetos();
    }

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

    public Projeto findProjetoById(Long projetoId) {
        return projetoService.findProjetoById(projetoId);
    }

    public Projeto fetchProjetoById(Long projetoId) {
        return projetoService.fetchProjetoById(projetoId);
    }

    public Long getProjetoSelecionadoIdParaVisualizacao() {
        return projetoSelecionadoIdParaVisualizacao;
    }

    public void setProjetoSelecionadoIdParaVisualizacao(Long projetoSelecionadoIdParaVisualizacao) {
        this.projetoSelecionadoIdParaVisualizacao = projetoSelecionadoIdParaVisualizacao;
    }

    public Projeto getProjetoSelecionadoParaVisualizacao() {
        return projetoSelecionadoParaVisualizacao;
    }

    public void setProjetoSelecionadoParaVisualizacao(Projeto projetoSelecionadoParaVisualizacao) {
        this.projetoSelecionadoParaVisualizacao = projetoSelecionadoParaVisualizacao;
    }

    public Long getProjetoSelecionadoId() {
        return projetoSelecionadoId;
    }

    public void setProjetoSelecionadoId(Long projetoSelecionadoId) {
        this.projetoSelecionadoId = projetoSelecionadoId;
    }
}

