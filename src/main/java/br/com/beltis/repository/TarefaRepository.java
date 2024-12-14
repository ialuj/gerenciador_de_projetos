package br.com.beltis.repository;

import br.com.beltis.model.Tarefa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TarefaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void adicionarTarefa(Tarefa tarefa) throws Exception {
        entityManager.persist(tarefa);
    }

    @Transactional
    public void editarTarefa(Tarefa tarefa) throws Exception {
        entityManager.merge(tarefa);
    }

    @Transactional
    public void excluirTarefa(Long tarefaId) throws Exception {
        Tarefa tarefa = entityManager.find(Tarefa.class, tarefaId);
        if (tarefa != null) {
            tarefa.setProjeto(null);
            entityManager.remove(tarefa);
        }
    }

    public List<Tarefa> listarTarefasPorProjeto(Long projetoId) throws Exception {
        return entityManager.createQuery("FROM Tarefa WHERE projeto.id = :projetoId", Tarefa.class)
                .setParameter("projetoId", projetoId)
                .getResultList();
    }
}

