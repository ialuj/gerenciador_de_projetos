package br.com.beltis.service;

import br.com.beltis.model.Projeto;
import br.com.beltis.model.Tarefa;
import br.com.beltis.repository.ProjetoRepository;
import br.com.beltis.utils.MensagemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoService {

    public static final Logger LOG = LoggerFactory.getLogger(ProjetoService.class);

    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto adicionarProjeto(Projeto projeto) {
        if(StringUtils.isBlank(projeto.getTitulo())) {
            throw new MensagemException("O Título do Projeto é Obrigatório!");
        }
        if(projeto.getDataInicio() == null) {
            throw new MensagemException("A Data de Início do Projeto é Obrigatória!");
        }
        try {
            projetoRepository.adicionarProjeto(projeto);
            return projeto;
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao adicionar projeto.");
        }
    }

    public Projeto editarProjeto(Projeto projeto) {
        if(StringUtils.isBlank(projeto.getTitulo())) {
            throw new MensagemException("O Título do Projeto é Obrigatório!");
        }
        if(projeto.getDataInicio() == null) {
            throw new MensagemException("A Data de Início do Projeto é Obrigatória!");
        }
        try {
            projetoRepository.editarProjeto(projeto);
            return projeto;
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao editar projeto.");
        }
    }

    public void excluirProjeto(Long projetoId) {
        try {
            projetoRepository.excluirProjeto(projetoId);
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao excluir projeto com ID: " + projetoId);
        }
    }

    public List<Projeto> listarProjetos() {
        try {
            return projetoRepository.listarProjetos();
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao listar projetos.");
        }
    }

    public Projeto findProjetoById(Long projetoId) {
        try {
            return projetoRepository.findById(projetoId);
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Projeto com ID - " + projetoId + " nao encontrado.");
        }
    }

    public Projeto fetchProjetoById(Long projetoId) {
        try {
            return projetoRepository.fetchById(projetoId);
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Projeto com ID - " + projetoId + " nao encontrado.");
        }
    }
}
