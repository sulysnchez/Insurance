/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itla.insurance.dao;

import com.itla.insurance.dto.AfiliadoDto;
import com.itla.insurance.dto.AnalisisDto;
import com.itla.insurance.dto.CiudadDto;
import com.itla.insurance.dto.EspecialidadDto;
import com.itla.insurance.dto.EstudioDto;
import com.itla.insurance.dto.InstitucionDto;
import com.itla.insurance.dto.OcupacionDto;
import com.itla.insurance.dto.PrestadoresDto;
import com.itla.insurance.dto.ProvinciaDto;
import com.itla.insurance.dto.ReclamacionDto;
import com.itla.insurance.dto.ServicioDto;
import com.itla.insurance.dto.SexoDto;
import com.itla.insurance.dto.Tipo_AfiliacionDto;
import com.itla.insurance.dto.Tipo_CoberturaDto;
import com.itla.insurance.dto.Tipo_IdentificacionDto;
import com.itla.insurance.dto.Tipo_ParentezcoDto;
import com.itla.insurance.dto.Tipo_PlanDto;
import com.itla.insurance.dto.Tipo_PssDto;
import com.itla.insurance.dto.Tipo_SangreDto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sulenni
 */
public class AfiliadoDao {
    
    public  DB dbase = null;
    private Connection conexion=null;
    
    public AfiliadoDao() throws Exception{
        String host = "localhost";
        String db = "seguro";
        String url = "jdbc:postgresql://"+host+"/"+db;
        String user = "postgres";
        String pass = "12345670";
        
        try {
            dbase = new DB();
        } catch (Exception exc) {
            throw exc;
        }  
    }
  
    public void deleteAfiliado(AfiliadoDto afiliado){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM afiliado\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, afiliado.getId());
            ps.execute();
            
            DB.conexion.commit();
        } catch (Exception e) {
            try {
                DB.conexion.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AfiliadoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void deleteAnalisis(AnalisisDto analisis){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM analisis\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, analisis.getId());
            ps.execute();
            
            DB.conexion.commit();
        } catch (Exception e) {
            try {
                DB.conexion.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AfiliadoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String insertAfiliado( AfiliadoDto afiliado) throws Exception{
    
        PreparedStatement nuevoAfiliado=null;
        String sqlInsertar="insert into public.afiliado (nombre, direccion, telefono, sexo, no_identificacion,"
                          + "                            id_tipo_identificacion, id_tipo_afiliacion, id_tipo_plan, id_tipo_cobertura,"
                          + "                            id_tipo_sangre, no_seguro_social, fecha_nacimiento, edad, id_ciudad,"
                          + "                            id_provincia, nombre_titular, id_parentezco, id_ocupacion) "
                          + "                            values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
          
        try {
            DB.conexion.setAutoCommit(false);
            nuevoAfiliado = DB.conexion.prepareStatement(sqlInsertar);
            java.util.Date fechaUtil = afiliado.getFechaNacimiento();
            java.sql.Date fechaSQL = new Date(fechaUtil.getYear(), fechaUtil.getMonth(), fechaUtil.getDay());
            nuevoAfiliado.setString(1,  afiliado.getNombre());
            nuevoAfiliado.setString(2,  afiliado.getDireccion());
            nuevoAfiliado.setString(3,  afiliado.getTelefono());
            nuevoAfiliado.setInt   (4,  afiliado.getIdSexo());
            nuevoAfiliado.setString(5,  afiliado.getIdentificacion());
            nuevoAfiliado.setInt   (6,  afiliado.getIdTipoIdentificacion());
            nuevoAfiliado.setInt   (7,  afiliado.getIdTipoAfiliacion());
            nuevoAfiliado.setInt   (8,  afiliado.getIdTipoPlan());
            nuevoAfiliado.setInt   (9, afiliado.getIdTipoCobertura());
            nuevoAfiliado.setInt   (10, afiliado.getIdTipoSangre());
            nuevoAfiliado.setInt   (11, afiliado.getNo_SeguroSocial());
            nuevoAfiliado.setDate  (12, fechaSQL);
            nuevoAfiliado.setInt   (13, afiliado.getEdad());
            nuevoAfiliado.setInt   (14, afiliado.getIdCiudad());
            nuevoAfiliado.setInt   (15, afiliado.getIdProvincia());
            nuevoAfiliado.setString(16, afiliado.getCedTitular());
            nuevoAfiliado.setInt   (17, afiliado.getIdParentezco());
            nuevoAfiliado.setInt   (18, afiliado.getIdOcupacion());
            
            nuevoAfiliado.executeUpdate();
            
            DB.conexion.commit(); 
            
            
            
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateAfiliado( AfiliadoDto afiliado) throws Exception{
    
        PreparedStatement actualizaAfiliado=null;   
        String sqlActualizar =  "UPDATE afiliado SET nombre=?, direccion=?, telefono=?, sexo=?, "
                              + " no_identificacion=?, id_tipo_identificacion=?, id_tipo_afiliacion=?, "
                              + "+ id_tipo_plan=?, id_tipo_cobertura=?, id_tipo_sangre=?, no_seguro_social=?, "
                              + "+ fecha_nacimiento=?, edad=?, id_ciudad=?, id_provincia=?, nombre_titular=?, "
                              + "+ no_contrato=?, id_parentezco=?, id_ocupacion=? "
                              + "+ WHERE no_poliza=?";

        try {
            DB.conexion.setAutoCommit(false);
            actualizaAfiliado = DB.conexion.prepareStatement(sqlActualizar);
            
            java.util.Date fechaUtil = afiliado.getFechaNacimiento();
            java.sql.Date fechaSQL = new Date(fechaUtil.getYear(), fechaUtil.getMonth(), fechaUtil.getDay());
            
            actualizaAfiliado.setInt   (20,  afiliado.getNo_Poliza());
            actualizaAfiliado.setString(1,  afiliado.getNombre());
            actualizaAfiliado.setString(2,  afiliado.getDireccion());
            actualizaAfiliado.setString(3,  afiliado.getTelefono());
            actualizaAfiliado.setInt   (4,  afiliado.getIdSexo());
            actualizaAfiliado.setString(5,  afiliado.getIdentificacion());
            actualizaAfiliado.setInt   (6,  afiliado.getIdTipoIdentificacion());
            actualizaAfiliado.setInt   (7,  afiliado.getIdTipoAfiliacion());
            actualizaAfiliado.setInt   (8,  afiliado.getIdTipoPlan());
            actualizaAfiliado.setInt   (9, afiliado.getIdTipoCobertura());
            actualizaAfiliado.setInt   (10, afiliado.getIdTipoSangre());
            actualizaAfiliado.setInt   (11, afiliado.getNo_SeguroSocial());
            actualizaAfiliado.setDate  (12, fechaSQL);
            actualizaAfiliado.setInt   (13, afiliado.getEdad());
            actualizaAfiliado.setInt   (14, afiliado.getIdCiudad());
            actualizaAfiliado.setInt   (15, afiliado.getIdProvincia());
            actualizaAfiliado.setString(16, afiliado.getCedTitular());
            actualizaAfiliado.setString(17, afiliado.getNo_Contrato());
            actualizaAfiliado.setInt   (18, afiliado.getIdParentezco());
            actualizaAfiliado.setInt   (19, afiliado.getIdOcupacion());
            
            actualizaAfiliado.executeUpdate();
            
            DB.conexion.commit(); 
            
            
            
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public List<Tipo_IdentificacionDto> GetAllTipoIdentificacion() throws SQLException{
        PreparedStatement LlenarTipoIdentificacion = null;
        String sqlLlenarTipoIdentificacion = "select id, nombre from tipo_identificacion order by id asc";
        ResultSet rs = null;
        List<Tipo_IdentificacionDto> tiposidentificacion = new ArrayList<Tipo_IdentificacionDto>();
        Tipo_IdentificacionDto tipoidentificacion;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipoIdentificacion = DB.conexion.prepareStatement(sqlLlenarTipoIdentificacion);
        
        rs = LlenarTipoIdentificacion.executeQuery();
        
        while(rs.next()){
            tipoidentificacion = new Tipo_IdentificacionDto();
            
            tipoidentificacion.setId(rs.getInt(1));
            tipoidentificacion.setNombre(rs.getString(2));
            
            tiposidentificacion.add(tipoidentificacion);
        }
              
        return tiposidentificacion;
    }
    
    public List<SexoDto> GetAllSexo() throws SQLException{
        PreparedStatement LlenarSexo = null;
        String sqlLlenarSexo = "select id, nombre from sexo order by id asc";
        ResultSet rs = null;
        List<SexoDto> sexospersona = new ArrayList<SexoDto>();
        SexoDto sexopersona;
        
        DB.conexion.setAutoCommit(false);
        LlenarSexo = DB.conexion.prepareStatement(sqlLlenarSexo);
        
        rs = LlenarSexo.executeQuery();
        
        while(rs.next()){
            sexopersona = new SexoDto();
            
            sexopersona.setId(rs.getInt(1));
            sexopersona.setNombre(rs.getString(2));
            
            sexospersona.add(sexopersona);
        }
              
        return sexospersona;
    }
    
    public List<CiudadDto> GetAllCiudad() throws SQLException{
        PreparedStatement LlenarCiudad = null;
        String sqlLlenarCiudad = "select id, nombre from ciudad order by id asc";
        ResultSet rs = null;
        List<CiudadDto> ciudades = new ArrayList<CiudadDto>();
        CiudadDto ciudad;
        
        DB.conexion.setAutoCommit(false);
        LlenarCiudad = DB.conexion.prepareStatement(sqlLlenarCiudad);
        
        rs = LlenarCiudad.executeQuery();
        
        while(rs.next()){
            ciudad = new CiudadDto();
            
            ciudad.setId(rs.getInt(1));
            ciudad.setNombre(rs.getString(2));
            
            ciudades.add(ciudad);
        }
              
        return ciudades;
    }
    public List<CiudadDto> GetAllCiudadByProvincia(Integer idProvincia) throws SQLException{
       
        PreparedStatement LlenarCiudad = null;
        String sqlLlenarCiudad = "select id, nombre from ciudad where id_provincia = ? order by id asc";
        ResultSet rs = null;
        List<CiudadDto> ciudades = new ArrayList<CiudadDto>();
        CiudadDto ciudad;
        
        DB.conexion.setAutoCommit(false);
        LlenarCiudad = DB.conexion.prepareStatement(sqlLlenarCiudad);
        LlenarCiudad.setInt(1, idProvincia);
        rs = LlenarCiudad.executeQuery();
        
        while(rs.next()){
            ciudad = new CiudadDto();
            
            ciudad.setId(rs.getInt(1));
            ciudad.setNombre(rs.getString(2));
            
            ciudades.add(ciudad);
        }
              
        return ciudades;
    }
    
    public List<ProvinciaDto> GetAllProvincia() throws SQLException{
        PreparedStatement LlenarProvincia = null;
        String sqlLlenarProvincia = "select id, nombre from Provincia order by id asc";
        ResultSet rs = null;
        List<ProvinciaDto> provincias = new ArrayList<ProvinciaDto>();
        ProvinciaDto provincia;
        
        DB.conexion.setAutoCommit(false);
        LlenarProvincia = DB.conexion.prepareStatement(sqlLlenarProvincia);
        
        rs = LlenarProvincia.executeQuery();
        
        while(rs.next()){
            provincia = new ProvinciaDto();
            
            provincia.setId(rs.getInt(1));
            provincia.setNombre(rs.getString(2));
            
            provincias.add(provincia);
        }
              
        return provincias;
    }
    
    public List<OcupacionDto> GetAllOcupacion() throws SQLException{
        PreparedStatement LlenarOcupacion = null;
        String sqlLlenarOcupacion = "select id, nombre from Ocupacion order by id asc";
        ResultSet rs = null;
        List<OcupacionDto> ocupaciones = new ArrayList<OcupacionDto>();
        OcupacionDto ocupacion;
        
        DB.conexion.setAutoCommit(false);
        LlenarOcupacion= DB.conexion.prepareStatement(sqlLlenarOcupacion);
        
        rs = LlenarOcupacion.executeQuery();
        
        while(rs.next()){
            ocupacion = new OcupacionDto();
            
            ocupacion.setId(rs.getInt(1));
            ocupacion.setNombre(rs.getString(2));
            
            ocupaciones.add(ocupacion);
        }
              
        return ocupaciones;
    }
    
    
    public List<Tipo_PssDto> GetAllTipoPss() throws SQLException{
        PreparedStatement LlenarTipoPss = null;
        String sqlLlenarTipoPss = "select id, nombre from tipo_pss order by id asc";
        ResultSet rs = null;
        List<Tipo_PssDto> tiposPss = new ArrayList<Tipo_PssDto>();
        Tipo_PssDto tipoPss;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipoPss= DB.conexion.prepareStatement(sqlLlenarTipoPss);
        
        rs = LlenarTipoPss.executeQuery();
        
        while(rs.next()){
            tipoPss = new Tipo_PssDto();
            
            tipoPss.setId(rs.getInt(1));
            tipoPss.setNombre(rs.getString(2));
            
            tiposPss.add(tipoPss);
        }
              
        return tiposPss;
    }
    
    
    public List<InstitucionDto> GetAllInstitucion() throws SQLException{
        PreparedStatement LlenarInstitucion = null;
        String sqlLlenarInstitucion = "select id, nombre, telefono, direccion from institucion order by id asc";
        ResultSet rs = null;
        List<InstitucionDto> instituciones = new ArrayList<InstitucionDto>();
        InstitucionDto institucion;
        
        DB.conexion.setAutoCommit(false);
        LlenarInstitucion = DB.conexion.prepareStatement(sqlLlenarInstitucion);
        
        rs = LlenarInstitucion.executeQuery();
        
        while(rs.next()){
            institucion = new InstitucionDto();
            
            institucion.setId(rs.getInt(1));
            institucion.setNombre(rs.getString(2));
            institucion.setTelefono(rs.getString(3));
            institucion.setDireccion(rs.getString(4));
            
            instituciones.add(institucion);
        }
              
        return instituciones;
    }
    
    public List<Tipo_AfiliacionDto> GetAllTipo_Afiliacion() throws SQLException{
        PreparedStatement LlenarTipo_Afiliacion = null;
        String sqlLlenarTipo_Afiliacion = "select id, nombre from tipo_afiliacion order by id asc";
        ResultSet rs = null;
        List<Tipo_AfiliacionDto> tiposafiliacion = new ArrayList<Tipo_AfiliacionDto>();
        Tipo_AfiliacionDto tipoafiliacion;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipo_Afiliacion = DB.conexion.prepareStatement(sqlLlenarTipo_Afiliacion);
        
        rs = LlenarTipo_Afiliacion.executeQuery();
        
        while(rs.next()){
            tipoafiliacion = new Tipo_AfiliacionDto();
            
            tipoafiliacion.setId(rs.getInt(1));
            tipoafiliacion.setNombre(rs.getString(2));
            
            tiposafiliacion.add(tipoafiliacion);
        }
              
        return tiposafiliacion;
    }
    
    public List<Tipo_PlanDto> GetAllTipo_Plan() throws SQLException{
        PreparedStatement LlenarTipo_Plan = null;
        String sqlLlenarTipo_Plan = "select id, nombre from tipo_plan order by id asc";
        ResultSet rs = null;
        List<Tipo_PlanDto> planes = new ArrayList<Tipo_PlanDto>();
        Tipo_PlanDto tipoplan;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipo_Plan = DB.conexion.prepareStatement(sqlLlenarTipo_Plan);
        
        rs = LlenarTipo_Plan.executeQuery();
        
        while(rs.next()){
            tipoplan = new Tipo_PlanDto();
            
            tipoplan.setId(rs.getInt(1));
            tipoplan.setNombre(rs.getString(2));
            
            planes.add(tipoplan);
        }
              
        return planes;
    }
    
    public List<Tipo_CoberturaDto> GetAllTipo_Cobertura() throws SQLException{
        PreparedStatement LlenarTipo_Cobertura = null;
        String sqlLlenarTipo_Cobertura = "select id, porciento from tipo_cobertura order by id asc";
        ResultSet rs = null;
        List<Tipo_CoberturaDto> coberturas = new ArrayList<Tipo_CoberturaDto>();
        Tipo_CoberturaDto tipocobertura;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipo_Cobertura = DB.conexion.prepareStatement(sqlLlenarTipo_Cobertura);
        
        rs = LlenarTipo_Cobertura.executeQuery();
        
        while(rs.next()){
            tipocobertura = new Tipo_CoberturaDto();
            
            tipocobertura.setId(rs.getInt(1));
            tipocobertura.setPorciento(rs.getInt(2));
            
            coberturas.add(tipocobertura);
        }
              
        return coberturas;
    }
    
    public List<Tipo_ParentezcoDto> GetAllTipo_Parentezco() throws SQLException{
        PreparedStatement LlenarTipo_Parentezco = null;
        String sqlLlenarTipo_Parentezco = "select id, nombre from tipo_parentezco order by id asc";
        ResultSet rs = null;
        List<Tipo_ParentezcoDto> tiposparentezco = new ArrayList<Tipo_ParentezcoDto>();
        Tipo_ParentezcoDto tipoparentezco;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipo_Parentezco = DB.conexion.prepareStatement(sqlLlenarTipo_Parentezco);
        
        rs = LlenarTipo_Parentezco.executeQuery();
        
        while(rs.next()){
            tipoparentezco = new Tipo_ParentezcoDto();
            
            tipoparentezco.setId(rs.getInt(1));
            tipoparentezco.setNombre(rs.getString(2));
            
            tiposparentezco.add(tipoparentezco);
        }
              
        return tiposparentezco;
    }
    
    public List<Tipo_SangreDto> GetAllTipo_Sangre() throws SQLException{
        PreparedStatement LlenarTipo_Sangre = null;
        String sqlLlenarTipo_Sangre = "select id, nombre from tipo_sangre order by id asc";
        ResultSet rs = null;
        List<Tipo_SangreDto> tipossangre = new ArrayList<Tipo_SangreDto>();
        Tipo_SangreDto tiposangre;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipo_Sangre = DB.conexion.prepareStatement(sqlLlenarTipo_Sangre);
        
        rs = LlenarTipo_Sangre.executeQuery();
        
        while(rs.next()){
            tiposangre = new Tipo_SangreDto();
            
            tiposangre.setId(rs.getInt(1));
            tiposangre.setNombre(rs.getString(2));
            
            tipossangre.add(tiposangre);
        }
              
        return tipossangre;
    } 
    
    public List<AfiliadoDto> GetAllAfiliado() throws SQLException{
        PreparedStatement LlenarAfiliado = null;
        String sqlLlenarAfiliado = "SELECT id, no_poliza, nombre, direccion, telefono, sexo, no_identificacion, \n" +
                                   "       id_tipo_identificacion, id_tipo_afiliacion, id_tipo_plan, id_tipo_cobertura, \n" +
                                   "       id_tipo_sangre, no_seguro_social, fecha_nacimiento, edad, id_ciudad, \n" +
                                   "       id_provincia, nombre_titular, no_contrato, id_parentezco, id_ocupacion\n" +
                                   "  FROM afiliado order by id asc";
        ResultSet rs = null;
        List<AfiliadoDto> afiliado = new ArrayList<AfiliadoDto>();
        AfiliadoDto afiliados;
        
        DB.conexion.setAutoCommit(false);
        LlenarAfiliado = DB.conexion.prepareStatement(sqlLlenarAfiliado);
        
        rs = LlenarAfiliado.executeQuery();
        
        while(rs.next()){
            afiliados = new AfiliadoDto();
            
            afiliados.setId(rs.getInt(1));
            afiliados.setNo_Poliza(rs.getInt(2));
            afiliados.setNombre(rs.getString(3));
            afiliados.setDireccion(rs.getString(4));
            afiliados.setTelefono(rs.getString(5));
            afiliados.setIdSexo(rs.getInt(6));
            afiliados.setIdentificacion(rs.getString(7));
            afiliados.setIdTipoIdentificacion(rs.getInt(8));
            afiliados.setIdTipoAfiliacion(rs.getInt(9));
            afiliados.setIdTipoPlan(rs.getInt(10));
            afiliados.setIdTipoCobertura(rs.getInt(11));
            afiliados.setIdTipoSangre(rs.getInt(12));
            afiliados.setNo_SeguroSocial(rs.getInt(13));
            afiliados.setFechaNacimiento(rs.getDate(14));
            afiliados.setEdad(rs.getInt(15));
            afiliados.setIdCiudad(rs.getInt(16));
            afiliados.setIdProvincia(rs.getInt(17));
            afiliados.setCedTitular(rs.getString(18));
            afiliados.setNo_Contrato(rs.getString(19));
            afiliados.setIdParentezco(rs.getInt(20));
            afiliados.setIdOcupacion(rs.getInt(21));
            
            afiliado.add(afiliados);
        }
              
        return afiliado;
    }
    public AfiliadoDto GetAfiliadoById(int idAfiliado) throws SQLException{
        PreparedStatement LlenarAfiliado = null;
        String sqlLlenarAfiliado = "SELECT id, no_poliza, nombre, direccion, telefono, sexo, no_identificacion, \n" +
                                   "       id_tipo_identificacion, id_tipo_afiliacion, id_tipo_plan, id_tipo_cobertura, \n" +
                                   "       id_tipo_sangre, no_seguro_social, fecha_nacimiento, edad, id_ciudad, \n" +
                                   "       id_provincia, nombre_titular, no_contrato, id_parentezco, id_ocupacion\n" +
                                   "  FROM afiliado WHERE id=? ";
        ResultSet rs = null;
       
        AfiliadoDto afiliado = null;
        
        DB.conexion.setAutoCommit(false);
        LlenarAfiliado = DB.conexion.prepareStatement(sqlLlenarAfiliado);
        LlenarAfiliado.setInt(1, idAfiliado);
        rs = LlenarAfiliado.executeQuery();
        
        while(rs.next()){
            afiliado = new AfiliadoDto();
            
            afiliado.setId(rs.getInt(1));
            afiliado.setNo_Poliza(rs.getInt(2));
            afiliado.setNombre(rs.getString(3));
            afiliado.setDireccion(rs.getString(4));
            afiliado.setTelefono(rs.getString(5));
            afiliado.setIdSexo(rs.getInt(6));
            afiliado.setIdentificacion(rs.getString(7));
            afiliado.setIdTipoIdentificacion(rs.getInt(8));
            afiliado.setIdTipoAfiliacion(rs.getInt(9));
            afiliado.setIdTipoPlan(rs.getInt(10));
            afiliado.setIdTipoCobertura(rs.getInt(11));
            afiliado.setIdTipoSangre(rs.getInt(12));
            afiliado.setNo_SeguroSocial(rs.getInt(13));
            afiliado.setFechaNacimiento(rs.getDate(14));
            afiliado.setEdad(rs.getInt(15));
            afiliado.setIdCiudad(rs.getInt(16));
            afiliado.setIdProvincia(rs.getInt(17));
            afiliado.setCedTitular(rs.getString(18));
            afiliado.setNo_Contrato(rs.getString(19));
            afiliado.setIdParentezco(rs.getInt(20));
            afiliado.setIdOcupacion(rs.getInt(21));
            
        }
              
        return afiliado;
    }
    public List<ServicioDto> GetAllEstudiosByReclamacion(Integer idReclamacion){
        
        List<ServicioDto> servicios = new ArrayList<ServicioDto>();
        try {
            PreparedStatement psServicios = null;
            String sqlServicios = "SELECT id, id_afiliado, id_prestador, monto_reclamado, monto_pagar, \n" +
                    "       monto_diferencia, id_cobertura, id_servicio, no_autorizacion, \n" +
                    "       nno_reclamacion, monto_total\n" +
                    "       FROM servicio\n" +
                    "       WHERE	 nno_reclamacion= ?";
            
            ResultSet rs = null;
            ServicioDto servicio;
            
            DB.conexion.setAutoCommit(false);
            psServicios = DB.conexion.prepareStatement(sqlServicios);
            psServicios.setInt(1, idReclamacion);
            rs = psServicios.executeQuery();
            
            while(rs.next()){
                servicio = new ServicioDto();
                
                servicio.setId(rs.getInt(1));
                servicio.setId_afiliado(rs.getInt(2));
                servicio.setId_prestador(rs.getInt(3));
                servicio.setMonto_reclamado(rs.getInt(4));
                servicio.setMonto_pagar(rs.getInt(5));
                servicio.setMonto_doferencia(rs.getInt(6));
                servicio.setId_cobertura(rs.getInt(7));
                servicio.setId_servicio(rs.getInt(8));
                servicio.setNo_autorizacion(rs.getInt(9));
                servicio.setNo_reclamacion(rs.getInt(10));
                servicio.setMonto_total(rs.getInt(11));
                
                servicios.add(servicio);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfiliadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return servicios;
    }
    public List<ReclamacionDto> GetAllReclamacion() throws SQLException{
        PreparedStatement psReclamacion = null;
        String sqlLlenarAfiliado = "SELECT id, id_tipo_servicio, diagnostico, id_afiliado, id_prestador\n" +
                                   "  FROM reclamacion;";
        ResultSet rs = null;
        List<ReclamacionDto> reclamaciones = new ArrayList<ReclamacionDto>();
        ReclamacionDto reclamacion;
        
        DB.conexion.setAutoCommit(false);
        psReclamacion = DB.conexion.prepareStatement(sqlLlenarAfiliado);
        
        rs = psReclamacion.executeQuery();
        
        while(rs.next()){
            reclamacion = new ReclamacionDto();
            
            reclamacion.setId(rs.getInt(1));
            reclamacion.setId_tipo_servicio(rs.getInt(2));
            reclamacion.setDiagnostico(rs.getString(3));
            reclamacion.setId_afiliado(rs.getInt(4));
            reclamacion.setId_prestador(rs.getInt(5));
            
            reclamaciones.add(reclamacion);
        }
              
        return reclamaciones;
    }
    
    public String GenerarPoliza() throws SQLException{
        PreparedStatement generarPoliza=null;
        String sqlGenerarPoliza = "select generarpoliza()";
        Integer noPoliza = null ;
        ResultSet rs;
        PreparedStatement generarpoliza = DB.conexion.prepareStatement(sqlGenerarPoliza);
        DB.conexion.setAutoCommit(false);
        rs = generarpoliza.executeQuery();
        
        while(rs.next()){
            noPoliza=rs.getInt(1);
        }
            
        DB.conexion.commit(); 
        
        return noPoliza.toString();
    }
    
    public String GenerarContrato() throws SQLException{
        PreparedStatement generarContrato=null;
        String sqlGenerarContrato = "select generarcontrato()";
        Integer noContrato = null ;
        ResultSet rs;
        PreparedStatement generarcontrato = DB.conexion.prepareStatement(sqlGenerarContrato);
        DB.conexion.setAutoCommit(false);
        rs = generarcontrato.executeQuery();
        
        while(rs.next()){
            noContrato=rs.getInt(1);
        }
            
        DB.conexion.commit(); 
        
        return noContrato.toString();
    }
    
    public String GenerarIdAnalisis() throws SQLException{
        PreparedStatement generarIdAnalisis=null;
        String sqlGenerarIdAnalisis = "SELECT (select id  FROM analisis order by id desc limit 1)+1;";
        Integer idanalisis = null ;
        ResultSet rs;
        PreparedStatement generaridnalisis = DB.conexion.prepareStatement(sqlGenerarIdAnalisis);
        DB.conexion.setAutoCommit(false);
        rs = generaridnalisis.executeQuery();
        
        while(rs.next()){
            idanalisis=rs.getInt(1);
        }
            
        DB.conexion.commit(); 
        
        return idanalisis.toString();
    }
    public DefaultTableModel getModelAfiliado(List<AfiliadoDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Número de Póliza");
        modelo.addColumn("Identificación");
        modelo.addColumn("Nombre");
        modelo.addColumn("Contrato");
        modelo.addColumn("ID");
        modelo.addColumn("Seguro Social");
        modelo.addColumn("Tipo Identificación");
        modelo.addColumn("Sexo");
        modelo.addColumn("Fecha de Nac.");
        modelo.addColumn("Edad");
        modelo.addColumn("Tipo Sangre");
        modelo.addColumn("Dirección");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Provincia");
        modelo.addColumn("Tipo Afiliación");
        modelo.addColumn("Tipo Plan");
        modelo.addColumn("Cobertura");
        modelo.addColumn("Titular");
        modelo.addColumn("Parentezco");
        modelo.addColumn("Ocupación");
        modelo.addColumn("Teléfono");
    
        Object[] registro = new Object[21];
        
        Collections.reverse(lista);
        for(AfiliadoDto afiliado: lista){
               
                    registro[0] = afiliado.getNo_Poliza();
                    registro[1] = afiliado.getIdentificacion();
                    registro[2] = afiliado.getNombre();
                    registro[3] = afiliado.getNo_Contrato();
                    registro[4] = afiliado.getId();
                    registro[5] = afiliado.getNo_SeguroSocial();
                    registro[6] = afiliado.getIdTipoIdentificacion();
                    registro[7] = afiliado.getIdSexo();
                    registro[8] = afiliado.getFechaNacimiento();
                    registro[9] = afiliado.getEdad();
                    registro[10]= afiliado.getIdTipoSangre();
                    registro[11]= afiliado.getDireccion();
                    registro[12]= afiliado.getIdCiudad();
                    registro[13]= afiliado.getIdProvincia();
                    registro[14]= afiliado.getIdTipoAfiliacion();
                    registro[15]= afiliado.getIdTipoPlan();
                    registro[16]= afiliado.getIdTipoCobertura();
                    registro[17]= afiliado.getCedTitular();
                    registro[18]= afiliado.getIdParentezco();
                    registro[19]= afiliado.getIdOcupacion();
                    registro[20]= afiliado.getTelefono();
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    public DefaultTableModel getModelReclamacion(List<ReclamacionDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID");
        modelo.addColumn("Tipo Servicio");
        modelo.addColumn("Diagnostico");
        modelo.addColumn("Afiliado");
        modelo.addColumn("Prestador");
    
        Object[] registro = new Object[5];
        
        Collections.reverse(lista);
        for(ReclamacionDto reclamacion: lista){
               
                    registro[0] = reclamacion.getId();
                    registro[1] = reclamacion.getId_tipo_servicio();
                    registro[2] = reclamacion.getDiagnostico();
                    registro[3] = reclamacion.getId_afiliado();
                    registro[4] = reclamacion.getId_prestador();
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public DefaultTableModel filtraModelAfiliado(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Número de Póliza");
        modelo.addColumn("Identificación");
        modelo.addColumn("Nombre");
        modelo.addColumn("Contrato");
        modelo.addColumn("ID");
        modelo.addColumn("Seguro Social");
        modelo.addColumn("Tipo Identificación");
        modelo.addColumn("Sexo");
        modelo.addColumn("Fecha de Nac.");
        modelo.addColumn("Edad");
        modelo.addColumn("Tipo Sangre");
        modelo.addColumn("Dirección");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Provincia");
        modelo.addColumn("Tipo Afiliación");
        modelo.addColumn("Tipo Plan");
        modelo.addColumn("Cobertura");
        modelo.addColumn("Titular");
        modelo.addColumn("Parentezco");
        modelo.addColumn("Ocupación");
        modelo.addColumn("Teléfono");
        
        PreparedStatement buscarAfiliado=null;
        String sqlBuscarAfiliado="SELECT no_poliza, no_identificacion, nombre, no_contrato, Id, no_seguro_social, \n" +
                            "       id_tipo_identificacion, sexo, fecha_nacimiento, edad, id_tipo_sangre, direccion, \n" +
                            "       id_ciudad, id_provincia, id_tipo_afiliacion, id_tipo_plan, id_tipo_cobertura, \n" +
                            "       nombre_titular, id_parentezco, id_ocupacion, telefono \n" +
                            "  FROM afiliado where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAfiliado = DB.conexion.prepareStatement(sqlBuscarAfiliado);
        rs = buscarAfiliado.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[21];
                for (int row = 0; row < 21; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    }
    
    public DefaultTableModel getModelEstudio(List<EstudioDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("Precio");
        
        Object[] registro = new Object[3];
        
        Collections.reverse(lista);
        for(EstudioDto estudio: lista){
               
                    registro[0] = estudio.getNombre();
                    registro[1] = estudio.getId();
                    registro[2] = estudio.getPrecio();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public DefaultTableModel getModelEstudioSeleccionado(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("Precio");
        modelo.addColumn("Diferencia");
        modelo.addColumn("A Pagar");
        
        return modelo;
    }
    
    public DefaultTableModel filtraModelEstudio(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("ID");
        modelo.addColumn("Precio");
        
        PreparedStatement buscarEstudio=null;
        String sqlBuscarEstudio="SELECT id, nombre, precio FROM estudio where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarEstudio = DB.conexion.prepareStatement(sqlBuscarEstudio);
        rs = buscarEstudio.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[3];
//                for (int row = 0; row < 3; row++) {
//                    datos[row] = rs.getObject(row+1);
//
//                }
                datos[0] = rs.getString(2);
                datos[1] = rs.getInt(1);
                datos[2] = rs.getInt(3);
                modelo.addRow(datos);
        }
        
        return modelo;
    }
    
    public List<EstudioDto> GetAllEstudio() throws SQLException{
        PreparedStatement LlenarEstudio = null;
        String sqlLlenarEstudio = "select id, nombre, precio from estudio order by id asc";
        ResultSet rs = null;
        List<EstudioDto> estudios = new ArrayList<EstudioDto>();
        EstudioDto estudio;
        
        DB.conexion.setAutoCommit(false);
        LlenarEstudio = DB.conexion.prepareStatement(sqlLlenarEstudio);
        
        rs = LlenarEstudio.executeQuery();
        
        while(rs.next()){
            estudio = new EstudioDto();
            
            estudio.setId(rs.getInt(1));
            estudio.setNombre(rs.getString(2));
            estudio.setPrecio(rs.getInt(3));
            
            estudios.add(estudio);
        }
              
        return estudios;
    }
    public EstudioDto GetEstudioById(Integer idEstudio) throws SQLException{
        PreparedStatement LlenarEstudio = null;
        String sqlLlenarEstudio = "select id, nombre, precio from estudio where id = ?";
        ResultSet rs = null;
        EstudioDto estudio = null;
        
        DB.conexion.setAutoCommit(false);
        LlenarEstudio = DB.conexion.prepareStatement(sqlLlenarEstudio);
        LlenarEstudio.setInt(1, idEstudio);
        rs = LlenarEstudio.executeQuery();
        
        while(rs.next()){
            estudio = new EstudioDto();
            
            estudio.setId(rs.getInt(1));
            estudio.setNombre(rs.getString(2));
            estudio.setPrecio(rs.getInt(3));
            
        }
              
        return estudio;
    }
    public DefaultTableModel getModelPrestador(List<PrestadoresDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Institucion");
        modelo.addColumn("PSS");
        modelo.addColumn("Id");
        modelo.addColumn("Telefono");
          
        Object[] registro = new Object[7];
        
        Collections.reverse(lista);
        for(PrestadoresDto prestador: lista){
               
                    registro[0] = prestador.getCodigo();
                    registro[1] = prestador.getNombre();
                    registro[2] = prestador.getId_especialidad();
                    registro[3] = prestador.getId_institucion();
                    registro[4] = prestador.getId_tipo_pss();
                    registro[5] = prestador.getId();
                    registro[6] = prestador.getTelefono();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
     
    public DefaultTableModel filtraModelPrestadores(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Especialidad");
        modelo.addColumn("Institución");
        modelo.addColumn("PSS");
        modelo.addColumn("Id");
        modelo.addColumn("Teléfono");
        
        PreparedStatement buscarPrestador=null;
        String sqlBuscarPrestador="SELECT prestadores.codigo, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion, prestadores.telefono, prestadores.id, \n" +
                                    "       prestadores.id_tipo_pss \n" +
                                    "  FROM prestadores where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarPrestador = DB.conexion.prepareStatement(sqlBuscarPrestador);
        rs = buscarPrestador.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[7];
                for (int row = 0; row < 7; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public List<PrestadoresDto> GetAllPrestador() throws SQLException{
        PreparedStatement LlenarPrestador = null;
//        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion, prestadores.telefono, prestadores.codigo, \n" +
//                                    "       prestadores.id_tipo_pss, especialidad.nombre as especialidad, especialidad.id, institucion.nombre as institucion, institucion.id \n" +
//                                    "  FROM prestadores, especialidad, institucion where prestadores.id_especialidad=especialidad.id and prestadores.id_institucion=institucion.id";
        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion,\n"+
                                    "       prestadores.telefono, prestadores.codigo, prestadores.id_tipo_pss \n" +
                                    "  FROM prestadores";
        
        
        ResultSet rs = null;
        List<PrestadoresDto> prestadores = new ArrayList<PrestadoresDto>();
        PrestadoresDto prestador;
        
        DB.conexion.setAutoCommit(false);
        LlenarPrestador = DB.conexion.prepareStatement(sqlLlenarPrestador);
        
        rs = LlenarPrestador.executeQuery();
        
        while(rs.next()){
            prestador = new PrestadoresDto();
            
            prestador.setCodigo(rs.getString("codigo"));
            prestador.setNombre(rs.getString("nombre"));
            prestador.setId_especialidad(rs.getInt("id_especialidad"));
            prestador.setId_institucion(rs.getInt("id_institucion"));
            prestador.setId_tipo_pss(rs.getInt("id_tipo_pss"));
            prestador.setId(rs.getInt("id"));
            prestador.setTelefono(rs.getString("telefono"));
             
            prestadores.add(prestador);
        }
              
        return prestadores;
    }
    public PrestadoresDto GetPrestadorById(int idPrestador) throws SQLException{
        PreparedStatement LlenarPrestador = null;
//        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion, prestadores.telefono, prestadores.codigo, \n" +
//                                    "       prestadores.id_tipo_pss, especialidad.nombre as especialidad, especialidad.id, institucion.nombre as institucion, institucion.id \n" +
//                                    "  FROM prestadores, especialidad, institucion where prestadores.id_especialidad=especialidad.id and prestadores.id_institucion=institucion.id";
        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion,\n"+
                                    "       prestadores.telefono, prestadores.codigo, prestadores.id_tipo_pss \n" +
                                    "  FROM prestadores WHERE prestadores.id=?";
        
        
        ResultSet rs = null;
        PrestadoresDto prestador = null;
        
        DB.conexion.setAutoCommit(false);
        LlenarPrestador = DB.conexion.prepareStatement(sqlLlenarPrestador);
        LlenarPrestador.setInt(1, idPrestador);
        rs = LlenarPrestador.executeQuery();
        
        while(rs.next()){
            prestador = new PrestadoresDto();
            
            prestador.setCodigo(rs.getString("codigo"));
            prestador.setNombre(rs.getString("nombre"));
            prestador.setId_especialidad(rs.getInt("id_especialidad"));
            prestador.setId_institucion(rs.getInt("id_institucion"));
            prestador.setId_tipo_pss(rs.getInt("id_tipo_pss"));
            prestador.setId(rs.getInt("id"));
            prestador.setTelefono(rs.getString("telefono"));
             
        }
              
        return prestador;
    }
    public String insertAnalisis( AnalisisDto analisis) throws Exception{
    
        PreparedStatement nuevoAnalisis = null;
        String sqlInsertar = "INSERT INTO analisis (nombre,precio) VALUES (?,?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoAnalisis = DB.conexion.prepareStatement(sqlInsertar);
            nuevoAnalisis.setString(1,  analisis.getNombre());
            nuevoAnalisis.setFloat(2,  analisis.getPrecio());
            
            nuevoAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateAnalisis( AnalisisDto analisis) throws Exception{
    
        PreparedStatement actualizaAnalisis = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE analisis SET nombre=?,precio=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaAnalisis = DB.conexion.prepareStatement(sqlUpdate);
            actualizaAnalisis.setString(1,  analisis.getNombre());
            actualizaAnalisis.setFloat(2, analisis.getPrecio());
            actualizaAnalisis.setInt(3, analisis.getId());
            
            actualizaAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public List<AnalisisDto> GetAllAnalisis() throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "select nombre, id, precio from analisis order by id asc";
        ResultSet rs = null;
        List<AnalisisDto> analisisr = new ArrayList<AnalisisDto>();
        AnalisisDto analisis;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        
        rs = LlenarAnalisis.executeQuery();
        
        while(rs.next()){
            analisis = new AnalisisDto();
            
            analisis.setNombre(rs.getString(1));
            analisis.setId(rs.getInt(2));
            analisis.setPrecio(rs.getFloat(3));
            
            analisisr.add(analisis);
        }
              
        return analisisr;
    }
    
    public List<EspecialidadDto> GetAllEspecialidad() throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "select nombre, id from especialidad order by id asc";
        ResultSet rs = null;
        List<EspecialidadDto> especialidades = new ArrayList<EspecialidadDto>();
        EspecialidadDto especialidad;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        
        rs = LlenarAnalisis.executeQuery();
        
        while(rs.next()){
            especialidad = new EspecialidadDto();
            
            especialidad.setNombre(rs.getString(1));
            especialidad.setId(rs.getInt(2));
            
            especialidades.add(especialidad);
        }
              
        return especialidades;
    }
    
    public DefaultTableModel getModelAnalisis(List<AnalisisDto> lista){
            DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("Precio");
          
        Object[] registro = new Object[3];
        
        Collections.reverse(lista);
        for(AnalisisDto analisis: lista){
               
                    registro[0] = analisis.getNombre();
                    registro[1] = analisis.getId();
                    registro[2] = analisis.getPrecio();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
     
    public DefaultTableModel filtraModelAnalisis(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("Precio");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id, precio from Analisis where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAnalisis = DB.conexion.prepareStatement(sqlBuscarAnalisis);
        rs = buscarAnalisis.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[3];
                for (int row = 0; row < 3; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public Tipo_CoberturaDto cobertura(int u) throws Exception{
    
        PreparedStatement nuevoAnalisis = null;
        String sqlInsertar = "  SELECT * FROM tipo_cobertura WHERE tipo_cobertura.id=?;";
        
        ResultSet rs = null;
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoAnalisis = DB.conexion.prepareStatement(sqlInsertar);
            nuevoAnalisis.setInt(1,u);
            
            nuevoAnalisis.executeQuery();
            rs=nuevoAnalisis.executeQuery();
            DB.conexion.commit(); 
            
        } catch (Exception exc) {
            throw exc;
        }
        
        Tipo_CoberturaDto co;
        co = new Tipo_CoberturaDto();
        
        while (rs.next()){
            co.setId(rs.getInt(1));
            co.setPorciento(rs.getInt(2));
        }
        
        return co;
    }

    public void insertServicio(ServicioDto servicio){
        
        PreparedStatement insertarServicio = null;
        String sqlInsert = 
             "INSERT INTO servicio(\n" +
"             id_afiliado, id_prestador, monto_reclamado, monto_pagar, \n" +
"            monto_diferencia, id_cobertura, id_servicio, no_autorizacion, \n" +
"            nno_reclamacion, monto_total)\n" +
"    VALUES (?, ?, ?, ?, \n" +
"            ?, ?, ?, ?, \n" +
"            ?, ?);";
        
        try{
            DB.conexion.setAutoCommit(false);
            insertarServicio = DB.conexion.prepareStatement(sqlInsert);
            
            insertarServicio.setInt(1,  servicio.getId_afiliado());
            insertarServicio.setInt(2,  servicio.getId_prestador());
            insertarServicio.setInt(3,  servicio.getMonto_reclamado());
            insertarServicio.setInt(4,  servicio.getMonto_pagar());
            insertarServicio.setInt(5,  servicio.getMonto_doferencia());
            insertarServicio.setInt(6,  servicio.getId_cobertura());
            insertarServicio.setInt(7,  servicio.getId_servicio());
            insertarServicio.setInt(8,  servicio.getNo_autorizacion());
            insertarServicio.setInt(9,  servicio.getNo_reclamacion());
            insertarServicio.setInt(10,  servicio.getMonto_total());
            
            insertarServicio.execute();
            
            DB.conexion.commit();
            
        }catch(Exception e){
            
        }
    }
    public void insertListServicio(List<ServicioDto> servicios){
        
        PreparedStatement insertarServicio = null;
        String sqlInsert = 
             "INSERT INTO servicio(\n" +
"             id_afiliado, id_prestador, monto_reclamado, monto_pagar, \n" +
"            monto_diferencia, id_cobertura, id_servicio, no_autorizacion, \n" +
"            nno_reclamacion, monto_total)\n" +
"    VALUES (?, ?, ?, ?, \n" +
"            ?, ?, ?, ?, \n" +
"            ?, ?);";
        
        try{
            for (ServicioDto servicio : servicios) {
                DB.conexion.setAutoCommit(false);
                insertarServicio = DB.conexion.prepareStatement(sqlInsert);

                insertarServicio.setInt(1,  servicio.getId_afiliado());
                insertarServicio.setInt(2,  servicio.getId_prestador());
                insertarServicio.setInt(3,  servicio.getMonto_reclamado());
                insertarServicio.setInt(4,  servicio.getMonto_pagar());
                insertarServicio.setInt(5,  servicio.getMonto_doferencia());
                insertarServicio.setInt(6,  servicio.getId_cobertura());
                insertarServicio.setInt(7,  servicio.getId_servicio());
                insertarServicio.setInt(8,  servicio.getNo_autorizacion());
                insertarServicio.setInt(9,  servicio.getNo_reclamacion());
                insertarServicio.setInt(10,  servicio.getMonto_total());

                insertarServicio.execute();

                DB.conexion.commit();
            }
        }catch(Exception e){
            try {
                DB.conexion.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(AfiliadoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void insertReclamacion(ReclamacionDto reclamacion){
        
        PreparedStatement insertarReclamacion = null;
        String sqlInsert = 
            "INSERT INTO reclamacion(\n" +
"            id, id_tipo_servicio, diagnostico, id_afiliado, id_prestador)\n" +
"    VALUES (?, ?, ?, ?, ?);";
        
        try{
            DB.conexion.setAutoCommit(false);
            insertarReclamacion = DB.conexion.prepareStatement(sqlInsert);
            
            insertarReclamacion.setInt(1,  reclamacion.getId());
            insertarReclamacion.setInt(2,  reclamacion.getId_tipo_servicio());
            insertarReclamacion.setString(3,  reclamacion.getDiagnostico());
            insertarReclamacion.setInt(4,  reclamacion.getId_afiliado());
            insertarReclamacion.setInt(5,  reclamacion.getId_prestador());
           
            insertarReclamacion.execute();
            
            DB.conexion.commit();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

