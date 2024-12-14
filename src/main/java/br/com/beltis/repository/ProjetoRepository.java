package br.com.beltis.repository;

import br.com.beltis.model.Projeto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProjetoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void adicionarProjeto(Projeto projeto) throws Exception {
        entityManager.persist(projeto);
    }

    @Transactional
    public void editarProjeto(Projeto projeto) throws Exception {
        entityManager.merge(projeto);
    }

    @Transactional
    public void excluirProjeto(Long projetoId) throws Exception {
        Projeto projeto = entityManager.find(Projeto.class, projetoId);
        if(projeto != null) {
            entityManager.remove(projeto);
        }
    }

    public List<Projeto> listarProjetos() throws Exception {
        return entityManager.createQuery("from Projeto").getResultList();
    }

    public Projeto findById(Long projetoId) throws Exception {
        return entityManager.createQuery("from Projeto p where p.id = :id", Projeto.class)
                .setParameter("id", projetoId)
                .getSingleResult();
    }

    public Projeto fetchById(Long projetoId) throws Exception {
        return entityManager.createQuery("from Projeto p inner join fetch p.tarefas where p.id = :id", Projeto.class)
                .setParameter("id", projetoId)
                .getSingleResult();
    }

}
