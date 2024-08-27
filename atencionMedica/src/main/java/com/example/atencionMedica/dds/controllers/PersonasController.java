package com.example.atencionMedica.dds.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonasController {

    @GetMapping()
    public List<String> getPersonas() {
        List<String> personas   = new ArrayList<>();
        Connection connection   = null;
        Statement statement     = null;
        ResultSet resultSet     = null;

        try {
            // Configurar la conexión a la base de datos
            String url      = "jdbc:mysql://localhost:3306/ricosanonatural";
            String user     = "root";
            String password = "fran123";
            connection      = DriverManager.getConnection(url, user, password);

            // Crear y ejecutar la consulta SQL
            statement = connection.createStatement();
            String sql = "SELECT * FROM plan_alimenticio";
            resultSet = statement.executeQuery(sql);

            // Procesar los resultados de la consulta
            while (resultSet.next()) {
                String nombre = resultSet.getString("id");
                personas.add(nombre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return personas;
    }
}