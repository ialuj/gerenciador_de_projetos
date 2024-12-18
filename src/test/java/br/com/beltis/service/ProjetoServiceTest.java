package br.com.beltis.service;

import br.com.beltis.model.Projeto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext-test.xml"
})
@ActiveProfiles("test")
@Transactional
public class ProjetoServiceTest {

    @Autowired
    private ProjetoService projetoService;

    @Test
    public void deviaAdicionarProjetoTest() {
        Projeto projeto = new Projeto("Sistema Nacional de Gestao de Recursos Hidricos", null, new Date());
        projetoService.adicionarProjeto(projeto);
        assertNotNull(projeto.getId());
        assertEquals("Sistema Nacional de Gestao de Recursos Hidricos", projeto.getTitulo());
    }

    @Test
    public void naoDeviaAdicionarProjetoTest() {
        try {
            Projeto projeto = new Projeto(null, "SNGRH", new Date());
            projetoService.adicionarProjeto(projeto);
        } catch (Exception e) {
            assertEquals("O Título do Projeto é Obrigatório!", e.getMessage());
        }
    }

    @Test
    public void deviaListarProjetosTest() {
        List<Projeto> projetos = projetoService.listarProjetos();
        assertTrue(!projetos.isEmpty());
        assertEquals(3, projetos.size());
    }

    @Test
    public void deviaBuscarProjetoPorIdTest() {
        Projeto projeto = projetoService.findProjetoById(1002L);
        assertNotNull(projeto.getId());
    }

    @Test
    public void deviaAtualizarProjetoTest() {
        Projeto projeto = projetoService.findProjetoById(1002L);
        assertNotNull(projeto.getId());
        projeto.setTitulo("Sistema Nacional de Gestao Academico");
        projetoService.editarProjeto(projeto);
        assertEquals("Sistema Nacional de Gestao Academico", projeto.getTitulo());
    }
}
