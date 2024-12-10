package br.com.beltis.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NavigationBean {

    public String inicio() {
        return "index.xhtml?faces-redirect=true";
    }

    public String produtos() {
        return "/WEB-INF/paginas/projetos.xhtml?faces-redirect=true";
    }

    public String tarefas() {
        return "/WEB-INF/paginas/tarefas.xhtml?faces-redirect=true";
    }
}

