package br.com.beltis.model;

import br.com.beltis.utils.TiposDePrioridade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tarefas")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "prioridade", nullable = false)
    @Enumerated(EnumType.STRING)
    private TiposDePrioridade prioridade;

    @Column(name = "estimativa_horas", nullable = false)
    private Integer estimativaHoras;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    public Tarefa() {
    }

    public Tarefa(String titulo, String descricao, TiposDePrioridade prioridade, Integer estimativaHoras, Projeto projeto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.estimativaHoras = estimativaHoras;
        this.projeto = projeto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TiposDePrioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(TiposDePrioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getEstimativaHoras() {
        return estimativaHoras;
    }

    public void setEstimativaHoras(Integer estimativaHoras) {
        this.estimativaHoras = estimativaHoras;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
