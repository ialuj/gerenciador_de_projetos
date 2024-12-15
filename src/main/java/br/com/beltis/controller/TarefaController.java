package br.com.beltis.controller;

import br.com.beltis.model.Projeto;
import br.com.beltis.model.Tarefa;
import br.com.beltis.service.TarefaService;
import br.com.beltis.utils.TiposDePrioridade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ViewScoped
public class TarefaController extends BaseController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TarefaService tarefaService;

    private Tarefa tarefaSelecionada = new Tarefa();
    private Projeto projetoSelecionado = new Projeto();
    private List<Tarefa> tarefas = new ArrayList<>();
    private final List<TiposDePrioridade> prioridades = Arrays.asList(TiposDePrioridade.values());
    private Long tarefaSelecionadaId;
    private Long tarefaSelecionadaIdParaVisualizacao;
    private Tarefa tarefaSelecionadaParaVisualizacao;

    @PostConstruct
    public void init() {
        if (tarefaService == null) {
            LOG.error("tarefaService não foi injetado.");
        }
        inicializarCampos();
    }

    public void inicializarCampos() {
        tarefaSelecionada = new Tarefa();
        tarefas = new ArrayList<>();
        tarefaSelecionadaId = null;
        tarefaSelecionadaIdParaVisualizacao = null;
        tarefaSelecionadaParaVisualizacao = new Tarefa();
    }

    public void listarTarefasPorProjeto() {
        if (projetoSelecionado != null) {
            tarefas = tarefaService.listarTarefasPorProjeto(projetoSelecionado.getId());
            if (tarefas == null) {
                tarefas = new ArrayList<>();
            }
        } else {
            tarefas = new ArrayList<>();
        }
    }

    public void limparCampos() {
        if(tarefaSelecionada != null) {
            tarefaSelecionada = new Tarefa();
        }
    }

    public void salvarTarefa() {
        try {
            if(projetoSelecionado == null) {
                adicionarMensagem("Selecione um Projeto!", FacesMessage.SEVERITY_ERROR);
                return;
            }
            tarefaSelecionada.setProjeto(projetoSelecionado);
            if (tarefaSelecionada.getId() == null) {
                tarefaService.adicionarTarefa(tarefaSelecionada);
                adicionarMensagem("Tarefa adicionada com sucesso!", FacesMessage.SEVERITY_INFO);
            } else {
                if(StringUtils.isBlank(tarefaSelecionada.getTitulo())) {
                    adicionarMensagem("Insira o título do Projeto!", FacesMessage.SEVERITY_ERROR);
                    return;
                }
                if(tarefaSelecionada.getPrioridade() == null) {
                    adicionarMensagem("Indique a prioridade da Tarefa!", FacesMessage.SEVERITY_ERROR);
                    return;
                }
                if(tarefaSelecionada.getEstimativaHoras() == null) {
                    adicionarMensagem("Indique a estimativa (em horas) da Tarefa!", FacesMessage.SEVERITY_ERROR);
                    return;
                }
                tarefaService.editarTarefa(tarefaSelecionada);
                adicionarMensagem("Tarefa atualizada com sucesso!", FacesMessage.SEVERITY_INFO);
            }
            listarTarefasPorProjeto();
            tarefaSelecionada = new Tarefa();
            cancelarExclusao();
        } catch (Exception e) {
            if (tarefaSelecionada.getId() == null) {
                LOG.error("Erro ao adicionar a Tarefa.", e.getMessage());
                adicionarMensagem("Erro ao adicionar a Tarefa.", FacesMessage.SEVERITY_ERROR);
            } else {
                LOG.error("Erro ao editar a Tarefa com ID - " + tarefaSelecionada.getId(), e.getMessage());
                adicionarMensagem("Erro ao editar a Tarefa.", FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void selecionarTarefa(Tarefa tarefa) {
        this.tarefaSelecionada = tarefa;
    }

    public void excluirTarefa() {
        try {
            if (tarefaSelecionadaId != null) {
                tarefaService.excluirTarefa(tarefaSelecionadaId);
                listarTarefasPorProjeto();
                cancelarExclusao();
                adicionarMensagem("Tarefa excluída com sucesso!", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            LOG.error("Erro ao excluir a Tarefa com ID - " + tarefaSelecionadaId, e.getMessage());
            cancelarExclusao();
            adicionarMensagem("Erro ao excluir a Tarefa.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void fecharVisualizacaoPopup() {
        if(tarefaSelecionada != null) {
            tarefaSelecionada = new Tarefa();
        }
        tarefaSelecionadaIdParaVisualizacao = null;
    }

    public void prepararExclusao(Tarefa tarefa) {
        this.tarefaSelecionadaId = tarefa.getId();
    }
    public void prepararTarefaParaVisualizar(Tarefa tarefa) {
        this.tarefaSelecionadaParaVisualizacao = tarefa;
        this.tarefaSelecionadaIdParaVisualizacao = tarefa.getId();
    }

    public void cancelarExclusao() {
        this.tarefaSelecionadaId = null;
    }

    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public List<TiposDePrioridade> getPrioridades() {
        return prioridades;
    }

    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    public Long getTarefaSelecionadaId() {
        return tarefaSelecionadaId;
    }

    public void setTarefaSelecionadaId(Long tarefaSelecionadaId) {
        this.tarefaSelecionadaId = tarefaSelecionadaId;
    }

    public Long getTarefaSelecionadaIdParaVisualizacao() {
        return tarefaSelecionadaIdParaVisualizacao;
    }

    public void setTarefaSelecionadaIdParaVisualizacao(Long tarefaSelecionadaIdParaVisualizacao) {
        this.tarefaSelecionadaIdParaVisualizacao = tarefaSelecionadaIdParaVisualizacao;
    }

    public Tarefa getTarefaSelecionadaParaVisualizacao() {
        return tarefaSelecionadaParaVisualizacao;
    }

    public void setTarefaSelecionadaParaVisualizacao(Tarefa tarefaSelecionadaParaVisualizacao) {
        this.tarefaSelecionadaParaVisualizacao = tarefaSelecionadaParaVisualizacao;
    }
}
