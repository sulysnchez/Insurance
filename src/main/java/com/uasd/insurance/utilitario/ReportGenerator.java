/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uasd.insurance.utilitario;

import com.itla.insurance.dao.DB;
import com.uasd.insurance.vistas.transacciones.frm_Pago;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Sulenni
 */
public class ReportGenerator {
    public static void allAnalisis(){
        try {
            JasperReport jr = (JasperReport)JRLoader.
            loadObject("C:\\Users\\Sulenni\\Repos\\Insurance\\src\\main\\java\\com\\uasd\\insurance\\vistas\\reportes\\reporteAnalisis.jasper");
            Map param = new HashMap();
            param.put(" ", 1);
            DB db = new DB();
            JasperPrint jp = JasperFillManager.fillReport(jr, param,DB.conexion);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Analisis");
            jv.setVisible(true);
        } catch (Exception e) {
            
        }
    }
    public static void allAfiliado(){
        try {
            JasperReport jr = (JasperReport)JRLoader.
            loadObject("C:\\Users\\Sulenni\\Repos\\Insurance\\src\\main\\java\\com\\uasd\\insurance\\vistas\\reportes\\reporteAfiliado.jasper");
            Map param = new HashMap();
            param.put(" ", 1);
            DB db = new DB();
            JasperPrint jp = JasperFillManager.fillReport(jr, param,DB.conexion);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Afiliados");
            jv.setVisible(true);
        } catch (Exception e) {
           
        }
    }
    public static void allPrestadores(){
        try {
            JasperReport jr = (JasperReport)JRLoader.
            loadObject("C:\\Users\\Sulenni\\Repos\\Insurance\\src\\main\\java\\com\\uasd\\insurance\\vistas\\reportes\\reportPrestador.jasper");
            Map param = new HashMap();
            param.put(" ", 1);
            DB db = new DB();
            JasperPrint jp = JasperFillManager.fillReport(jr, param,DB.conexion);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Prestadores");
            jv.setVisible(true);
        } catch (Exception e) {
           
        }
    }
    public static void allPrestadores2(){
        try {
            JasperReport jr = (JasperReport)JRLoader.
            loadObject("C:\\Users\\Sulenni\\Repos\\Insurance\\src\\main\\java\\com\\uasd\\insurance\\vistas\\reportes\\Pagos.jasper");
            Map param = new HashMap();
            param.put(" ", 1);
            DB db = new DB();
            JasperPrint jp = JasperFillManager.fillReport(jr, param,DB.conexion);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Prestadores");
            jv.setVisible(true);
        } catch (Exception e) {
           
        }
    }
    
    public static void pagosPorPrestador(Integer idPrestador){
        try {
            JasperReport jr = (JasperReport)JRLoader.
            loadObject("C:\\Users\\Sulenni\\Repos\\Insurance\\src\\main\\java\\com\\uasd\\insurance\\vistas\\reportes\\PagoPorPrestador.jasper");
            Map param = new HashMap();
            param.put("idPrestador", idPrestador);
            DB db = new DB();
            JasperPrint jp = JasperFillManager.fillReport(jr, param,DB.conexion);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Pago por prestadores");
            jv.setVisible(true);
        } catch (Exception e) {
           
        }
    }
    
    public static void allReclamacionesPendientesDePago(){
        try {
            JasperReport jr = (JasperReport)JRLoader.
            loadObject("C:\\Users\\Sulenni\\Repos\\Insurance\\src\\main\\java\\com\\uasd\\insurance\\vistas\\reportes\\ReclamacionesPendientes.jasper");
            DB db = new DB();
            JasperPrint jp = JasperFillManager.fillReport(jr, null,DB.conexion);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Reclamaciones Pendientes de Pago");
            jv.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, e);           
        }
    }
    
    
    public static void allReclamacionesPagas(){
        try {
            JasperReport jr = (JasperReport)JRLoader.
            loadObject("C:\\Users\\Sulenni\\Repos\\Insurance\\src\\main\\java\\com\\uasd\\insurance\\vistas\\reportes\\ReclamacionesPagadas.jasper");
            DB db = new DB();
            JasperPrint jp = JasperFillManager.fillReport(jr, null,DB.conexion);
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setTitle("Reclamaciones Pendientes de Pago");
            jv.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, e);           
        }
    }
    
}
