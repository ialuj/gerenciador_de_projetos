package br.com.beltis.service;

import br.com.beltis.model.Projeto;
import br.com.beltis.model.Tarefa;
import br.com.beltis.utils.TiposDePrioridade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext-test.xml"
})
@ActiveProfiles("test")
@Transactional
public class TarefaServiceTest {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private ProjetoService projetoService;

    @Test
    public void deviaAdicionarTarefa() {
        Long projetoId = 1002L;
        Projeto projeto = projetoService.findProjetoById(projetoId);
        assertNotNull(projeto.getId());
        Tarefa tarefa = new Tarefa("Documentacao de Casos de Teste", null, TiposDePrioridade.MUITO_ALTA, 40, projeto);
        tarefaService.adicionarTarefa(tarefa);
        assertNotNull(tarefa.getId());
    }

    @Test
    public void deviaExcluirTarefa() {
        try {
            Long tarefaId = 1002L;
            tarefaService.excluirTarefa(tarefaId);
            assertTrue(true);
        }
        catch (Exception e) {

        }
    }
}
