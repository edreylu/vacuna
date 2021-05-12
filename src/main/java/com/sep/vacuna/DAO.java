/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sep.vacuna;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Ares
 */
public class DAO implements Serializable{

    public List<Docente> buscaDocente(String curp, String rfc) {
        Conexion conexion = new Conexion();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String query = "SELECT "
                + "    no_registro,"
                + "    apellido_paterno,"
                + "    apellido_materno,"
                + "    nombre,"
                + "    edad,"
                + "    funcion,"
                + "    cct,"
                + "    curp,"
                + "    rfc,"
                + "    domicilio,"
                + "    municipio,"
                + "    area_central_pertenece,"
                + "    tipo_comorbilidad,"
                + "    otro_padecimiento,"
                + "    estatus_vacuna_aplicada,"
                + "    nivel_educativo,"
                + "    no_corde,"
                + "    corde,"
                + "    no_sede,"
                + "    sede,"
                + "    domicilio_sede,"
                + "    fecha_vacunacion_programada,"
                + "    fecha_descarga_formato"
                + " FROM "
                + "    registro_vacuna "
                + " WHERE curp = ?"
                + "  and rfc = ?";

        List<Docente> docentes = new ArrayList<>();
        Docente docente = null;
        try{
            conn = conexion.getConexion();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, curp);
            preparedStatement.setString(2, rfc);
            rs = preparedStatement.executeQuery();
                  
            while (rs.next()) {

                docente = new Docente();
                docente.setNoRegistro(rs.getInt("NO_REGISTRO"));
                docente.setNombre(rs.getString("NOMBRE"));
                docente.setCurp(rs.getString("CURP"));
                docente.setNivelEducativo("NIVEL_EDUCATIVO");
                docente.setNoCorde(rs.getInt("NO_CORDE"));
                docente.setSede(rs.getString("CORDE"));

                docentes.add(docente);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }finally{
           conexion.closeAll(rs, preparedStatement, conn);
        }
        System.out.println("se consultaron: " + docentes.size() + " pacientes.");
        return docentes;
    }
}
