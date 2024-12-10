package br.com.beltis.controller;

import br.com.beltis.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@Controller
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;
}
