/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.vocabulario.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Exception;
import java.util.ArrayList;
import com.pmp.vocabulario.VocabularioPJO;

/**
 *
 * @author zunig
 */
public class VocabularioDao {
    private Connection conn = null;
    public VocabularioDao(){
        try {
        conn = Conexion.getConnection();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS VOCABULARIO ("
                + "Id INTEGER PRIMARY KEY NOT NULL,"
                + "espaniol TEXT ,"
                + "ingles TEXT ,"
                + "italiano TEXT ,"
                + "aleman TEXT"
                + ");";
        Statement comandoSQLCreate = conn.createStatement();
        comandoSQLCreate.executeUpdate(sqlCreateTable);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
    public void insertPalabra(VocabularioPJO newPalabra){
        try{
            String sqlInsert = "INSERT INTO VOCABULARIO " +
                "(id, espaniol, ingles, italiano, aleman) " +
                "values (?, ?, ?, ?, ?)";
            PreparedStatement comandoInsert = conn.prepareStatement(sqlInsert);
            comandoInsert.setInt(1, newPalabra.getId());
            comandoInsert.setString(2, newPalabra.getEspaniol());
            comandoInsert.setString(3, newPalabra.getIngles());
            comandoInsert.setString(4, newPalabra.getItaliano());
            comandoInsert.setString(5, newPalabra.getAleman());
            int registroAgregados = comandoInsert.executeUpdate();
            System.out.println(registroAgregados);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    public void updatePalabra(VocabularioPJO updatePalabra) {
        try{
            String sqlStrUpdate = "UPDATE VOCABULARIO set espaniol = ?, " +
                    " ingles = ?," +
                    " italiano = ?," +
                    " aleman = ? where id = ?;";
            PreparedStatement comando = this.conn.prepareStatement(sqlStrUpdate);
            comando.setString(1, updatePalabra.getEspaniol());
            comando.setString(2, updatePalabra.getIngles());
            comando.setString(3, updatePalabra.getItaliano());
            comando.setString(4, updatePalabra.getAleman());
            comando.setInt(5, updatePalabra.getId());
            comando.executeUpdate();
            comando.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void deletePalabra(VocabularioPJO deletePalabra) {
        try{
            String sqlStrDelete = "DELETE FROM VOCABULARIO where id = ?;";
            PreparedStatement comando = this.conn.prepareStatement(sqlStrDelete);
            comando.setInt(1, deletePalabra.getId());
            comando.executeUpdate();
            comando.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<VocabularioPJO> obtenerPalabra(){
        try {
            String sqlstr = "SELECT * from VOCABULARIO;";
            Statement comando = conn.createStatement();
            ArrayList palabras = new ArrayList<VocabularioPJO>();
            ResultSet registros = comando.executeQuery(sqlstr);
            while (registros.next()) {
                VocabularioPJO vocabularioPJO = new VocabularioPJO();
                vocabularioPJO.setId(registros.getInt("id"));
                vocabularioPJO.setEspaniol(registros.getString("Espaniol"));
                vocabularioPJO.setIngles(registros.getString("Ingles"));
                vocabularioPJO.setItaliano(registros.getString("Italiano"));
                vocabularioPJO.setAleman(registros.getString("Aleman"));
                palabras.add(vocabularioPJO);
            }
            return palabras;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<VocabularioPJO>();
        }
    }
}
