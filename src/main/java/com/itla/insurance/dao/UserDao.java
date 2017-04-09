/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.insurance.dao;

import com.itla.insurance.dto.UserDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sulenni
 */
public class UserDao {
    public  DB dbase = null;
    private Connection conexion=null;
    
    public UserDao() throws Exception{
//        String host = "localhost";
//        String db = "seguro";
//        String url = "jdbc:postgresql://"+host+"/"+db;
//        String user = "postgres";
//        String pass = "12345670";
        
        try {
            dbase = new DB();
        } catch (Exception exc) {
            throw exc;
        }
        
    }
    
    public UserDao(String host,String db,String user,String pass) throws SQLException{
       
        String url = "jdbc:postgresql://"+host+"/"+db;
        
        try {     
            conexion=DriverManager.getConnection(url,user,pass);
        } catch (SQLException exc){
            throw exc;
        }
        
    }
    
    public boolean LogIn(String userName, String passUser) throws Exception{
    
        PreparedStatement validar=null;
        String sqlValidar="select id, name, pass from public.user where name=? and pass=?";
        boolean b;
        
        ResultSet rsLogin;
        
        try {
            DB.conexion.setAutoCommit(false);
            
            validar = DB.conexion.prepareStatement(sqlValidar);
            
            validar.setString(1, userName);
            validar.setString(2, passUser);
            
            rsLogin = validar.executeQuery();
            
            if(rsLogin.next()){
                b=true;
            }else{
                b=false;
            }
                        
        } catch (Exception exc) {
            throw exc;     
        }
        
        DB.conexion.close();
        
      //  return resSession.CrearSession(resDto);
        return b;
    } 
    
    public String insertUser(String userName, String passUser) throws Exception{
    
        PreparedStatement nuevoUser=null;
        String sqlInsertar="insert into public.user (name, pass) values (?, ?)";
             
        try {
            DB.conexion.setAutoCommit(false);
            nuevoUser = DB.conexion.prepareStatement(sqlInsertar);
            
            nuevoUser.setString(1, userName);
            nuevoUser.setString(2, passUser);
            
            nuevoUser.executeUpdate();
            
            DB.conexion.commit(); 
            
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String changeUser(UserDto oldUser, String newUserName) throws Exception{
    
    PreparedStatement cambiarUser=null;
        String sqlCambiarUse="";
        
        try {
            cambiarUser = DB.conexion.prepareStatement(sqlCambiarUse);
            
            cambiarUser.setString(1, oldUser.getName());
            cambiarUser.setString(2, oldUser.getPass());
            cambiarUser.setString(3, newUserName);
            
            cambiarUser.execute();
            
            DB.conexion.commit();
            
        } catch (Exception e) {
            throw e;
        }
        
        return null;
        
    }
}
