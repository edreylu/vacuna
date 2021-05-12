/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sep.vacuna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.NamingException;

/**
 *
 * @author edward
 */
public class Conexion {
    
  public Connection getConexion() {
        Connection conn = null;
        try {
            javax.naming.Context initContext = new javax.naming.InitialContext();
            DataSource dataSource;
            try {
                dataSource = (DataSource) initContext.lookup("jdbc/vacuna");
                //System.out.println("Entro a jdbc/helpdesk");
            } catch (NamingException e) {
                dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/vacuna"); //tomcat
                //System.out.println("Entro a java:/comp/env/jdbc/helpdesk");
            }

             conn = (Connection) dataSource.getConnection();

        } catch (NamingException sqlExc) {
            //System.out.println("Error en ConexionOracle.abrirConexion()\n" + sqlExc.getMessage());
        } catch (SQLException sqlExc) {
            //System.out.println("Error en ConexionOracle.abrirConexion()\n" + sqlExc.getMessage());
        }
        return conn;
    }

    public void closeAll(ResultSet rs, PreparedStatement pst, Connection con) {
        if (rs != null) {
            try {
                rs.close();
                //System.out.println("Se cerro el resultset");
            } catch (SQLException ex) {
                System.err.println("Error al cerrar resultset: " + ex.getMessage());
            }
        }
        if (pst != null) {
            try {
                pst.close();
                //System.out.println("Se cerro el preparedStatement");
            } catch (SQLException ex) {
                System.err.println("Error al cerrar preparedStatement: " + ex.getMessage());
            }
        }
        if (con != null) {
            try {
                con.close();
                //System.out.println("Se cerro la conexion");
            } catch (SQLException ex) {
                System.err.println("Error al cerrar conexion: " + ex.getMessage());
            }
        }
    }
   
}
