package br.com.beltis.utils;

public enum TiposDePrioridade {

    MUITO_ALTA("Muito Alta"),
    ALTA("Alta"),
    BAIXA("Baixa"),
    MUITO_BAIXA("Muito Baixa");

    private final String descricao;

    TiposDePrioridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

