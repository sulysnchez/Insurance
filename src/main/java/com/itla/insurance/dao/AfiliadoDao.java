
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
import com.itla.insurance.dto.InstitucionDto;
import com.itla.insurance.dto.OcupacionDto;
import com.itla.insurance.dto.Origen_PadecimientoDto;
import com.itla.insurance.dto.Prestador_ServicioDto;
import com.itla.insurance.dto.PrestadoresDto;
import com.itla.insurance.dto.ProvinciaDto;
import com.itla.insurance.dto.ReclamacionDto;
import com.itla.insurance.dto.ServicioDto;
import com.itla.insurance.dto.Servicio_ReclamacionDto;
import com.itla.insurance.dto.SexoDto;
import com.itla.insurance.dto.Tipo_AfiliacionDto;
import com.itla.insurance.dto.Tipo_CoberturaDto;
import com.itla.insurance.dto.Tipo_IdentificacionDto;
import com.itla.insurance.dto.Tipo_PagoDto;
import com.itla.insurance.dto.Tipo_ParentezcoDto;
import com.itla.insurance.dto.Tipo_PlanDto;
import com.itla.insurance.dto.Tipo_PssDto;
import com.itla.insurance.dto.Tipo_SangreDto;
import com.itla.insurance.dto.Tipo_servicioDto;
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
//    
//    public List<SexoDto> GetAllSexo() throws SQLException{
//        PreparedStatement LlenarSexo = null;
//        String sqlLlenarSexo = "select id, nombre from sexo order by id asc";
//        ResultSet rs = null;
//        List<SexoDto> sexospersona = new ArrayList<SexoDto>();
//        SexoDto sexopersona;
//        
//        DB.conexion.setAutoCommit(false);
//        LlenarSexo = DB.conexion.prepareStatement(sqlLlenarSexo);
//        
//        rs = LlenarSexo.executeQuery();
//        
//        while(rs.next()){
//            sexopersona = new SexoDto();
//            
//            sexopersona.setId(rs.getInt(1));
//            sexopersona.setNombre(rs.getString(2));
//            
//            sexospersona.add(sexopersona);
//        }
//              
//        return sexospersona;
//    }
    
    public List<Prestador_ServicioDto> GetAllPrestadorServicio() throws SQLException{
        PreparedStatement psPrestadorServicio = null;
        String sqlPrestadorServicio = "SELECT id_prestador, id_analisis, precio, id FROM prestador_servicio order by id asc";
        ResultSet rs = null;
        List<Prestador_ServicioDto> prestadorServicios = new ArrayList<Prestador_ServicioDto>();
        Prestador_ServicioDto PrestadorServicio;
        
        DB.conexion.setAutoCommit(false);
        psPrestadorServicio = DB.conexion.prepareStatement(sqlPrestadorServicio);
        
        rs = psPrestadorServicio.executeQuery();
        
        while(rs.next()){
            PrestadorServicio = new Prestador_ServicioDto();
            
            PrestadorServicio.setId_prestador(rs.getInt(1));
            PrestadorServicio.setId_servicio(rs.getInt(2));
            PrestadorServicio.setPrecio(rs.getFloat(3));
            
            
            
            prestadorServicios.add(PrestadorServicio);
        }
              
        return prestadorServicios;
    }
    
        public List<Prestador_ServicioDto> GetAllPrestadorServicioByPrestador(int idPrestador) throws SQLException{
        PreparedStatement psPrestadorServicio = null;
        String sqlPrestadorServicio = "SELECT id_prestador, id_analisis, precio, id FROM prestador_servicio WHERE id_prestador = ? order by id asc";
        ResultSet rs = null;
        List<Prestador_ServicioDto> prestadorServicios = new ArrayList<Prestador_ServicioDto>();
        Prestador_ServicioDto PrestadorServicio;
        
        DB.conexion.setAutoCommit(false);
        psPrestadorServicio = DB.conexion.prepareStatement(sqlPrestadorServicio);
        psPrestadorServicio.setInt(1, idPrestador);
        rs = psPrestadorServicio.executeQuery();
        
        while(rs.next()){
            PrestadorServicio = new Prestador_ServicioDto();
            
            PrestadorServicio.setId_prestador(rs.getInt(1));
            PrestadorServicio.setId_servicio(rs.getInt(2));
            PrestadorServicio.setPrecio(rs.getFloat(3));
            PrestadorServicio.setNombre_servicio(GetAnalisisById(PrestadorServicio.getId_servicio()).getNombre());
            PrestadorServicio.setId(rs.getInt(4));
            prestadorServicios.add(PrestadorServicio);
        }
              
        return prestadorServicios;
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
    public List<Servicio_ReclamacionDto> GetAllServiciosReclamacionByReclamacion(Integer idReclamacion){
        
        List<Servicio_ReclamacionDto> serviciosReclamacion = new ArrayList<Servicio_ReclamacionDto>();
        try {
            PreparedStatement psServicios = null;
            String sqlServicios = "SELECT id, id_reclamacion, id_servicio\n" +
                    "       FROM servicios_reclamacion\n" +
                    "       WHERE	 id_reclamacion= ?";
            
            ResultSet rs = null;
            Servicio_ReclamacionDto servicioReclamacion;
            
            DB.conexion.setAutoCommit(false);
            psServicios = DB.conexion.prepareStatement(sqlServicios);
            psServicios.setInt(1, idReclamacion);
            rs = psServicios.executeQuery();
            
            while(rs.next()){
                servicioReclamacion = new Servicio_ReclamacionDto();
                
                servicioReclamacion.setId(rs.getInt(1));
                servicioReclamacion.setId_reclamacion(rs.getInt(2));
                servicioReclamacion.setId_servicio(rs.getInt(3));
                
                serviciosReclamacion.add(servicioReclamacion);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfiliadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return serviciosReclamacion;
    }
        
    public List<ReclamacionDto> GetAllReclamacion(boolean paga, String nombre) throws SQLException {
        return GetAllReclamacion("WHERE r.pagado = " + (paga ? "true" : "false") + " and (p.nombre like '%" + nombre + "%' or a.nombre like '%" + nombre + "%')");
    }
    
    public List<ReclamacionDto> GetAllReclamacion() throws SQLException{
        return GetAllReclamacion("");
    }
    
    private List<ReclamacionDto> GetAllReclamacion(String extraFilters) throws SQLException{
        PreparedStatement psReclamacion = null;
        ResultSet rs = null;
        List<ReclamacionDto> reclamaciones = new ArrayList<ReclamacionDto>();
        ReclamacionDto reclamacion;
        String sqlReclamacion = "SELECT r.id, r.id_tipo_servicio, r.diagnostico, r.id_afiliado, r.id_prestador, a.nombre, p.nombre, r.pagado, COALESCE(total.total_analisis - (total.total_analisis * t.porciento / 100.0), 0)::real \n" +
                        "   FROM reclamacion r JOIN afiliado a ON r.id_afiliado = a.id JOIN prestadores p ON r.id_prestador = p.id	\n" +
                        "   JOIN tipo_cobertura t ON a.id_tipo_cobertura = t.id\n" +
                        "   Left JOIN (SELECT sr.id_reclamacion, sum(a.precio)::real AS total_analisis FROM Servicios_Reclamacion sr JOIN analisis a ON sr.id_servicio = a.id group by sr.id_reclamacion) total\n" +
                        "	ON r.id = total.id_reclamacion  "
                + extraFilters + "                     order by r.id;";
        
        DB.conexion.setAutoCommit(false);
        psReclamacion = DB.conexion.prepareStatement(sqlReclamacion);
        
        rs = psReclamacion.executeQuery();
        
        while(rs.next()){
            reclamacion = new ReclamacionDto();
            
            reclamacion.setId(rs.getInt(1));
            reclamacion.setId_tipo_servicio(rs.getInt(2));
            reclamacion.setDiagnostico(rs.getString(3));
            reclamacion.setId_afiliado(rs.getInt(4));
            reclamacion.setId_prestador(rs.getInt(5));
            reclamacion.setNombre_afiliado(rs.getString(6));
            reclamacion.setNombre_prestador(rs.getString(7));
            reclamacion.setPagado(rs.getBoolean(8));
            reclamacion.setTotalAPagar(rs.getFloat(9));
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
        
        modelo.addColumn("Id");
        modelo.addColumn("Afiliado");
        modelo.addColumn("Id_Afiliado");
        modelo.addColumn("Prestador");
        modelo.addColumn("Id_Prestador");
        modelo.addColumn("Pagado");        
        modelo.addColumn("Total a Pagar");
        modelo.addColumn("Diagnostico");

    
        Object[] registro = new Object[8];
        
        Collections.reverse(lista);
        for(ReclamacionDto reclamacion: lista){
               
                    registro[0] = reclamacion.getId();
                    registro[1] = reclamacion.getNombre_afiliado();
                    registro[2] = reclamacion.getId_afiliado();
                    registro[3] = reclamacion.getNombre_prestador();
                    registro[4] = reclamacion.getId_prestador();
                    registro[5] = reclamacion.getPagado();
                    registro[6] = reclamacion.getTotalAPagar();
                    registro[7] = reclamacion.getDiagnostico();
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    public ReclamacionDto GetReclamacionById(int reclamacionId) throws SQLException{
        PreparedStatement psReclamacion = null;
        String sqlLlenarAfiliado = "SELECT r.id, r.id_tipo_servicio, r.diagnostico, r.id_afiliado, r.id_prestador, a.nombre, p.nombre\n" +
                                   "  FROM reclamacion r JOIN afiliado a ON r.id_afiliado = a.id JOIN prestadores p ON r.id_prestador = p.id "
                + "WHERE r.id = ?;";
        ResultSet rs = null;
        ReclamacionDto reclamacion = null;
        
        DB.conexion.setAutoCommit(false);
        psReclamacion = DB.conexion.prepareStatement(sqlLlenarAfiliado);
        psReclamacion.setInt(1, reclamacionId);
        rs = psReclamacion.executeQuery();
        
        while(rs.next()){
            reclamacion = new ReclamacionDto();
            
            reclamacion.setId(rs.getInt(1));
            reclamacion.setId_tipo_servicio(rs.getInt(2));
            reclamacion.setDiagnostico(rs.getString(3));
            reclamacion.setId_afiliado(rs.getInt(4));
            reclamacion.setId_prestador(rs.getInt(5));
            reclamacion.setNombre_afiliado(rs.getString(6));
            reclamacion.setNombre_prestador(rs.getString(7));
        }
              
        return reclamacion;
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
    
    public DefaultTableModel getModelReclamacionAnalisisSeleccionado(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("Precio");
        modelo.addColumn("Diferencia");
        modelo.addColumn("A Pagar");
        
        return modelo;
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
        modelo.addColumn("Direccion");
          
        Object[] registro = new Object[8];
        
        Collections.reverse(lista);
        for(PrestadoresDto prestador: lista){
               
                    registro[0] = prestador.getCodigo();
                    registro[1] = prestador.getNombre();
                    registro[2] = prestador.getId_especialidad();
                    registro[3] = prestador.getId_institucion();
                    registro[4] = prestador.getId_tipo_pss();
                    registro[5] = prestador.getId();
                    registro[6] = prestador.getTelefono();
                    registro[7] = prestador.getDireccion();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
     
    public List<PrestadoresDto> filtraModelPrestadores(String filtro) throws SQLException{
        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion,\n"+
                                    "       prestadores.telefono, prestadores.codigo, prestadores.id_tipo_pss, prestadores.direccion \n" +
                                    "  FROM prestadores where Nombre like '%" + filtro + "%'";
        
        return GetPrestador(sqlLlenarPrestador);

//        DefaultTableModel modelo = new DefaultTableModel();
//        
//        modelo.addColumn("Código");
//        modelo.addColumn("Nombre");
//        modelo.addColumn("Especialidad");
//        modelo.addColumn("Institución");
//        modelo.addColumn("PSS");
//        modelo.addColumn("Id");
//        modelo.addColumn("Teléfono");
//        
//        PreparedStatement buscarPrestador=null;
//        String sqlBuscarPrestador="SELECT prestadores.codigo, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion, prestadores.telefono, prestadores.id, \n" +
//                                    "       prestadores.id_tipo_pss \n" +
//                                    "  FROM prestadores where Nombre like '%" + filtro + "%'";
//        
//        ResultSet rs = null;
//        
//        DB.conexion.setAutoCommit(false);
//        buscarPrestador = DB.conexion.prepareStatement(sqlBuscarPrestador);
//        rs = buscarPrestador.executeQuery();
//
//        while (rs.next()) {
//            
//            Object[] datos = new Object[7];
//                for (int row = 0; row < 7; row++) {
//                    datos[row] = rs.getObject(row+1);
//
//                }
//                modelo.addRow(datos);
//        }
//        
//        return modelo;
    } 
    
    public List<PrestadoresDto> GetAllPrestador() throws SQLException{
//        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion, prestadores.telefono, prestadores.codigo, \n" +
//                                    "       prestadores.id_tipo_pss, especialidad.nombre as especialidad, especialidad.id, institucion.nombre as institucion, institucion.id \n" +
//                                    "  FROM prestadores, especialidad, institucion where prestadores.id_especialidad=especialidad.id and prestadores.id_institucion=institucion.id";
        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion,\n"+
                                    "       prestadores.telefono, prestadores.codigo, prestadores.id_tipo_pss, prestadores.direccion \n" +
                                    "  FROM prestadores";
        
        
        return GetPrestador(sqlLlenarPrestador);
    }
    
    public List<PrestadoresDto> GetPrestador(String sqlSelect) throws SQLException{
        PreparedStatement LlenarPrestador = null;
        
        ResultSet rs = null;
        List<PrestadoresDto> prestadores = new ArrayList<PrestadoresDto>();
        PrestadoresDto prestador;
        
        DB.conexion.setAutoCommit(false);
        LlenarPrestador = DB.conexion.prepareStatement(sqlSelect);
        
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
            prestador.setDireccion(rs.getString("direccion"));
             
            prestador.setServicios(GetAllPrestadorServicioByPrestador(prestador.getId()));
            
            prestadores.add(prestador);
        }
              
        return prestadores;
    }
    
    public PrestadoresDto GetPrestadorById(int idPrestador) throws SQLException{
        PreparedStatement LlenarPrestador = null;
        String sqlLlenarPrestador = "SELECT prestadores.id, prestadores.nombre, prestadores.id_especialidad, prestadores.id_institucion,\n"+
                                    "       prestadores.telefono, prestadores.codigo, prestadores.id_tipo_pss, prestadores.direccion \n" +
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
             
            
            prestador.setServicios(GetAllPrestadorServicioByPrestador(prestador.getId()));
            
            
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
    
    public String UpdatePrestadorServicio(Prestador_ServicioDto preser) throws Exception{
    
        PreparedStatement actualizaAnalisis = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE prestador_servicio\n" +
                    " SET id_prestador=?, id_analisis=?, precio=?\n" +
                    " WHERE id = ?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaAnalisis = DB.conexion.prepareStatement(sqlUpdate);
            actualizaAnalisis.setInt(1,  preser.getId_prestador());
            actualizaAnalisis.setInt(2, preser.getId_servicio());
            actualizaAnalisis.setFloat(3, preser.getPrecio());
            actualizaAnalisis.setInt(4, preser.getId());
            
            actualizaAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }    
    public String InsertPrestadorServicio(Prestador_ServicioDto preser) throws Exception{
    
        PreparedStatement actualizaAnalisis = null;
        String sqlUpdate = null;
        
        sqlUpdate = "INSERT INTO prestador_servicio\n" +
                    " (id_prestador, id_analisis, precio) values(?,?,?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaAnalisis = DB.conexion.prepareStatement(sqlUpdate);
            actualizaAnalisis.setInt(1,  preser.getId_prestador());
            actualizaAnalisis.setInt(2, preser.getId_servicio());
            actualizaAnalisis.setFloat(3, preser.getPrecio());
            
            actualizaAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public PrestadoresDto UpdatePrestador(PrestadoresDto prestador) throws Exception{
    
        PreparedStatement actualizaAnalisis = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE prestadores\n" +
"   SET nombre=?, id_especialidad=?, id_institucion=?, telefono=?, codigo=?, \n" +
"       id_tipo_pss=?\n" +
" WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaAnalisis = DB.conexion.prepareStatement(sqlUpdate);
            actualizaAnalisis.setString(1,  prestador.getNombre());
            actualizaAnalisis.setInt(2, prestador.getId_especialidad());
            actualizaAnalisis.setInt(3, prestador.getId_institucion());
            actualizaAnalisis.setString(4, prestador.getTelefono());
            actualizaAnalisis.setString(5, prestador.getCodigo());
            actualizaAnalisis.setInt(6, prestador.getId_tipo_pss());
            actualizaAnalisis.setInt(7, prestador.getId());
                    
            actualizaAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        return prestador;
    }
    public PrestadoresDto InsertPrestador(PrestadoresDto prestador) throws Exception{
    
        PreparedStatement psInsertar = null;
        String sqlUpdate = null;
        
        sqlUpdate = "INSERT INTO prestadores\n" +
                    "(nombre, id_especialidad, id_institucion, telefono, codigo, \n" +
                    "id_tipo_pss, direccion) values(?,?,?,?,?,?, ?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            psInsertar = DB.conexion.prepareStatement(sqlUpdate);
            psInsertar.setString(1,  prestador.getNombre());
            psInsertar.setInt(2, prestador.getId_especialidad());
            psInsertar.setInt(3, prestador.getId_institucion());
            psInsertar.setString(4, prestador.getTelefono());
            psInsertar.setString(5, prestador.getCodigo());
            psInsertar.setInt(6, prestador.getId_tipo_pss());
            psInsertar.setString(7, prestador.getDireccion());
            
            psInsertar.execute();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        return prestador;
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
    
    public List<AnalisisDto> GetAllAnalisisByPrestador(int idPrestador) throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "select a.nombre, a.id, p.precio from analisis a join prestador_servicio p on a.id=p.id_analisis\n" +
                                   "where p.id_prestador=? \n" +
                                   "\n" +
                                   "order by id asc";
        ResultSet rs = null;
        List<AnalisisDto> analisisr = new ArrayList<AnalisisDto>();
        AnalisisDto analisis;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        LlenarAnalisis.setInt(1, idPrestador);
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
    public List<AnalisisDto> GetAllAnalisisByPrestadorAndNombre(int idPrestador, String nombre) throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "select a.nombre, a.id, p.precio from analisis a join prestador_servicio p on a.id=p.id_analisis\n" +
                                   "where p.id_prestador=? and a.nombre ilike '%"+nombre+"%'\n" +
                                   "\n" +
                                   "order by id asc";
        ResultSet rs = null;
        List<AnalisisDto> analisisr = new ArrayList<AnalisisDto>();
        AnalisisDto analisis;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        LlenarAnalisis.setInt(1, idPrestador);
//        LlenarAnalisis.setString(2, nombre);
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
    
    public AnalisisDto GetAnalisisById(int idAnalisis) throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "select nombre, id, precio from analisis WHERE id = ? order by id asc";
        ResultSet rs = null;
        AnalisisDto analisis = null;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        LlenarAnalisis.setInt(1, idAnalisis);
        rs = LlenarAnalisis.executeQuery();
        
        while(rs.next()){
            analisis = new AnalisisDto();
            
            analisis.setNombre(rs.getString(1));
            analisis.setId(rs.getInt(2));
            analisis.setPrecio(rs.getFloat(3));
            
        }
              
        return analisis;
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
    
    public DefaultTableModel getModelPrestador_Servicio(List<Prestador_ServicioDto> lista){
            DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("Precio");
          
        Object[] registro = new Object[3];
        
        Collections.reverse(lista);
        for(Prestador_ServicioDto analisis: lista){
               
                    registro[0] = analisis.getNombre_servicio();
                    registro[1] = analisis.getId_servicio();
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
            insertarServicio.setFloat(3,  servicio.getMonto_reclamado());
            insertarServicio.setFloat(4,  servicio.getMonto_pagar());
            insertarServicio.setFloat(5,  servicio.getMonto_doferencia());
            insertarServicio.setInt(6,  servicio.getId_cobertura());
            insertarServicio.setInt(7,  servicio.getId_servicio());
            insertarServicio.setInt(8,  servicio.getNo_autorizacion());
            insertarServicio.setInt(9,  servicio.getNo_reclamacion());
            insertarServicio.setFloat(10,  servicio.getMonto_total());
            
            insertarServicio.execute();
            
            DB.conexion.commit();
            
        }catch(Exception e){
            
        }
    }
    public void insertListServiciosReclamacion(List<Servicio_ReclamacionDto> serviciosReclamacion){
        
        PreparedStatement insertarServicio = null;
        String sqlInsert = 
             "INSERT INTO servicios_reclamacion(\n" +
            "             id_reclamacion, id_servicio)\n" +
            "    VALUES (?, ?);";
        
        try{
            for (Servicio_ReclamacionDto servicioReclamacion : serviciosReclamacion) {
                DB.conexion.setAutoCommit(false);
                insertarServicio = DB.conexion.prepareStatement(sqlInsert);

                insertarServicio.setInt(1,  servicioReclamacion.getId_reclamacion());
                insertarServicio.setInt(2,  servicioReclamacion.getId_servicio());

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
    
    public void setReclamacionPaidStatus(int idReclamacion, boolean status){
        String sqlUpdate = 
            "Update reclamacion SET \n" +
            "       pagado  = ? \n" +
            "   WHERE id = ?;";
        ResultSet rs = null;
        PreparedStatement actualizarReclamacion = null;
        try{
            DB.conexion.setAutoCommit(false);
            actualizarReclamacion = DB.conexion.prepareStatement(sqlUpdate);
            
            
            actualizarReclamacion.setBoolean(1, status);
            actualizarReclamacion.setInt(2, idReclamacion);
           
            actualizarReclamacion.execute();
            DB.conexion.commit();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public int insertReclamacion(ReclamacionDto reclamacion){
        ResultSet rs = null;
        Integer idReclamacion = 0;
        PreparedStatement insertarReclamacion = null;
        PreparedStatement psSelect = null;
        String sqlInsert = 
            "INSERT INTO reclamacion(\n" +
"             id_tipo_servicio, diagnostico, id_afiliado, id_prestador)\n" +
"    VALUES ( ?, ?, ?, ?);";
        String sqlSelectId = "select id from reclamacion  order by id desc limit 1;";
        
        try{
            DB.conexion.setAutoCommit(false);
            insertarReclamacion = DB.conexion.prepareStatement(sqlInsert);
            
            
            insertarReclamacion.setInt(1,  reclamacion.getId_tipo_servicio());
            insertarReclamacion.setString(2,  reclamacion.getDiagnostico());
            insertarReclamacion.setInt(3,  reclamacion.getId_afiliado());
            insertarReclamacion.setInt(4,  reclamacion.getId_prestador());
           
            insertarReclamacion.execute();
            
            DB.conexion.commit();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try {
            psSelect = DB.conexion.prepareStatement(sqlSelectId);
            rs = psSelect.executeQuery();
            DB.conexion.commit();
            while(rs.next()){
                idReclamacion = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfiliadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idReclamacion;
    }
    
        //TIPO SERVICIO
    public List<Tipo_servicioDto> GetAllTipoServicio() throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "SELECT id, nombre, nombre_abreviado FROM tipo_servicio order by id asc";
        ResultSet rs = null;
        List<Tipo_servicioDto> tiposServicio = new ArrayList<Tipo_servicioDto>();
        Tipo_servicioDto tipoServicio;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        
        rs = LlenarAnalisis.executeQuery();
        
        while(rs.next()){
            tipoServicio = new Tipo_servicioDto();
            
            tipoServicio.setNombre(rs.getString("nombre"));
            tipoServicio.setId(rs.getInt("id"));
            tipoServicio.setNombre_abreviado(rs.getString("nombre_abreviado"));
            
            tiposServicio.add(tipoServicio);
        }
              
        return tiposServicio;
    }
    
    public DefaultTableModel getModelTipoServicio(List<Tipo_servicioDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("NombreAbreviado");
          
        Object[] registro = new Object[3];
        
        Collections.reverse(lista);
        for(Tipo_servicioDto tipoServicio: lista){
               
                    registro[0] = tipoServicio.getNombre();
                    registro[1] = tipoServicio.getId();
                    registro[2] = tipoServicio.getNombre_abreviado();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoServicio( Tipo_servicioDto tipoServicio) throws Exception{
    
        PreparedStatement nuevoAnalisis = null;
        String sqlInsertar = "INSERT INTO tipo_servicio (nombre,nombre_abreviado) VALUES (?,?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoAnalisis = DB.conexion.prepareStatement(sqlInsertar);
            nuevoAnalisis.setString(1,  tipoServicio.getNombre());
            nuevoAnalisis.setString(2,  tipoServicio.getNombre_abreviado());
            
            nuevoAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoServicio(Tipo_servicioDto tipoServicio) throws Exception{
    
        PreparedStatement actualizaAnalisis = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_servicio SET nombre=?,nombre_abreviado=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaAnalisis = DB.conexion.prepareStatement(sqlUpdate);
            actualizaAnalisis.setString(1,  tipoServicio.getNombre());
            actualizaAnalisis.setString(2, tipoServicio.getNombre_abreviado());
            actualizaAnalisis.setInt(3, tipoServicio.getId());
            
            actualizaAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoServicio(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("NombreAbreviado");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id, nombre_abreviado from tipo_servicio where Nombre like '%" + filtro + "%'";
        
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
    
    public void deleteTipoServicio(Tipo_servicioDto tipoServicio){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_servicio\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoServicio.getId());
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
    
    //TIPO DE SANGRE
    public List<Tipo_SangreDto> GetAllTipoSangre() throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "SELECT id, nombre FROM tipo_sangre order by id asc";
        ResultSet rs = null;
        List<Tipo_SangreDto> tiposSangre = new ArrayList<Tipo_SangreDto>();
        Tipo_SangreDto tipoSangre;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        
        rs = LlenarAnalisis.executeQuery();
        
        while(rs.next()){
            tipoSangre = new Tipo_SangreDto();
            
            tipoSangre.setNombre(rs.getString("nombre"));
            tipoSangre.setId(rs.getInt("id"));
            
            tiposSangre.add(tipoSangre);
        }
              
        return tiposSangre;
    }
    
    public DefaultTableModel getModelTipoSangre(List<Tipo_SangreDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(Tipo_SangreDto tipoSangre: lista){
               
                    registro[0] = tipoSangre.getNombre();
                    registro[1] = tipoSangre.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoSangre( Tipo_SangreDto tipoSangre) throws Exception{
    
        PreparedStatement nuevoAnalisis = null;
        String sqlInsertar = "INSERT INTO tipo_sangre (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoAnalisis = DB.conexion.prepareStatement(sqlInsertar);
            nuevoAnalisis.setString(1,  tipoSangre.getNombre());
            
            nuevoAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoSangre(Tipo_SangreDto tipoSangre) throws Exception{
    
        PreparedStatement actualizaTipoSangre = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_sangre SET nombre=?WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaTipoSangre = DB.conexion.prepareStatement(sqlUpdate);
            actualizaTipoSangre.setString(1,  tipoSangre.getNombre());
            actualizaTipoSangre.setInt(2, tipoSangre.getId());
            
            actualizaTipoSangre.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoSangre(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id from tipo_sangre where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAnalisis = DB.conexion.prepareStatement(sqlBuscarAnalisis);
        rs = buscarAnalisis.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteTipoSangre(Tipo_SangreDto tipoSangre){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_sangre\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoSangre.getId());
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
    
    //TIPO PSS
    public DefaultTableModel getModelTipoPss(List<Tipo_PssDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(Tipo_PssDto tipoSangre: lista){
               
                    registro[0] = tipoSangre.getNombre();
                    registro[1] = tipoSangre.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoPss(Tipo_PssDto tipoPss) throws Exception{
    
        PreparedStatement nuevoAnalisis = null;
        String sqlInsertar = "INSERT INTO tipo_pss (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoAnalisis = DB.conexion.prepareStatement(sqlInsertar);
            nuevoAnalisis.setString(1,  tipoPss.getNombre());
            
            nuevoAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoPss(Tipo_PssDto tipoPss) throws Exception{
    
        PreparedStatement actualizaTipoPss = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_pss SET nombre=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaTipoPss = DB.conexion.prepareStatement(sqlUpdate);
            actualizaTipoPss.setString(1,  tipoPss.getNombre());
            actualizaTipoPss.setInt(2, tipoPss.getId());
            
            actualizaTipoPss.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoPss(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id from tipo_pss where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAnalisis = DB.conexion.prepareStatement(sqlBuscarAnalisis);
        rs = buscarAnalisis.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteTipoPss(Tipo_PssDto tipoPss){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_pss\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoPss.getId());
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
    
    //TIPO DE PLAN
    public List<Tipo_PlanDto> GetAllTipoPlan() throws SQLException{
        PreparedStatement LlenarAnalisis = null;
        String sqlLlenarAnalisis = "SELECT id, nombre, id_tipo_cobertura FROM tipo_plan order by id asc";
        ResultSet rs = null;
        List<Tipo_PlanDto> tiposPlan = new ArrayList<Tipo_PlanDto>();
        Tipo_PlanDto tipoPlan;
        
        DB.conexion.setAutoCommit(false);
        LlenarAnalisis = DB.conexion.prepareStatement(sqlLlenarAnalisis);
        
        rs = LlenarAnalisis.executeQuery();
        
        while(rs.next()){
            tipoPlan = new Tipo_PlanDto();
            
            tipoPlan.setNombre(rs.getString("nombre"));
            tipoPlan.setId(rs.getInt("id"));
            tipoPlan.setId_cobertura(rs.getInt("id_tipo_cobertura"));
            
            tiposPlan.add(tipoPlan);
        }
              
        return tiposPlan;
    }
    
    public DefaultTableModel getModelTipoPlan(List<Tipo_PlanDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("IdTipoCobertura");
          
        Object[] registro = new Object[3];
        
        Collections.reverse(lista);
        for(Tipo_PlanDto tipoPlan: lista){
               
                    registro[0] = tipoPlan.getNombre();
                    registro[1] = tipoPlan.getId();
                    registro[2] = tipoPlan.getId_cobertura();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoPlan(Tipo_PlanDto tipoPlan) throws Exception{
    
        PreparedStatement nuevoAnalisis = null;
        String sqlInsertar = "INSERT INTO tipo_plan (nombre,id_tipo_cobertura) VALUES (?,?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoAnalisis = DB.conexion.prepareStatement(sqlInsertar);
            nuevoAnalisis.setString(1,  tipoPlan.getNombre());
            nuevoAnalisis.setInt(2,  tipoPlan.getId_cobertura());
            
            nuevoAnalisis.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoPlan(Tipo_PlanDto tipoPlan) throws Exception{
    
        PreparedStatement actualizaTipoSangre = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_plan SET nombre=? ,id_tipo_cobertura=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaTipoSangre = DB.conexion.prepareStatement(sqlUpdate);
            actualizaTipoSangre.setString(1,  tipoPlan.getNombre());
            actualizaTipoSangre.setInt(2, tipoPlan.getId_cobertura());
            actualizaTipoSangre.setInt(3, tipoPlan.getId());
            
            actualizaTipoSangre.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoPlan(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("IdTipoCobertura");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id, id_tipo_cobertura from tipo_plan where Nombre like '%" + filtro + "%'";
        
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
    
    public void deleteTipoPlan(Tipo_PlanDto tipoPlan){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_plan\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoPlan.getId());
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
    
    //TIPO DE PARENTEZCO
    public List<Tipo_ParentezcoDto> GetAllTipoParentezco() throws SQLException{
        PreparedStatement LlenarParentezco = null;
        String sqlLlenarParentezco = "SELECT id, nombre FROM tipo_parentezco order by id asc";
        ResultSet rs = null;
        List<Tipo_ParentezcoDto> tiposParentezco = new ArrayList<Tipo_ParentezcoDto>();
        Tipo_ParentezcoDto tipoParentezco;
        
        DB.conexion.setAutoCommit(false);
        LlenarParentezco = DB.conexion.prepareStatement(sqlLlenarParentezco);
        
        rs = LlenarParentezco.executeQuery();
        
        while(rs.next()){
            tipoParentezco = new Tipo_ParentezcoDto();
            
            tipoParentezco.setNombre(rs.getString("nombre"));
            tipoParentezco.setId(rs.getInt("id"));
            
            tiposParentezco.add(tipoParentezco);
        }
              
        return tiposParentezco;
    }
    
    public DefaultTableModel getModelTipoParentezco(List<Tipo_ParentezcoDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(Tipo_ParentezcoDto tipoSangre: lista){
               
                    registro[0] = tipoSangre.getNombre();
                    registro[1] = tipoSangre.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoParentezco(Tipo_ParentezcoDto tipoParentezco) throws Exception{
    
        PreparedStatement nuevoParentezco = null;
        String sqlInsertar = "INSERT INTO tipo_parentezco (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoParentezco = DB.conexion.prepareStatement(sqlInsertar);
            nuevoParentezco.setString(1,  tipoParentezco.getNombre());
            
            nuevoParentezco.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoParentezco(Tipo_ParentezcoDto tipoParentezco) throws Exception{
    
        PreparedStatement actualizaTipoParentezco = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_parentezco SET nombre=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaTipoParentezco = DB.conexion.prepareStatement(sqlUpdate);
            actualizaTipoParentezco.setString(1,  tipoParentezco.getNombre());
            actualizaTipoParentezco.setInt(2, tipoParentezco.getId());
            
            actualizaTipoParentezco.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoParentezco(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id from tipo_parentezco where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAnalisis = DB.conexion.prepareStatement(sqlBuscarAnalisis);
        rs = buscarAnalisis.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteTipoParentezco(Tipo_ParentezcoDto tipoParentezco){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_parentezco\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoParentezco.getId());
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
    
    //TIPO DE PAGO
    public List<Tipo_PagoDto> GetAllTipoPago() throws SQLException{
        PreparedStatement LlenarPago = null;
        String sqlLlenarPago = "SELECT id, nombre FROM tipo_pago order by id asc";
        ResultSet rs = null;
        List<Tipo_PagoDto> tiposPago = new ArrayList<Tipo_PagoDto>();
        Tipo_PagoDto tipoPago;
        
        DB.conexion.setAutoCommit(false);
        LlenarPago = DB.conexion.prepareStatement(sqlLlenarPago);
        
        rs = LlenarPago.executeQuery();
        
        while(rs.next()){
            tipoPago = new Tipo_PagoDto();
            
            tipoPago.setNombre(rs.getString("nombre"));
            tipoPago.setId(rs.getInt("id"));
            
            tiposPago.add(tipoPago);
        }
              
        return tiposPago;
    }
    
    public DefaultTableModel getModelTipoPago(List<Tipo_PagoDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(Tipo_PagoDto tipoPago: lista){
               
                    registro[0] = tipoPago.getNombre();
                    registro[1] = tipoPago.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoPago(Tipo_PagoDto tipoPago) throws Exception{
    
        PreparedStatement nuevoParentezco = null;
        String sqlInsertar = "INSERT INTO tipo_pago (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoParentezco = DB.conexion.prepareStatement(sqlInsertar);
            nuevoParentezco.setString(1,  tipoPago.getNombre());
            
            nuevoParentezco.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoPago(Tipo_PagoDto tipoPago) throws Exception{
    
        PreparedStatement actualizaTipoParentezco = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_pago SET nombre=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaTipoParentezco = DB.conexion.prepareStatement(sqlUpdate);
            actualizaTipoParentezco.setString(1,  tipoPago.getNombre());
            actualizaTipoParentezco.setInt(2, tipoPago.getId());
            
            actualizaTipoParentezco.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoPago(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id from tipo_pago where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAnalisis = DB.conexion.prepareStatement(sqlBuscarAnalisis);
        rs = buscarAnalisis.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteTipoPago(Tipo_PagoDto tipoPago){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_pago\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoPago.getId());
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
    
    //TIPO DE Cobertura
    public List<Tipo_CoberturaDto> GetAllTipoCobertura() throws SQLException{
        PreparedStatement LlenarTipoCobertura = null;
        String sqlLlenarPago = "SELECT id, porciento FROM tipo_cobertura order by id asc";
        ResultSet rs = null;
        List<Tipo_CoberturaDto> tiposCobertura = new ArrayList<Tipo_CoberturaDto>();
        Tipo_CoberturaDto tipoCobertura;
        
        DB.conexion.setAutoCommit(false);
        LlenarTipoCobertura = DB.conexion.prepareStatement(sqlLlenarPago);
        
        rs = LlenarTipoCobertura.executeQuery();
        
        while(rs.next()){
            tipoCobertura = new Tipo_CoberturaDto();
            
            tipoCobertura.setPorciento(rs.getInt("porciento"));
            tipoCobertura.setId(rs.getInt("id"));
            
            tiposCobertura.add(tipoCobertura);
        }
              
        return tiposCobertura;
    }
    
    public DefaultTableModel getModelTipoCobertura(List<Tipo_CoberturaDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("porciento");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(Tipo_CoberturaDto tipoCobertura: lista){
               
                    registro[0] = tipoCobertura.getPorciento();
                    registro[1] = tipoCobertura.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoCobertura(Tipo_CoberturaDto tipoCobertura) throws Exception{
    
        PreparedStatement nuevoParentezco = null;
        String sqlInsertar = "INSERT INTO tipo_cobertura (porciento) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            nuevoParentezco = DB.conexion.prepareStatement(sqlInsertar);
            nuevoParentezco.setInt(1,  tipoCobertura.getPorciento());
            
            nuevoParentezco.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoCobertura(Tipo_CoberturaDto tipoCobertura) throws Exception{
    
        PreparedStatement actualizaTipoCobertura = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_cobertura SET porciento=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaTipoCobertura = DB.conexion.prepareStatement(sqlUpdate);
            actualizaTipoCobertura.setInt(1,  tipoCobertura.getPorciento());
            actualizaTipoCobertura.setInt(2, tipoCobertura.getId());
            
            actualizaTipoCobertura.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoCobertura(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("porciento");
        modelo.addColumn("Id");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT porciento, id from tipo_cobertura where porciento = " + filtro;
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAnalisis = DB.conexion.prepareStatement(sqlBuscarAnalisis);
        rs = buscarAnalisis.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteTipoCobertura(Tipo_CoberturaDto tipoCobertura){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_cobertura\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoCobertura.getId());
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
    
    //TIPO DE AFILIACION
    public List<Tipo_AfiliacionDto> GetAllTipoAfiliacion() throws SQLException{
        PreparedStatement LlenarPago = null;
        String sqlLlenarPago = "SELECT id, nombre FROM tipo_afiliacion order by id asc";
        ResultSet rs = null;
        List<Tipo_AfiliacionDto> tiposAfiliacion = new ArrayList<Tipo_AfiliacionDto>();
        Tipo_AfiliacionDto tipoAfilicion;
        
        DB.conexion.setAutoCommit(false);
        LlenarPago = DB.conexion.prepareStatement(sqlLlenarPago);
        
        rs = LlenarPago.executeQuery();
        
        while(rs.next()){
            tipoAfilicion = new Tipo_AfiliacionDto();
            
            tipoAfilicion.setNombre(rs.getString("nombre"));
            tipoAfilicion.setId(rs.getInt("id"));
            
            tiposAfiliacion.add(tipoAfilicion);
        }
              
        return tiposAfiliacion;
    }
    
    public DefaultTableModel getModelTipoAfiliacion(List<Tipo_AfiliacionDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(Tipo_AfiliacionDto tipoAfiliacion: lista){
               
                    registro[0] = tipoAfiliacion.getNombre();
                    registro[1] = tipoAfiliacion.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertTipoAfiliacion(Tipo_AfiliacionDto tipoAfiliacion) throws Exception{
    
        PreparedStatement ps = null;
        String sqlInsertar = "INSERT INTO tipo_afiliacion (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlInsertar);
            ps.setString(1,  tipoAfiliacion.getNombre());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateTipoAfiliacion(Tipo_AfiliacionDto tipoAfiliacion) throws Exception{
    
        PreparedStatement actualizaTipoParentezco = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE tipo_afiliacion SET nombre=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            actualizaTipoParentezco = DB.conexion.prepareStatement(sqlUpdate);
            actualizaTipoParentezco.setString(1,  tipoAfiliacion.getNombre());
            actualizaTipoParentezco.setInt(2, tipoAfiliacion.getId());
            
            actualizaTipoParentezco.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelTipoAfiliacion(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement buscarAnalisis = null;
        String sqlBuscarAnalisis = "SELECT Nombre, id from tipo_afiliacion where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        buscarAnalisis = DB.conexion.prepareStatement(sqlBuscarAnalisis);
        rs = buscarAnalisis.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteTipoAfiliacion(Tipo_AfiliacionDto tipoAfiliacion){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM tipo_afiliacion\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoAfiliacion.getId());
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
    
    //ORIGEN PADECIMIENTO
    public List<Origen_PadecimientoDto> GetAllOrigenPadecimiento() throws SQLException{
        PreparedStatement ps = null;
        String sqlLlenarPago = "SELECT id, nombre FROM origen_padecimiento order by id asc";
        ResultSet rs = null;
        List<Origen_PadecimientoDto> origenesPadecimiento = new ArrayList<Origen_PadecimientoDto>();
        Origen_PadecimientoDto origenPadecimiento;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlLlenarPago);
        
        rs = ps.executeQuery();
        
        while(rs.next()){
            origenPadecimiento = new Origen_PadecimientoDto();
            
            origenPadecimiento.setNombre(rs.getString("nombre"));
            origenPadecimiento.setId(rs.getInt("id"));
            
            origenesPadecimiento.add(origenPadecimiento);
        }
              
        return origenesPadecimiento;
    }
    
    public DefaultTableModel getModelOrigenPadecimiento(List<Origen_PadecimientoDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(Origen_PadecimientoDto tipoAfiliacion: lista){
               
                    registro[0] = tipoAfiliacion.getNombre();
                    registro[1] = tipoAfiliacion.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertOrigenPadecimiento(Origen_PadecimientoDto origenPadecimiento) throws Exception{
    
        PreparedStatement ps = null;
        String sqlInsertar = "INSERT INTO origen_padecimiento (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlInsertar);
            ps.setString(1,  origenPadecimiento.getNombre());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateOrigenPadecimiento(Origen_PadecimientoDto origenPadecimiento) throws Exception{
    
        PreparedStatement ps = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE origen_padecimiento SET nombre=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlUpdate);
            ps.setString(1,  origenPadecimiento.getNombre());
            ps.setInt(2, origenPadecimiento.getId());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelOrigenPadecimiento(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement ps = null;
        String sqlBuscar = "SELECT Nombre, id from origen_padecimiento where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlBuscar);
        rs = ps.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteOrigenPadecimiento(Origen_PadecimientoDto tipoAfiliacion){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM origen_padecimiento\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, tipoAfiliacion.getId());
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
    
    //OCUPACION
    public List<OcupacionDto> GetAllOcupacion2() throws SQLException{
        PreparedStatement ps = null;
        String sqlLlenarPago = "SELECT id, nombre FROM ocupacion order by id asc";
        ResultSet rs = null;
        List<OcupacionDto> ocupaciones = new ArrayList<OcupacionDto>();
        OcupacionDto ocupacion;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlLlenarPago);
        
        rs = ps.executeQuery();
        
        while(rs.next()){
            ocupacion = new OcupacionDto();
            
            ocupacion.setNombre(rs.getString("nombre"));
            ocupacion.setId(rs.getInt("id"));
            
            ocupaciones.add(ocupacion);
        }
              
        return ocupaciones;
    }
    
    public DefaultTableModel getModelOcupacion(List<OcupacionDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(OcupacionDto ocupacion: lista){
               
                    registro[0] = ocupacion.getNombre();
                    registro[1] = ocupacion.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertOcupacion(OcupacionDto ocupacion) throws Exception{
    
        PreparedStatement ps = null;
        String sqlInsertar = "INSERT INTO ocupacion (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlInsertar);
            ps.setString(1,  ocupacion.getNombre());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateOcupacion(OcupacionDto ocupacion) throws Exception{
    
        PreparedStatement ps = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE ocupacion SET nombre=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlUpdate);
            ps.setString(1,  ocupacion.getNombre());
            ps.setInt(2, ocupacion.getId());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelOcupacion(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement ps = null;
        String sqlBuscar = "SELECT Nombre, id from ocupacion where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlBuscar);
        rs = ps.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteOcupacion(OcupacionDto ocupacion){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM ocupacion\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, ocupacion.getId());
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
    
    //ESPECIALIDAD
    public List<EspecialidadDto> GetAllEspecialidad2() throws SQLException{
        PreparedStatement ps = null;
        String sqlLlenarPago = "SELECT id, nombre FROM especialidad order by id asc";
        ResultSet rs = null;
        List<EspecialidadDto> especialidades = new ArrayList<EspecialidadDto>();
        EspecialidadDto especialidad;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlLlenarPago);
        
        rs = ps.executeQuery();
        
        while(rs.next()){
            especialidad = new EspecialidadDto();
            
            especialidad.setNombre(rs.getString("nombre"));
            especialidad.setId(rs.getInt("id"));
            
            especialidades.add(especialidad);
        }
              
        return especialidades;
    }
    
    public DefaultTableModel getModelEspecialidad(List<EspecialidadDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
          
        Object[] registro = new Object[2];
        
        Collections.reverse(lista);
        for(EspecialidadDto ocupacion: lista){
               
                    registro[0] = ocupacion.getNombre();
                    registro[1] = ocupacion.getId();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertEspecialidad(EspecialidadDto especialidad) throws Exception{
    
        PreparedStatement ps = null;
        String sqlInsertar = "INSERT INTO especialidad (nombre) VALUES (?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlInsertar);
            ps.setString(1,  especialidad.getNombre());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateEspecialidad(EspecialidadDto especialidad) throws Exception{
    
        PreparedStatement ps = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE especialidad SET nombre=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlUpdate);
            ps.setString(1,  especialidad.getNombre());
            ps.setInt(2, especialidad.getId());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelEspecialidad(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        PreparedStatement ps = null;
        String sqlBuscar = "SELECT Nombre, id from especialidad where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlBuscar);
        rs = ps.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[2];
                for (int row = 0; row < 2; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteEspecialidad(EspecialidadDto especialidad){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM especialidad\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, especialidad.getId());
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
    
    //INSTITUCION
    public List<InstitucionDto> GetAllInstitucion2() throws SQLException{
        PreparedStatement ps = null;
        String sqlLlenarPago = "SELECT id, nombre, telefono, direccion FROM institucion order by id asc";
        ResultSet rs = null;
        List<InstitucionDto> instituciones = new ArrayList<InstitucionDto>();
        InstitucionDto institucion;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlLlenarPago);
        
        rs = ps.executeQuery();
        
        while(rs.next()){
            institucion = new InstitucionDto();
            
            institucion.setNombre(rs.getString("nombre"));
            institucion.setDireccion(rs.getString("direccion"));
            institucion.setTelefono(rs.getString("telefono"));
            institucion.setId(rs.getInt("id"));
            
            instituciones.add(institucion);
        }
              
        return instituciones;
    }
    
    public DefaultTableModel getModelInstitucion(List<InstitucionDto> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
          
        Object[] registro = new Object[4];
        
        Collections.reverse(lista);
        for(InstitucionDto ocupacion: lista){
               
                    registro[0] = ocupacion.getNombre();
                    registro[1] = ocupacion.getId();
                    registro[2] = ocupacion.getTelefono();
                    registro[3] = ocupacion.getDireccion();
                    
                modelo.addRow(registro);
               
            } 
        
        return modelo;
    }
    
    public String insertInstitucion(InstitucionDto institucion) throws Exception{
    
        PreparedStatement ps = null;
        String sqlInsertar = "INSERT INTO institucion (nombre,telefono,direccion) VALUES (?,?,?)";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlInsertar);
            ps.setString(1,  institucion.getNombre());
            ps.setString(2,  institucion.getTelefono());
            ps.setString(3,  institucion.getDireccion());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public String updateInstitucion(InstitucionDto institucion) throws Exception{
    
        PreparedStatement ps = null;
        String sqlUpdate = null;
        
        sqlUpdate = "UPDATE institucion SET nombre=?, telefono=?, direccion=? WHERE id=?;";
        
        try {
            DB.conexion.setAutoCommit(false);
            ps = DB.conexion.prepareStatement(sqlUpdate);
            ps.setString(1,  institucion.getNombre());
            ps.setString(2,  institucion.getTelefono());
            ps.setString(3,  institucion.getDireccion());
            ps.setInt(4, institucion.getId());
            
            ps.executeUpdate();
            
            DB.conexion.commit(); 
     
        } catch (Exception exc) {
            throw exc;
        }
        
        return null;
    }
    
    public DefaultTableModel filtraModelInstitucion(String filtro) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Id");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        
        PreparedStatement ps = null;
        String sqlBuscar = "SELECT Nombre, id , telefono, direccion from institucion where Nombre like '%" + filtro + "%'";
        
        ResultSet rs = null;
        
        DB.conexion.setAutoCommit(false);
        ps = DB.conexion.prepareStatement(sqlBuscar);
        rs = ps.executeQuery();

        while (rs.next()) {
            
            Object[] datos = new Object[4];
                for (int row = 0; row < 4; row++) {
                    datos[row] = rs.getObject(row+1);

                }
                modelo.addRow(datos);
        }
        
        return modelo;
    } 
    
    public void deleteInstitucion(InstitucionDto institucion){
        PreparedStatement ps = null;
        String sqlDelete="DELETE FROM institucion\n" +
                         " WHERE id=?";
        try {
            DB.conexion.setAutoCommit(false);
            
            ps = DB.conexion.prepareStatement(sqlDelete);
            ps.setInt(1, institucion.getId());
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
}

