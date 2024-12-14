// Função para abrir o popup e preencher os dados
function openPopup(task) {
    // Preencher os campos com os dados da tarefa
    document.getElementById('projectTitle').innerText = task.projetoTitulo;
    document.getElementById('startDate').innerText = task.projetoDataInicio;
    document.getElementById('taskTitle').innerText = task.titulo;
    document.getElementById('taskDescription').innerText = task.descricao;
    document.getElementById('taskPriority').innerText = task.prioridade;
    document.getElementById('taskEstimate').innerText = task.estimativaHoras;

    // Mostrar o popup
    document.getElementById('visualizacaoPopup').style.display = 'flex';
}

// Função para fechar o popup
function closePopup() {
    document.getElementById('visualizacaoPopup').style.display = 'none';
}

// Exemplo de como abrir o popup
document.querySelectorAll('.view-task-btn').forEach(function(button) {
    button.addEventListener('click', function() {
        const task = {
            projetoTitulo: 'Projeto Exemplo',
            projetoDataInicio: '2024-01-01',
            titulo: 'Tarefa Exemplo',
            descricao: 'Descrição da tarefa.',
            prioridade: 'Alta',
            estimativaHoras: '8'
        };

        openPopup(task);
    });
});
