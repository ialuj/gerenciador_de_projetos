package br.com.beltis.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NavigationBean {

    public String inicio() {
        return "home";
    }

    public String produtos() {
        return "projetos";
    }

    public String tarefas() {
        return "tarefas";
    }
}

