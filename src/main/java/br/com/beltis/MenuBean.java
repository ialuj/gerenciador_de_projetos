package br.com.beltis;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class MenuBean {
    private String menuValue = "My Menu"; // Certifique-se de inicializar a propriedade

    public String getMenuValue() {
        System.out.println("Menu Value: " + menuValue);
        return menuValue;
    }

    public void setMenuValue(String menuValue) {
        this.menuValue = menuValue;
    }
}

