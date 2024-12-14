package br.com.beltis;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDBConnection {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3321/gerenciador_projetos";
        String user = "root";
        String password = "gNZUV/c+P0lOeJlw7pTJfnWuS7VMF1a+";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
