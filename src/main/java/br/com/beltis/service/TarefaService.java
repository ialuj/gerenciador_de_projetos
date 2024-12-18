package br.com.beltis.service;

import br.com.beltis.model.Tarefa;
import br.com.beltis.repository.TarefaRepository;
import br.com.beltis.utils.MensagemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    public static final Logger LOG = LoggerFactory.getLogger(TarefaService.class);

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        if(StringUtils.isBlank(tarefa.getTitulo())) {
            throw new MensagemException("O Título da Tarefa é Obrigatório!");
        }
        if(tarefa.getPrioridade() == null) {
            throw new MensagemException("A Prioridade da Tarefa é Obrigatória!");
        }
        if(tarefa.getEstimativaHoras() == null) {
            throw new MensagemException("A Estimativa da Tarefa é Obrigatória!");
        }
        try {
            tarefaRepository.adicionarTarefa(tarefa);
            return tarefa;
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao adicionar tarefa.");
        }
    }

    public Tarefa editarTarefa(Tarefa tarefa) {
        if(StringUtils.isBlank(tarefa.getTitulo())) {
            throw new MensagemException("O Título da Tarefa é Obrigatório!");
        }
        if(tarefa.getPrioridade() == null) {
            throw new MensagemException("A Prioridade da Tarefa é Obrigatória!");
        }
        if(tarefa.getEstimativaHoras() == null) {
            throw new MensagemException("A Estimativa da Tarefa é Obrigatória!");
        }
        try {
            tarefaRepository.editarTarefa(tarefa);
            return tarefa;
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao editar tarefa.");
        }
    }

    public void excluirTarefa(Long tarefaId) {
        try {
            tarefaRepository.excluirTarefa(tarefaId);
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao excluir tarefa com ID: " + tarefaId);
        }
    }

    public List<Tarefa> listarTarefasPorProjeto(Long projetoId) {
        try {
            return tarefaRepository.listarTarefasPorProjeto(projetoId);
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao listar tarefas por projeto com ID: " + projetoId);
        }
    }
}
