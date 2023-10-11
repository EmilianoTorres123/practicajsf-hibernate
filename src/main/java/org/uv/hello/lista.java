/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.hello;

/**
 *
 * @author aaron-emiliano
 */
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author aaron-emiliano
 */
public class lista {
    public static void mostrarRegistros(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            conexion cx=conexion.getConexion();
            
            String sql = "SELECT * FROM empleado";
            Statement statement = cx.con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            request.setAttribute("registros", resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}