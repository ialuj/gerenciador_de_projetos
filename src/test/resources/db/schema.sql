INSERT INTO projetos(id, data_inicio, titulo, descricao)
VALUES
    (1001, NOW(), 'SGP', 'Sistema de Gestao de Projetos'),
    (1002, NOW(), 'SGC', 'Sistema de Gestao Academico'),
    (1003, NOW(), 'SNGI', 'Sistema Nacional de Gestao de Impostos');

INSERT INTO tarefas(id, titulo, descricao, prioridade, estimativa_horas, projeto_id)
VALUES
    (1001, 'Documentacao do Modelo de Negocios', null, 'Muito Alta', 60, (SELECT p.ID FROM projetos p WHERE p.ID = 1001)),
    (1002, 'Documentacao do Requisitos Funcionais', null, 'Muito Alta', 80, (SELECT p.ID FROM projetos p WHERE p.ID = 1002));