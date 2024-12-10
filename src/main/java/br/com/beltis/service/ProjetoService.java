package br.com.beltis.service;

import br.com.beltis.model.Projeto;
import br.com.beltis.repository.ProjetoRepository;
import br.com.beltis.utils.MensagemException;
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
        try {
            projetoRepository.adicionarProjeto(projeto);
            return projeto;
        } catch (Exception exception) {
            LOG.error(exception.getMessage());
            throw new MensagemException("Erro ao adicionar projeto.");
        }
    }

    public Projeto editarProjeto(Projeto projeto) {
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
            throw new MensagemException("Erro ao listar projetos. "+exception.getMessage());
        }
    }
}
