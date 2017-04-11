package com.itla.insurance.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Estudiante
 */
//public class DB {
    
//}


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edwin
 */
public class DB {
    
    public static Connection conexion = null;

  // "jdbc:postgresql://localhost/northwind"
    public DB(String host,String db,String user,String pass) 
        throws SQLException, ClassNotFoundException
    {
        String url = "jdbc:postgresql://"+host+"/"+db;
        
        try {     
//            Class.forName("org.postgresl.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            conexion=DriverManager.getConnection(url,user,pass);
        } catch (SQLException exc){
            throw exc;
           //  System.err.println("** Error de Base de datos **\n"+exc.getMessage());
        }
        
    }
    
     public DB(String motor,String host,String db,String user,String pass) 
             throws SQLException
     {
        String url = "jdbc:"+motor+"://"+host+"/"+db;
        
        System.out.print(url);
        try {     
           // Class.forName("org.postgresl.Driver");
            conexion=DriverManager.getConnection(url,user,pass);
        } catch (SQLException exc){
             throw exc;
             //System.err.println("** Error de Base de datos **\n"+exc.getMessage());
        }
        
    }
    public DB() throws SQLException{
        String host = "localhost";
        String db = "seguro";
        String url = "jdbc:postgresql://"+host+"/"+db;
        String user = "postgres";
        String pass = "12345670";
        try{
            conexion=DriverManager.getConnection(url,user,pass);
        }catch(SQLException ex){
            throw ex;
        }
    } 
    
    public void execSP()
    {
        try
        {
          CallableStatement cStmt = conexion.prepareCall("{call demoSp(?, ?)}");    
          cStmt.setString(1, "abcdefg");    
          cStmt.setInt(2, 0);  
          cStmt.registerOutParameter("inOutParam", Types.INTEGER);    
        }catch(SQLException e) {}
        
    }
    
    
    public ResultSet execSelect(String sql){   
         try 
         {
            Statement comando = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = comando.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
           // Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("** Error de Base de datos en el select **\n"+ex.getMessage());
            return null;
        }
    }
        
    public void executeQuery(String query)
       {
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
//            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("** Error de Base de datos **\n"+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    

    public void CerrarConexion() {
        try {

            conexion.close();
            conexion = null;
        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  String comilla(String st)
    {
       return "'"+st+"'"; 
    }
    
    
     public void updateCoffeeSales(int valor, String name)
             //HashMap<String, Integer> salesForWeek)
        throws SQLException 
     {
         
        
        PreparedStatement updateSales = null;
        PreparedStatement updateTotal = null;

        String updateString = 
            "update  _producto set SALES = ? where COF_NAME = ?";
        
        String updateStatement =
            "update  _producto set TOTAL = TOTAL + ? where COF_NAME = ?";

        try {
            conexion.setAutoCommit(false);
            updateSales = conexion.prepareStatement(updateString);
            updateTotal = conexion.prepareStatement(updateStatement);
             
                updateSales.setInt(1,valor);
                updateSales.setString(2,name);
                updateSales.executeUpdate();
                
                updateTotal.setInt(1, valor);
                updateTotal.setString(2,name);
                updateTotal.executeUpdate();
                conexion.commit();
        
        } catch (SQLException e ) {
            e.printStackTrace();
            
            if (conexion != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conexion.rollback();
                } catch(SQLException excep) {
                     e.printStackTrace();
                }
            }
        } finally {
            if (updateSales != null) {
                updateSales.close();
            }
            if (updateTotal != null) {
                updateTotal.close();
            }
            conexion.setAutoCommit(true);
        }
    }
      
     
     
     
     public void ModelarTabla(ResultSet rs, DB dbase, JTable tabla){
        
        try{
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tabla.setModel(modelo);
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
       
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
            modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) 
            {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) 
                {
                    fila[i]=rs.getObject(i+1);
                }
             modelo.addRow(fila);
            }
            rs.close();
            dbase.CerrarConexion();
        }
        catch(Exception ex){
             ex.printStackTrace();
        }
    }
     
     public DefaultTableModel getModelo(String sql) throws SQLException{
        
        DefaultTableModel modelo = null;

        ResultSet rs = execSelect(sql);
        
          try{
            //Para establecer el modelo al JTable
            modelo = new DefaultTableModel();
            //tabla.setModel(modelo);
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
       
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
            modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) 
            {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) 
                {
                    fila[i]=rs.getObject(i+1);
                }
             modelo.addRow(fila);
            }
            rs.close();
            CerrarConexion();
        }
        catch(Exception ex){
             ex.printStackTrace();
        }
        return modelo;
    }



     
}